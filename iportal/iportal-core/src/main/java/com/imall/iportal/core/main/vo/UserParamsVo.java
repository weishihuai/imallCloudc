package com.imall.iportal.core.main.vo;

/**
 * Created by yang on 2015-09-23.
 * 用户名称、真实姓名、员工工号、Email、手机、机构、状态
 */
public class UserParamsVo {
    /**
     * 门店名称
     */
    private String shopNm;

    /**
     * 姓名|用户名称
     */
    private String name;
    /**
     *  所属组织机构 ID
     */
    private java.lang.Long orgId;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 状态
     */
    private String isEnable;
    /**
     * 开始时间
     */
    private String startTimeString;
    /**
     * 结束时间
     */
    private String endTimeString;

    public String getShopNm() {
        return shopNm;
    }

    public void setShopNm(String shopNm) {
        this.shopNm = shopNm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }
}
