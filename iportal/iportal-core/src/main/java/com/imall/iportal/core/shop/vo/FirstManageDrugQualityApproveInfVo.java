package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/2.
 */
public class FirstManageDrugQualityApproveInfVo {

    /**
     * 采购审核人id
     */
    private Long purchaseApproveManId;
    /**
     * 采购审核时间
     */
    private String purchaseApproveTimeStr;
    /**
     * 采购审核类型代码
     */
    private String purchaseApproveStateCode;
    /**
     * 采购申请备注
     */
    private String purchaseApproveRemark;
    /**
     * 采购审核人名称
     */
    private String purchaseApproveManName;

    /**
     * 以下为质量部分,与采购一致
     */
    private Long qualityApproveManId;
    private String qualityApproveTimeStr;
    private String qualityApproveStateCode;
    private String qualityApproveRemark;
    private String qualityApproveManName;
    /**
     * 以下为企业部分,与采购一致
     */
    private Long enterpriseApproveManId;
    private String enterpriseApproveTimeStr;
    private String enterpriseApproveStateCode;
    private String enterpriseApproveRemark;
    private String enterpriseApproveManName;
    public Long getPurchaseApproveManId() {
        return purchaseApproveManId;
    }

    public void setPurchaseApproveManId(Long purchaseApproveManId) {
        this.purchaseApproveManId = purchaseApproveManId;
    }

    public String getPurchaseApproveTimeStr() {
        return purchaseApproveTimeStr;
    }

    public void setPurchaseApproveTimeStr(String purchaseApproveTimeStr) {
        this.purchaseApproveTimeStr = purchaseApproveTimeStr;
    }

    public String getPurchaseApproveStateCode() {
        return purchaseApproveStateCode;
    }

    public void setPurchaseApproveStateCode(String purchaseApproveStateCode) {
        this.purchaseApproveStateCode = purchaseApproveStateCode;
    }

    public String getPurchaseApproveRemark() {
        return purchaseApproveRemark;
    }

    public void setPurchaseApproveRemark(String purchaseApproveRemark) {
        this.purchaseApproveRemark = purchaseApproveRemark;
    }

    public Long getQualityApproveManId() {
        return qualityApproveManId;
    }

    public void setQualityApproveManId(Long qualityApproveManId) {
        this.qualityApproveManId = qualityApproveManId;
    }

    public String getQualityApproveTimeStr() {
        return qualityApproveTimeStr;
    }

    public void setQualityApproveTimeStr(String qualityApproveTimeStr) {
        this.qualityApproveTimeStr = qualityApproveTimeStr;
    }

    public String getQualityApproveStateCode() {
        return qualityApproveStateCode;
    }

    public void setQualityApproveStateCode(String qualityApproveStateCode) {
        this.qualityApproveStateCode = qualityApproveStateCode;
    }

    public String getQualityApproveRemark() {
        return qualityApproveRemark;
    }

    public void setQualityApproveRemark(String qualityApproveRemark) {
        this.qualityApproveRemark = qualityApproveRemark;
    }

    public Long getEnterpriseApproveManId() {
        return enterpriseApproveManId;
    }

    public void setEnterpriseApproveManId(Long enterpriseApproveManId) {
        this.enterpriseApproveManId = enterpriseApproveManId;
    }

    public String getEnterpriseApproveTimeStr() {
        return enterpriseApproveTimeStr;
    }

    public void setEnterpriseApproveTimeStr(String enterpriseApproveTimeStr) {
        this.enterpriseApproveTimeStr = enterpriseApproveTimeStr;
    }

    public String getEnterpriseApproveStateCode() {
        return enterpriseApproveStateCode;
    }

    public void setEnterpriseApproveStateCode(String enterpriseApproveStateCode) {
        this.enterpriseApproveStateCode = enterpriseApproveStateCode;
    }

    public String getEnterpriseApproveRemark() {
        return enterpriseApproveRemark;
    }

    public void setEnterpriseApproveRemark(String enterpriseApproveRemark) {
        this.enterpriseApproveRemark = enterpriseApproveRemark;
    }

    public String getPurchaseApproveManName() {
        return purchaseApproveManName;
    }

    public void setPurchaseApproveManName(String purchaseApproveManName) {
        this.purchaseApproveManName = purchaseApproveManName;
    }

    public String getQualityApproveManName() {
        return qualityApproveManName;
    }

    public void setQualityApproveManName(String qualityApproveManName) {
        this.qualityApproveManName = qualityApproveManName;
    }

    public String getEnterpriseApproveManName() {
        return enterpriseApproveManName;
    }

    public void setEnterpriseApproveManName(String enterpriseApproveManName) {
        this.enterpriseApproveManName = enterpriseApproveManName;
    }
}
