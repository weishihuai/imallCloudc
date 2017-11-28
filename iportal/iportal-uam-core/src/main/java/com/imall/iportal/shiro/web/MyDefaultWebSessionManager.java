package com.imall.iportal.shiro.web;

import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ygw on 2017/8/17.
 */
public class MyDefaultWebSessionManager extends DefaultWebSessionManager {

    private static final Logger log = LoggerFactory.getLogger(DefaultWebSessionManager.class);

    //bug修复
    @Override
    protected synchronized void enableSessionValidation() {
        SessionValidationScheduler scheduler = this.getSessionValidationScheduler();
        if(scheduler == null) {
            super.enableSessionValidation();
            return;
        }
        if(!scheduler.isEnabled()){
            scheduler.enableSessionValidation();
            this.afterSessionValidationEnabled();
        }

    }
}
