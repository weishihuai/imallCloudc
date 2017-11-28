package com.imall.iportal.core.main.log.annotation;

import com.imall.iportal.core.main.log.dicts.LogColumnTypeEnum;

/**
 * User: ygw
 * Date: 17-8-29
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface LogColumnField {

    String value() default "";

    LogColumnTypeEnum type() default LogColumnTypeEnum.NORMAL;

}
