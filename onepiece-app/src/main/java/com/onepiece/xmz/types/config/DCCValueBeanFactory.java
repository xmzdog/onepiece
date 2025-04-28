package com.onepiece.xmz.types.config;

import com.onepiece.xmz.types.annotations.DCCValue;
import com.onepiece.xmz.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/28
 * Time: 22:21
 * Description: DCCValueBeanFactory 做的事情就是在 Spring容器启动时批量初始化字段，运行时监听Redis变化动态热更新字段值，实现了零停机的配置热更新机制。
 * 这个 DCCValueBeanFactory 是一个 Spring Bean后处理器（BeanPostProcessor），配合你之前定义的 @DCCValue 注解使用，主要完成三件事情：
 * 功能	描述
 * 1. 扫描Bean中的@DCCValue注解字段	在Spring容器初始化每个Bean之后，把带@DCCValue的字段，通过Redis读取配置并赋值到字段上
 * 2. 订阅Redis的Topic（消息频道）	监听动态配置更新事件，如果配置更新了，立刻热刷新字段值
 * 3. 管理字段与对象的关系	把字段和对象缓存起来，方便后续动态更新时定位到具体字段
 */
@Slf4j
@Configuration
public class DCCValueBeanFactory implements BeanPostProcessor {

    //    配置在redis 里key 的前缀 （目的：用来统一规范配置的命名空间）
    private static final String BASE_CONFIG_PATH = "group_buy_market_dcc_";
    //  用来访问Redis（读写bucket、订阅topic）。
    private final RedissonClient redissonClient;
    //    保存Redis Key 和 Java对象实例的对应关系。
//    以后当收到配置更新时，可以快速定位到需要改哪个对象的哪个字段。
    private final Map<String, Object> dccObjGroup = new HashMap<>();

