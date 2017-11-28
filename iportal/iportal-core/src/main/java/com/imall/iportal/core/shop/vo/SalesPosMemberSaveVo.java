package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/6.
 * 前端销售pos会员 - 保存Vo对象
 */
public class SalesPosMemberSaveVo implements Cloneable, java.io.Serializable {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 会员姓名
     */
    @NotBlank
    @Length(max = 32)
    private String name;
    /**
     * 手机号码
     */
    @NotBlank
    @Length(max = 32)
    private String mobile;
    /**
     * 会员卡号
     */
    private String memberCardNum;
    /**
     * 发卡人
     */
    private String giveCardMan;
    /**
     * 住址
     */
    private String homeAddr;
    /**
     * 会员状态代码
     */
    private String memberStateCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getGiveCardMan() {
        return giveCardMan;
    }

    public void setGiveCardMan(String giveCardMan) {
        this.giveCardMan = giveCardMan;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getMemberStateCode() {
        return memberStateCode;
    }

    public void setMemberStateCode(String memberStateCode) {
        this.memberStateCode = memberStateCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
