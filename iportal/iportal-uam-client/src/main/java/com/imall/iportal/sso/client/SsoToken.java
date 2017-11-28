package com.imall.iportal.sso.client;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by yang on 2015-10-21.
 */
public class SsoToken implements AuthenticationToken {


    private String ticket;
    private String principal;

    public SsoToken(){

    }

    public SsoToken(String ticket, String principal) {
        this.ticket = ticket;
        this.principal = principal;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return ticket;
    }
}