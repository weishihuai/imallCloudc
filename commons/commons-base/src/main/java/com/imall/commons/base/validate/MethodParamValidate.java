package com.imall.commons.base.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodParamValidate {

    /**
     * 参数名称
     */
    String name();

    /**
     * 正则表达式，适用于基础数据类型 Long，Integer，Double，String
     */
    String regex() default "";

    /**
     * 字符串最小长度 或 数组最小数量  -1表示不验证
     */
    long min() default -1;

    /**
     * 字符串最大长度  或 数组最大数量   -1表示不验证
     */
    long max() default -1;

    /**
     * 是否必填
     */
    boolean required() default false;

    /**
     * 若参数是对象，则必须声明此对象Class
     */
    Class clazz() default IParamObject.class;

    /**
     * 错误消息
     */
    String errorMsg() default "";

}