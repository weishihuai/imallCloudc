package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class FirstManageSupplierQualityApproveInfVo {
    /**
     *  审核 id（非审核信息id）
     */
    private Long id;
    /**
     * shop id
     */
    private Long shopId;
    /**
     * supplierId
     */
    private Long supplierId;


    /**
     *  采购 id
     */
    private Long purchaseId;
    /**
     * 质量 id
     */
    private Long qualityId;
    /**
     * 企业 id
     */
    private Long entId;


    /**
     * 采购 审核 人
     */
    private String purchaseApproveMan;
    /**
     * 质量 审核 人
     */
    private String qualityApproveMan;
    /**
     * 企业 审核 人
     */
    private String entApproveMan;


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
     * 采购 审核 类型
     */
    private String purchaseApproveType;
    /**
     * 质量 审核 类型
     */
    private String qualityApproveType;
    /**
     *  企业 审核 类型
     */
    private String entApproveType;



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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getQualityId() {
        return qualityId;
    }

    public void setQualityId(Long qualityId) {
        this.qualityId = qualityId;
    }

    public Long getEntId() {
        return entId;
    }

    public void setEntId(Long entId) {
        this.entId = entId;
    }

    public String getPurchaseApproveMan() {
        return purchaseApproveMan;
    }

    public void setPurchaseApproveMan(String purchaseApproveMan) {
        this.purchaseApproveMan = purchaseApproveMan;
    }

    public String getQualityApproveMan() {
        return qualityApproveMan;
    }

    public void setQualityApproveMan(String qualityApproveMan) {
        this.qualityApproveMan = qualityApproveMan;
    }

    public String getEntApproveMan() {
        return entApproveMan;
    }

    public void setEntApproveMan(String entApproveMan) {
        this.entApproveMan = entApproveMan;
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

    public String getPurchaseApproveType() {
        return purchaseApproveType;
    }

    public void setPurchaseApproveType(String purchaseApproveType) {
        this.purchaseApproveType = purchaseApproveType;
    }

    public String getQualityApproveType() {
        return qualityApproveType;
    }

    public void setQualityApproveType(String qualityApproveType) {
        this.qualityApproveType = qualityApproveType;
    }

    public String getEntApproveType() {
        return entApproveType;
    }

    public void setEntApproveType(String entApproveType) {
        this.entApproveType = entApproveType;
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
