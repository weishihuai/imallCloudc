package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by ou on 2017/7/7.
 */
public class FirstManageSupplierQualityApproveDetailVo {

    /**
     * 首营 审核 id
     */
    private Long id;
    /**
     * 供应商 名称
     */
    private String supplierNm;
    /**
     * 单位 性质
     */
    private String unitNatureName;
    /**
     * 质量 负责 人
     */
    private String qualityResponseManName;
    /**
     * 法定代表人
     */
    private String legalRepresentative;
    /**
     * 业务 负责 人
     */
    private String businessResponseManName;
    /**
     * 业务 负责 人 电话
     */
    private String businessResponseManTel;
    /**
     * 业务 负责 人 邮箱
     */
    private String businessResponseManEmail;
    /**
     * 注册 资本
     */
    private Double regCapital;
    /**
     * 注册 地址
     */
    private String regAddr;
    /**
     * 传真
     */
    private String fax;
    /**
     * 退货 地址
     */
    private String returnedPurchaseAddr;
    /**
     * 经营 范围
     */
    private String businessRange;
    /**
     * 经营 分类
     */
    private String businessCategory;
    /**
     * 资质文件
     */
    private List<SupplierCertificatesFileVo> supplierCertificatesFileList;
    /**
     * 申请 人
     */
    private String applyMan;

    /**
     * 申请 日期
     */
    private String applyDateString;
    /**
     * 提交 意见
     */
    private String submitOpinion;
    /**
     * 备注
     */
    private String remark;
    /**
     * 首营 供应商 质量 审核 信息
     */
    private List<FirstManageSupplierQualityApproveInfDetailVo> firstManageSupplierQualityApproveInfDetailVos;

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

    public String getUnitNatureName() {
        return unitNatureName;
    }

    public void setUnitNatureName(String unitNatureName) {
        this.unitNatureName = unitNatureName;
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

    public List<SupplierCertificatesFileVo> getSupplierCertificatesFileList() {
        return supplierCertificatesFileList;
    }

    public void setSupplierCertificatesFileList(List<SupplierCertificatesFileVo> supplierCertificatesFileList) {
        this.supplierCertificatesFileList = supplierCertificatesFileList;
    }

    public String getApplyMan() {
        return applyMan;
    }

    public void setApplyMan(String applyMan) {
        this.applyMan = applyMan;
    }

    public String getApplyDateString() {
        return applyDateString;
    }

    public void setApplyDateString(String applyDateString) {
        this.applyDateString = applyDateString;
    }

    public String getSubmitOpinion() {
        return submitOpinion;
    }

    public void setSubmitOpinion(String submitOpinion) {
        this.submitOpinion = submitOpinion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FirstManageSupplierQualityApproveInfDetailVo> getFirstManageSupplierQualityApproveInfDetailVos() {
        return firstManageSupplierQualityApproveInfDetailVos;
    }

    public void setFirstManageSupplierQualityApproveInfDetailVos(List<FirstManageSupplierQualityApproveInfDetailVo> firstManageSupplierQualityApproveInfDetailVos) {
        this.firstManageSupplierQualityApproveInfDetailVos = firstManageSupplierQualityApproveInfDetailVos;
    }
}
