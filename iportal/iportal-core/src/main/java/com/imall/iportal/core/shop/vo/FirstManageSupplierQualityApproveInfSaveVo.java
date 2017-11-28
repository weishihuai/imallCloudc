package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class FirstManageSupplierQualityApproveInfSaveVo {

    /**
     *  审核 id（非审核信息id）
     */
    private Long firstSupplierDrugQualityApproveId;
    /**
     *  门店 ID
     */
    private Long shopId;
    /**
     *   供应商 ID
     */
    private Long supplierId;


    /**
     * 采购 审核 人 ID
     */
    private Long purchaseApproveManId;
    /**
     * 质量 审核  人 ID
     */
    private Long qualityApproveManId;
    /**
     * 企业 审核  人 ID
     */
    private Long entApproveManId;


    /**
     * 采购 审核 时间
     */
    private String purchaseApproveTimeString;
    /**
     * 质量 审核 时间
     */
    private String qualityApproveTimeString;
    /**
     * 企业 审核 时间
     */
    private String entApproveTimeString;


    /**
     *采购 审核 状态
     */
    private String purchaseApproveState;
    /**
     *质量 审核 状态
     */
    private String qualityApproveState;
    /**
     *企业 审核 状态
     */
    private String entApproveState;



    /**
     * 采购 审核 备注
     */
    private String purchaseApproveRemark;
    /**
     * 质量 审核 备注
     */
    private String qualityApproveRemark;
    /**
     * 企业 审核 备注
     */
    private String entApproveRemark;

    public Long getFirstSupplierDrugQualityApproveId() {
        return firstSupplierDrugQualityApproveId;
    }

    public void setFirstSupplierDrugQualityApproveId(Long firstSupplierDrugQualityApproveId) {
        this.firstSupplierDrugQualityApproveId = firstSupplierDrugQualityApproveId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getPurchaseApproveManId() {
        return purchaseApproveManId;
    }

    public void setPurchaseApproveManId(Long purchaseApproveManId) {
        this.purchaseApproveManId = purchaseApproveManId;
    }

    public Long getQualityApproveManId() {
        return qualityApproveManId;
    }

    public void setQualityApproveManId(Long qualityApproveManId) {
        this.qualityApproveManId = qualityApproveManId;
    }

    public Long getEntApproveManId() {
        return entApproveManId;
    }

    public void setEntApproveManId(Long entApproveManId) {
        this.entApproveManId = entApproveManId;
    }

    public String getPurchaseApproveTimeString() {
        return purchaseApproveTimeString;
    }

    public void setPurchaseApproveTimeString(String purchaseApproveTimeString) {
        this.purchaseApproveTimeString = purchaseApproveTimeString;
    }

    public String getQualityApproveTimeString() {
        return qualityApproveTimeString;
    }

    public void setQualityApproveTimeString(String qualityApproveTimeString) {
        this.qualityApproveTimeString = qualityApproveTimeString;
    }

    public String getEntApproveTimeString() {
        return entApproveTimeString;
    }

    public void setEntApproveTimeString(String entApproveTimeString) {
        this.entApproveTimeString = entApproveTimeString;
    }

    public String getPurchaseApproveState() {
        return purchaseApproveState;
    }

    public void setPurchaseApproveState(String purchaseApproveState) {
        this.purchaseApproveState = purchaseApproveState;
    }

    public String getQualityApproveState() {
        return qualityApproveState;
    }

    public void setQualityApproveState(String qualityApproveState) {
        this.qualityApproveState = qualityApproveState;
    }

    public String getEntApproveState() {
        return entApproveState;
    }

    public void setEntApproveState(String entApproveState) {
        this.entApproveState = entApproveState;
    }

    public String getPurchaseApproveRemark() {
        return purchaseApproveRemark;
    }

    public void setPurchaseApproveRemark(String purchaseApproveRemark) {
        this.purchaseApproveRemark = purchaseApproveRemark;
    }

    public String getQualityApproveRemark() {
        return qualityApproveRemark;
    }

    public void setQualityApproveRemark(String qualityApproveRemark) {
        this.qualityApproveRemark = qualityApproveRemark;
    }

    public String getEntApproveRemark() {
        return entApproveRemark;
    }

    public void setEntApproveRemark(String entApproveRemark) {
        this.entApproveRemark = entApproveRemark;
    }
}
