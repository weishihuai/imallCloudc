package com.imall.iportal.core.main.valid;

/**
 * Created by ygw on 2016/12/12.
 */
public class SysDeveloperAuthSaveValid implements Cloneable, java.io.Serializable {

    public static final long serialVersionUID = -15263788L;

    private Long userId;

    private String userName;

    private Long appId;

    private String appName;

    private String hostname;

    private String isAvailable;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
