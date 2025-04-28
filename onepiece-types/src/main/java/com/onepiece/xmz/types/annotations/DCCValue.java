package com.onepiece.xmz.types.annotations;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/28
 * Time: 21:59
 * Description: 这段代码定义了一个只能加在字段上、运行时可以通过反射读取、带有一个可选字符串参数的注解，并且会出现在Javadoc文档里。
 *
 *
 * @Retention(RetentionPolicy.RUNTIME)
 * 意思：这个注解（@DCCValue）的生命周期是运行时（Runtime）。
 * 作用：编译后这个注解信息还保留在 .class 文件里，而且在程序运行期间可以通过反射拿到。
 * 举例：比如你可以用 Field.getAnnotation(DCCValue.class) 反射读取它。
 * 生命周期	说明
 * SOURCE	只在源码阶段保留，编译后就没了（比如 @Override）
 * CLASS	编译成 .class 文件时存在，但运行时不加载到内存（默认值）
 * RUNTIME	编译后存在，运行时也能通过反射拿到（最常用来做运行时处理，比如Spring里大量用Runtime）
 *
 *
 * @Target({ElementType.FIELD})
 * 意思：这个注解 @DCCValue 只能用在字段（Field）上。
 * 作用：你只能在类的成员变量上加 @DCCValue，不能加在方法、类、参数等其他地方。
 *
 *@Documented
 * 意思：使用这个注解（@DCCValue）的字段，在生成javadoc文档时会显示出来。
 * 作用：让自动化文档工具（比如Javadoc）把这个注解也列到文档里。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DCCValue {
    String value() default "";
}
