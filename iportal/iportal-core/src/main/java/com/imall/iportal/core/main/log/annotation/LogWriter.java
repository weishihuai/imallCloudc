package com.imall.iportal.core.main.log.annotation;


import com.imall.iportal.core.main.log.dicts.LogTypeCodeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: ygw
 * Date: 17-8-29
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogWriter {

    LogTypeCodeEnum type();

    String title() default "";

    String nav() default "";
}
