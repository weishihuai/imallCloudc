package com.imall.commons.redis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用到缓存的类（标注在实类上）
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RedisCacheable {

    /**
     * 实体类
     */
    Class<?> value();
    /**
     * 表名
     */
    String tableName();
    /**
     * 所属应用
     */
     String appName();


}
