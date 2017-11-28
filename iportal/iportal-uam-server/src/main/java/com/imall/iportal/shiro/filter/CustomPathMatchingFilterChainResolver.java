package com.imall.iportal.shiro.filter;

import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

/**
 * Created by yang on 2015-10-29.
 */
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {


    public void setCustomDefaultFilterChainManager(CustomDefaultFilterChainManager customDefaultFilterChainManager) {
        setFilterChainManager(customDefaultFilterChainManager);
    }
}