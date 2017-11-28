package com.imall.iportal.core.weshop.vo;

/**
 * Created by lxh on 2017/8/9.
 */
public class WeChatBaseResult {
    private Integer errcode;
    private String errmsg;

    public WeChatBaseResult(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public WeChatBaseResult() {
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
