package com.imall.iportal.shiro.bind.annotation;

import com.imall.commons.base.global.Global;

import java.lang.annotation.*;

/**
 *  <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 * Created by yang on 2015-10-28.
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrUser {

    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Global.CURR_USER;
}
