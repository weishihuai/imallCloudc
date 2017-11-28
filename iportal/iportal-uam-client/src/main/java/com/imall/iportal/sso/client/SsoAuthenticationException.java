package com.imall.iportal.sso.client;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by yang on 2015-10-22.
 */

public class SsoAuthenticationException extends AuthenticationException {

    public SsoAuthenticationException(Throwable cause) {
        super(cause);
    }
}
