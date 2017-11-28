package com.imall.iportal.core.main.vo;

/**
 * Created by yang on 2015-09-23.
 */
public class DeveloperAuthParamsVo {

    private String userName;
    private String appName;
    private String hostname;
    private String isAvaiable;

    public DeveloperAuthParamsVo(){}

    public DeveloperAuthParamsVo(String userName, String appName, String hostname, String isAvaiable) {
        this.userName = userName;
        this.appName = appName;
        this.hostname = hostname;
        this.isAvaiable = isAvaiable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIsAvaiable() {
        return isAvaiable;
    }

    public void setIsAvaiable(String isAvaiable) {
        this.isAvaiable = isAvaiable;
    }
}
