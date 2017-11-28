package com.imall.iportal.core.platform.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ou on 2017/7/7.
 */
public class SupplierDocUpdateVo {

    /**
     * 供应商 id
     */
    @NotNull
    private Long id;
    /**
     * 供应商 名称
     */
    @NotBlank
    @Length(max = 32)
    private String supplierNm;
    /**
     * 单位 性质
     */
    private String unitNature;
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
    @NotBlank
    private String businessResponseManTel;
    /**
     * 业务 负责 人 邮箱
     */
    private String businessResponseManEmail;
    /**
     * 注册 资本
     */
    private String regCapital;
    /**
     * 注册 地址
     */
    private String regAddr;
    /**
     * 退货 地址
     */
    private String returnedPurchaseAddr;
    /**
     * 经营 范围
     */
    @NotBlank
    private String businessRange;
    /**
     * 经营 分类
     */
    @NotBlank
    private String businessCategory;
    /**
     * 资质文件
     */
    @NotEmpty
    private List<SupplierDocCertificatesFileSaveVo> supplierDocCertificatesFileList;
    /**
     * 传真
     */
    private String fax;


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

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
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

    public List<SupplierDocCertificatesFileSaveVo> getSupplierDocCertificatesFileList() {
        return supplierDocCertificatesFileList;
    }

    public void setSupplierDocCertificatesFileList(List<SupplierDocCertificatesFileSaveVo> supplierDocCertificatesFileList) {
        this.supplierDocCertificatesFileList = supplierDocCertificatesFileList;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
