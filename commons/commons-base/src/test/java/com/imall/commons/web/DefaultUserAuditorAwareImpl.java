package com.imall.commons.web;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by lxd on 2014/11/28.
 */
public class DefaultUserAuditorAwareImpl implements AuditorAware<String>
{

    public DefaultUserAuditorAwareImpl() {}

    public String getCurrentAuditor()
    {
        return "B2C_FRONEND_USER";
    }
}