    public DCCValueBeanFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * Redis订阅监听器
     * 监听Redis的Topic group_buy_market_dcc
     * 每次收到消息，格式是 "字段名:新值"，比如 "downgradeSwitch:1"
     * 处理流程：
     * 1. 拆分字段名和新值
     * 2. 查找Redis里这个字段的Bucket
     * 3. 更新Redis中缓存的值
     * 4. 从 dccObjGroup 找到对应的Bean
     * 5. 用反射设置这个字段的新值
     */
    @Bean("dccTopic")
    public RTopic dccRedisTopicListener(RedissonClient redissonClient) {
        /**
         * RTopic 是Redisson 提供的一个接口，用来做 Redis 的发布订阅功能。用来操作Redisson里面封装好的“操作Redis频道(Topic)” 的一个工具
         * 使用Topic在Redis里面实现 发布、订阅、监听消息的功能
         */
//        拿到 Redis中的 "group_buy_market_dcc" 频道（Topic）对象。
        RTopic topic = redissonClient.getTopic("group_buy_market_dcc");
        /**
         * 监听回调的参数名
         * charSequence ：值消息的频道名
         * s: 值真正发布的消息内容
         *
         * 当有程序执行了 topic.publish("downgradeSwitch:0")
         * 此时监听器回调的时候，这两个参数就是：
         * charSequence = "group_buy_market_dcc"（谁发的）
         * message = "downgradeSwitch:0"（发了什么内容）
         */
//        给这个频道加一个监听器。每当别人向 "group_buy_market_dcc" 频道 publish 消息时，这里就能自动收到。
        topic.addListener(String.class, (charSequence, s) -> {
            String[] split = s.split(Constants.SPLIT);

            // 获取值
            String attribute = split[0];
            String key = BASE_CONFIG_PATH + attribute;
//            最新值
            String value = split[1];

            // 设置值
            RBucket<String> bucket = redissonClient.getBucket(key);
            boolean exists = bucket.isExists();
            if (!exists) return;
            bucket.set(value);

            Object objBean = dccObjGroup.get(key);
            if (null == objBean) return;

            Class<?> objBeanClass = objBean.getClass();
            // 检查 objBean 是否是代理对象 （比如加了事务@Transactional、切面@Aspect的对象），
            if (AopUtils.isAopProxy(objBean)) {
                // 获取代理对象的目标对象
                objBeanClass = AopUtils.getTargetClass(objBean);
            }

            try {
                // 1. getDeclaredField 方法用于获取指定类中声明的所有字段，包括私有字段、受保护字段和公共字段。
                // 2. getField 方法用于获取指定类中的公共字段，即只能获取到公共访问修饰符（public）的字段。
                /**
                 *  attribute 的值为：downgradeSwitch 或者 cutRange
                 *  objBeanClass.getDeclaredField(attribute);  找到目标的字段
                 */
                Field field = objBeanClass.getDeclaredField(attribute);
//                允许操作私有字段
                field.setAccessible(true);
//                将这个私有字段的值设置成最新的值
                field.set(objBean, value);
//                恢复字段的访问性
                field.setAccessible(false);

                log.info("DCC 节点监听，动态设置值 {} {}", key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return topic;
    }

    /**
     * Spring容器初始化每个Bean之后，调用这个方法，它主要干了：
     * 判断当前Bean是否是AOP代理，如果是，拿到真实对象。
     * 遍历Bean的所有字段。
     * 找到带 @DCCValue 注解的字段。
     * 解析注解内容，比如：
     * downgradeSwitch:0 → key是group_buy_market_dcc_downgradeSwitch，默认值是0
     * 检查Redis中是否已经有这个key：
     * 没有 → 用默认值初始化
     * 有 → 取Redis里的最新值
     * 把取到的值，用反射设置到字段上。
     * 把Bean注册进 dccObjGroup，方便后续动态更新。
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 注意；增加 AOP 代理后，获得类的方式要通过 AopProxyUtils.getTargetClass(bean); 不能直接 bean.class 因为代理后类的结构发生变化，这样不能获得到自己的自定义注解了。
        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;
        if (AopUtils.isAopProxy(bean)) {
//            确保能正确地反射出原本的字段和注解。
            targetBeanClass = AopUtils.getTargetClass(bean);    //  拿到原始类
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean);  // 拿到原始对象实例。
        }
//拿到这个Bean所有的字段（包括私有的private字段）。
        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field : fields) {
//            如果字段上没有加 @DCCValue 注解，就直接跳过，不管。
            if (!field.isAnnotationPresent(DCCValue.class)) {
                continue;
            }

//            拿到注解里配置的字符串，比如 "downgradeSwitch:0"。
//            如果这个字符串是空的，直接抛出异常 —— 防止开发时忘记填写默认值。
            DCCValue dccValue = field.getAnnotation(DCCValue.class);
            String value = dccValue.value();
            if (StringUtils.isBlank(value)) {
                throw new RuntimeException(field.getName() + " @DCCValue is not config value config case 「isSwitch/isSwitch:1」");
            }
//            "downgradeSwitch:0" → splits[0]="downgradeSwitch"，splits[1]="0"
//            组装出完整的Redis Key："group_buy_market_dcc_downgradeSwitch"
            String[] splits = value.split(":");
            String key = BASE_CONFIG_PATH.concat(splits[0]);
            String defaultValue = splits.length == 2 ? splits[1] : null;

            // 设置值
            String setValue = defaultValue;

            try {
                // 如果为空则抛出异常
                if (StringUtils.isBlank(defaultValue)) {
                    throw new RuntimeException("dcc config error " + key + " is not null - 请配置默认值！");
                }

                // Redis 操作，判断配置Key是否存在，不存在则创建，存在则获取最新值
                RBucket<String> bucket = redissonClient.getBucket(key);
                boolean exists = bucket.isExists();
                if (!exists) {
                    bucket.set(defaultValue);
                } else {
                    setValue = bucket.get();
                }

//                通过反射设置字段值
                field.setAccessible(true);
                field.set(targetBeanObject, setValue);
                field.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//把【Redis Key → Bean实例】关系注册到 dccObjGroup 这个Map里。
            dccObjGroup.put(key, targetBeanObject);
        }

        return bean;
    }

}
