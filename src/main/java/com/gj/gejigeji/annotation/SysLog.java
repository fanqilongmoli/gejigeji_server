package com.gj.gejigeji.annotation;

import java.lang.annotation.*;



/**
 * 1.注解定义:java文件叫做Annotation，用@interface表示
 * 2.元注解：@interface上面需要注解一些东西，包括@Retention、@Target、@Document、@Inherited
 *      - @Retention 注解保留策略
 *          - @Retention(RetentionPolicy.SOURCE) 注解仅存在源码中，在class字节中不包含
 *          - @Retention(RetentionPolicy.CLASS)  默认的保留策略，注解会在class字节码文件中存在，但是运行时无法获得
 *          - @Retention(RetentionPolicy.RUNTIME) 注解会在class字节码中存在，在运行时可以通过反射获得
 *      - @Target 注解的作用目标
 *          - @Target(ElementType.TYPE)  接口、类、枚举、注解
 *          - @Target(ElementType.FIELD)  字段、枚举的常量
 *          - @Target(ElementType.METHOD) 方法
 *          - @Target(ElementType.PARAMETER) 方法参数
 *          - @Target(ElementType.CONSTRUCTOR) 构造函数
 *          - @Target(ElementType.LOCAL_VARIABLE)  局部变量
 *          - @Target(ElementType.ANNOTATION_TYPE) 注解
 *          - @Target(ElementType.PACKAGE) 包
 *      - @Documented 注解包含在javadoc中
 *      - @Inherited 注解可以被继承
 *
 * 系统日志注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
