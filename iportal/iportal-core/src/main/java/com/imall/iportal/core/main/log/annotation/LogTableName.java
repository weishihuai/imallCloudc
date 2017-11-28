package com.imall.iportal.core.main.log.annotation;

/**
 * User: ygw
 * Date: 17-8-29
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface LogTableName {

    String value() default "";

}
