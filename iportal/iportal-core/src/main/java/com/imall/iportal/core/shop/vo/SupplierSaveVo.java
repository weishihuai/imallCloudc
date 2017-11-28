package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.main.entity.FileMng;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ou on 2017/7/7.
 */
public class SupplierSaveVo {


    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 供应商 档案 ID
     */
    private Long supplierDocId;
    /**
     * 供应商 名称
     */
    @NotBlank
    @Length(max = 32)
    private String supplierNm;
    /**
     * 单位 性质
     */
    @Length(max = 32)
    private String unitNature;
    /**
     * 质量 负责 人
     */
    @Length(max = 32)
    private String qualityResponseManName;
    /**
     * 法定代表人
     */
    @Length(max = 32)
    private String legalRepresentative;
    /**
     * 业务 负责 人
     */
    @Length(max = 32)
    private String businessResponseManName;
    /**
     * 业务 负责 人 电话
     */
    @Length(max = 32)
    private String businessResponseManTel;
    /**
     * 业务 负责 人 邮箱
     */
    @Length(max = 128)
    private String businessResponseManEmail;
    /**
     * 注册 资本
     */
    @Length(max = 22)
    private String regCapital;
    /**
     * 注册 地址
     */
    @Length(max = 128)
    private String regAddr;
    /**
     * 退货 地址
     */
    @Length(max = 128)
    private String returnedPurchaseAddr;
    /**
     * 经营 范围
     */
    @NotBlank
    @Length(max = 512)
    private String businessRange;
    /**
     * 经营 分类
     */
    @NotBlank
    @Length(max = 512)
    private String businessCategory;

    /**
     * 资质文件
     */
    @NotEmpty
    private List<SupplierCertificatesFileSaveVo> supplierCertificatesFileList;
    /**
     * 申请 人
     */
    @Length(max = 32)
    private String applyMan;

    /**
     * 申请 日期
     */
    @Length(max = 32)
    private String applyDateString;
    /**
     * 门店 业务 负责 人
     */
    @Length(max = 32)
    private String shopBusinessResponseMan;
    /**
     * 门店 业务 负责 人 电话
     */
    @Length(max = 32)
    private String shopBusinessResponseManTel;
    /**
     * 供应商 状态
     */
    @Length(max = 32)
    private String state;
    /**
     * 是否 首营
     */
    @NotBlank
    @Length(max = 32)
    private String isFirstCheck;
    /**
     * 提交 意见
     */
    @Length(max = 128)
    private String submitOpinion;
    /**
     * 质量 管理 体系 评价
     */
    @Length(max = 128)
    private String qualityMngSystemEvaluate;
    /**
     * 备注
     */
    @Length(max = 128)
    private String remark;
    /**
     * 传真
     */
    @Length(max = 32)
    private String fax;


    //图像集合
    private List<FileMng> fileMngs;

    public List<FileMng> getFileMngs() {
        return fileMngs;
    }

    public void setFileMngs(List<FileMng> fileMngs) {
        this.fileMngs = fileMngs;
    }

    public Long getSupplierDocId() {
        return supplierDocId;
    }

    public void setSupplierDocId(Long supplierDocId) {
        this.supplierDocId = supplierDocId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public List<SupplierCertificatesFileSaveVo> getSupplierCertificatesFileList() {
        return supplierCertificatesFileList;
    }

    public void setSupplierCertificatesFileList(List<SupplierCertificatesFileSaveVo> supplierCertificatesFileList) {
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

    public String getShopBusinessResponseMan() {
        return shopBusinessResponseMan;
    }

    public void setShopBusinessResponseMan(String shopBusinessResponseMan) {
        this.shopBusinessResponseMan = shopBusinessResponseMan;
    }

    public String getShopBusinessResponseManTel() {
        return shopBusinessResponseManTel;
    }

    public void setShopBusinessResponseManTel(String shopBusinessResponseManTel) {
        this.shopBusinessResponseManTel = shopBusinessResponseManTel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsFirstCheck() {
        return isFirstCheck;
    }

    public void setIsFirstCheck(String isFirstCheck) {
        this.isFirstCheck = isFirstCheck;
    }

    public String getSubmitOpinion() {
        return submitOpinion;
    }

    public void setSubmitOpinion(String submitOpinion) {
        this.submitOpinion = submitOpinion;
    }

    public String getQualityMngSystemEvaluate() {
        return qualityMngSystemEvaluate;
    }

    public void setQualityMngSystemEvaluate(String qualityMngSystemEvaluate) {
        this.qualityMngSystemEvaluate = qualityMngSystemEvaluate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
