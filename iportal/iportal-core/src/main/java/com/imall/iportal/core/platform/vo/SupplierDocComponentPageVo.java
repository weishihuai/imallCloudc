package com.imall.iportal.core.platform.vo;

import java.util.List;

/**
 * Created by ou on 2017/7/7.
 */
public class SupplierDocComponentPageVo {

    /**
     * 供应商 id
     */
    private Long id;
    /**
     * 供应商 编码
     */
    private Long supplierCode;

    /**
     * 供应商 名称
     */
    private  String supplierNm;
    /**
     * 单位 性质
     */
    private  String unitNature;
    /**
     * 质量 负责 人 姓名
     */
    private  String qualityResponseManName;
    /**
     *  法定代表人
     */
    private  String legalRepresentative;
    /**
     *  业务 负责 人
     */
    private  String businessResponseManName;
    /**
     * 业务 负责 人 电话
     */
    private  String businessResponseManTel;
    /**
     * 业务 负责 人 邮箱
     */
    private  String businessResponseManEmail;
    /**
     * 注册 资本
     */
    private  Double regCapital;
    /**
     * 注册 地址
     */
    private  String regAddr;
    /**
     * 传真
     */
    private  String fax;
    /**
     * 退货 地址
     */
    private  String returnedPurchaseAddr;
    /**
     * 经营 许可证号
     */
    private  String commodityLicense;
    /**
     * 营业执照号
     */
    private  String businessLicense;

    /**
     * 经营 范围
     */
    private String businessRange;
    /**
     * 经营 分类
     */
    private String businessCategory;
    /**
     * 状态
     */
    private String state;
    /**
     *  资质文件
     */
    private List<SupplierDocCertificatesFileVo> supplierCertificatesFileList;

    public Long getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(Long supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getUnitNature() {
        return unitNature;
    }

    public void setUnitNature(String unitNature) {
        this.unitNature = unitNature;
    }

    public String getQualityResponseManName() {
        return qualityResponseManName;
    }

    public void setQualityResponseManName(String qualityResponseManName) {
        this.qualityResponseManName = qualityResponseManName;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getBusinessResponseManName() {
        return businessResponseManName;
    }

    public void setBusinessResponseManName(String businessResponseManName) {
        this.businessResponseManName = businessResponseManName;
    }

    public String getBusinessResponseManTel() {
        return businessResponseManTel;
    }

    public void setBusinessResponseManTel(String businessResponseManTel) {
        this.businessResponseManTel = businessResponseManTel;
    }

    public String getBusinessResponseManEmail() {
        return businessResponseManEmail;
    }

    public void setBusinessResponseManEmail(String businessResponseManEmail) {
        this.businessResponseManEmail = businessResponseManEmail;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getReturnedPurchaseAddr() {
        return returnedPurchaseAddr;
    }

    public void setReturnedPurchaseAddr(String returnedPurchaseAddr) {
        this.returnedPurchaseAddr = returnedPurchaseAddr;
    }

    public String getCommodityLicense() {
        return commodityLicense;
    }

    public void setCommodityLicense(String commodityLicense) {
        this.commodityLicense = commodityLicense;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessRange() {
        return businessRange;
    }

    public void setBusinessRange(String businessRange) {
        this.businessRange = businessRange;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public List<SupplierDocCertificatesFileVo> getSupplierCertificatesFileList() {
        return supplierCertificatesFileList;
    }

    public void setSupplierCertificatesFileList(List<SupplierDocCertificatesFileVo> supplierCertificatesFileList) {
        this.supplierCertificatesFileList = supplierCertificatesFileList;
    }
}
