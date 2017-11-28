package com.imall.iportal.sso.vo;

import java.util.Date;

/**
 * Created by ygw on 2015/12/3.
 */
public class SsoAuthVo {

    private String newTicket;
    private String oldTicket;
    private Date createTime;
    private String sessionId;
    private String userName; //登录ID

    //五分钟失效

    public String getNewTicket() {
        return newTicket;
    }

    public void setNewTicket(String newTicket) {
        this.newTicket = newTicket;
    }

    public String getOldTicket() {
        return oldTicket;
    }

    public void setOldTicket(String oldTicket) {
        this.oldTicket = oldTicket;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
