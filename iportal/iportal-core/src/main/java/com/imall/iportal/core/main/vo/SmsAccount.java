package com.imall.iportal.core.main.vo;

/**
 * Created by yys on 2017/2/16.
 */
public class SmsAccount {

    private String account;             //短信账号
    private String password;            //密码
    private String smsInterfaceType;    //短信接口类型

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsInterfaceType() {
        return smsInterfaceType;
    }

    public void setSmsInterfaceType(String smsInterfaceType) {
        this.smsInterfaceType = smsInterfaceType;
    }
}
