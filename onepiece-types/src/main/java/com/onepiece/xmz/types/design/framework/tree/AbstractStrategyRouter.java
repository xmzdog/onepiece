package com.onepiece.xmz.types.design.framework.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/23
 * Time: 22:54
 * Description: 策略路由器
 *  策略路由抽象类
 *  策略模式的实现，用来实现灵活、可扩展的业务逻辑分发，特别适合在处理不同类型请求或上下文条件下执行不同策略的场景。
 *
 *  T：请求参数类型（比如请求数据、用户输入等）
 * D：动态上下文类型（比如用户的角色、渠道、来源等上下文）
 * R：返回值类型
 *
 *
 * StrategyMapper<T, D, R>：策略映射器，负责根据参数选择策略。
 * StrategyHandler<T, D, R>：策略处理器，负责具体执行策略逻辑。
 */
public abstract class AbstractStrategyRouter<T, D, R> implements StrategyMapper<T, D, R>, StrategyHandler<T, D, R> {

    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = DEFAULT;

    /**
     * 目的：根据参数和上下文获取合适的策略，然后执行策略并返回结果
     * @param requestParameter  请求参数
     * @param dynamicContext    上下文
     * @return
     * @throws Exception
     */
    public R router(T requestParameter, D dynamicContext) throws Exception {
        StrategyHandler<T, D, R> strategyHandler = get(requestParameter, dynamicContext);
        if(null != strategyHandler) return strategyHandler.apply(requestParameter, dynamicContext);
        return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }

}
