package com.imall.iportal.core.platform.vo;

/**
 * Created by ou on 2017/7/24.
 */
public class ShopPageVo {

    /**
     *  门店 id
     */
    private Long id;
    /**
     *  orgId
     */
    private Long orgId;

    /**
     * 门店 编码
     */
    private String shopCode;
    /**
     * 企业 名称
     */
    private String entNm;
    /**
     * 公司 地址
     */
    private String companyAddr;
    /**
     * 企业 负责 人 姓名
     */
    private String entResponseMan;
    /**
     * 联系人 手机
     */
    private String mobile;
    /**
     *公司 电话
     */
    private String companyTel;
    /**
     *门店 状态 (是否 启用)
     */
    private String isEnable;
    /**
     *创建时间
     */
    private String createTimeString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getEntNm() {
        return entNm;
    }

    public void setEntNm(String entNm) {
        this.entNm = entNm;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getEntResponseMan() {
        return entResponseMan;
    }

    public void setEntResponseMan(String entResponseMan) {
        this.entResponseMan = entResponseMan;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
