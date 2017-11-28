package com.imall.commons.base.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

/**
 * Created by lxd on 2014/11/28.
 */
public class DefaultUserAuditorAwareImpl implements AuditorAware<String>
{

    public DefaultUserAuditorAwareImpl() {}

    public String getCurrentAuditor()
    {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        return StringUtils.isNoneBlank(username) ? username : "HAVE_NOT_USER";
    }
}
