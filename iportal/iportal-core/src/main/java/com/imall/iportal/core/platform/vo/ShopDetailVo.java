package com.imall.iportal.core.platform.vo;

import java.util.List;

/**
 * Created by ou on 2017/7/24.
 */
public class ShopDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 企业 编码
     */
    private String shopCode;
    /**
     * 企业 名称
     */
    private String entNm;
    /**
     * 法定代表人
     */
    private String legalRepresentativeMan;
    /**
     * 企业 负责 人 姓名
     */
    private String entResponseMan;
    /**
     * 税务登记证号
     */
    private String taxRegisterCertNum;
    /**
     * 企业 类型
     */
    private String entType;
    /**
     * 企业 类型
     */
    private String entTypeCode;
    /**
     * 是否 医保 门店
     */
    private String isMedicalInsuranceShop;
    /**
     * 经营 方式
     */
    private String businessWay;
    /**
     * 经营 方式
     */
    private String businessWayCode;
    /**
     * 注册 资本
     */
    private Double regCapital;
    /**
     * 注册 地址
     */
    private String regAddr;
    /**
     * 公司 地址
     */
    private String companyAddr;
    /**
     * 仓库 地址
     */
    private String storageAddr;
    /**
     * 公司 电话
     */
    private String companyTel;
    /**
     * 公司 传真
     */
    private String companyFax;
    /**
     * 年检 日期
     */
    private String annualInspectionDateString;
    /**
     * 公司 简介
     */
    private String companyBrief;
    /**
     * 经营 范围
     */
    private String businessRange;
    /**
     *门店 状态 (是否 启用)
     */
    private String isEnable;

    /**
     *用户名称，用户登陆名
     */
    private String userName;
    /**
     *用户Id
     */
    private Long userId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 资质文件
     */
    List<ShopCertificatesFileVo> shopCertificatesFileVoList;

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getEntTypeCode() {
        return entTypeCode;
    }

    public void setEntTypeCode(String entTypeCode) {
        this.entTypeCode = entTypeCode;
    }

    public String getBusinessWayCode() {
        return businessWayCode;
    }

    public void setBusinessWayCode(String businessWayCode) {
        this.businessWayCode = businessWayCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEntNm() {
        return entNm;
    }

    public void setEntNm(String entNm) {
        this.entNm = entNm;
    }

    public String getLegalRepresentativeMan() {
        return legalRepresentativeMan;
    }

    public void setLegalRepresentativeMan(String legalRepresentativeMan) {
        this.legalRepresentativeMan = legalRepresentativeMan;
    }

    public String getEntResponseMan() {
        return entResponseMan;
    }

    public void setEntResponseMan(String entResponseMan) {
        this.entResponseMan = entResponseMan;
    }

    public String getTaxRegisterCertNum() {
        return taxRegisterCertNum;
    }

    public void setTaxRegisterCertNum(String taxRegisterCertNum) {
        this.taxRegisterCertNum = taxRegisterCertNum;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getIsMedicalInsuranceShop() {
        return isMedicalInsuranceShop;
    }

    public void setIsMedicalInsuranceShop(String isMedicalInsuranceShop) {
        this.isMedicalInsuranceShop = isMedicalInsuranceShop;
    }

    public String getBusinessWay() {
        return businessWay;
    }

    public void setBusinessWay(String businessWay) {
        this.businessWay = businessWay;
    }

    public Double getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(Double regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getStorageAddr() {
        return storageAddr;
    }

    public void setStorageAddr(String storageAddr) {
        this.storageAddr = storageAddr;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getAnnualInspectionDateString() {
        return annualInspectionDateString;
    }

    public void setAnnualInspectionDateString(String annualInspectionDateString) {
        this.annualInspectionDateString = annualInspectionDateString;
    }

    public String getCompanyBrief() {
        return companyBrief;
    }

    public void setCompanyBrief(String companyBrief) {
        this.companyBrief = companyBrief;
    }

    public String getBusinessRange() {
        return businessRange;
    }

    public void setBusinessRange(String businessRange) {
        this.businessRange = businessRange;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<ShopCertificatesFileVo> getShopCertificatesFileVoList() {
        return shopCertificatesFileVoList;
    }

    public void setShopCertificatesFileVoList(List<ShopCertificatesFileVo> shopCertificatesFileVoList) {
        this.shopCertificatesFileVoList = shopCertificatesFileVoList;
    }
}
