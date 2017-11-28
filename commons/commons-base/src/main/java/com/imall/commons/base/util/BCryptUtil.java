package com.imall.commons.base.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by ygw on 2016/4/7.
 */
public class BCryptUtil {

    public static String hashpw(String source, String salt) {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        return md5.encodePassword(source, salt);
    }
}
