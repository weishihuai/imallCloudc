package com.imall.iportal.core.main.vo;


import com.imall.iportal.core.main.entity.SysDeveloperAuth;

/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysDeveloperAuthVo extends SysDeveloperAuth {

    private String userName;
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
