package com.imall.iportal.core.weshop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/16.
 * 列表搜索参数
 */
public class FansSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 粉丝 姓名
     */
    private String fansName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 微信称昵
     */
    private String nickName;
    /**
     * FANS_SOURCE_CODE - 粉丝 来源 代码
     */
    private String fansSourceCode;
    /**
     * BUY_TIMES - 购买 次数
     */
    private Long buyTimes;
    /**
     * IS_MEMBER - 粉丝身份
     */
    private String isMember;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getFansName() {
        return fansName;
    }

    public void setFansName(String fansName) {
        this.fansName = fansName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFansSourceCode() {
        return fansSourceCode;
    }

    public void setFansSourceCode(String fansSourceCode) {
        this.fansSourceCode = fansSourceCode;
    }

    public Long getBuyTimes() {
        return buyTimes;
    }

    public void setBuyTimes(Long buyTimes) {
        this.buyTimes = buyTimes;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }
}
