package com.imall.iportal.core.weshop.vo;

public class WeiXinGetJsApiTicketResult extends WeChatBaseResult {
    private String ticket;

    private Integer expires_in;//到期时间

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
