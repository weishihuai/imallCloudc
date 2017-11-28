package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 - 并用药品信息
 */
public class BlendDrugDetailVo {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * DRUG_INF_TYPE_CODE - 药品 信息 类型 代码
     */
    private String blendDrugInfTypeCode;
    /**
     * APPROVAL_NUMBER - 批准文号
     */
    private String blendApprovalNumber;
    /**
     * GOODS_NM - 商品 名称
     */
    private String blendGoodsNm;
    /**
     * COMMON_NM - 通用 名称
     */
    private String blendCommonNm;
    /**
     * PRODUCE_MANUFACTURER - 生产厂家
     */
    private String blendProduceManufacturer;
    /**
     * BATCH - 生产 批号
     */
    private String blendBatch;
    /**
     * USAGE_AND_DOSAGE - 用法用量
     */
    private String blendUsageAndDosage;
    /**
     * DRUG_USE_TIME - 用药 起止 时间
     */
    private String blendDrugUseTime;
    /**
     * DRUG_USE_REASON - 用药 原因
     */
    private String blendDrugUseReason;

    public String getBlendDrugInfTypeCode() {
        return blendDrugInfTypeCode;
    }

    public void setBlendDrugInfTypeCode(String blendDrugInfTypeCode) {
        this.blendDrugInfTypeCode = blendDrugInfTypeCode;
    }

    public String getBlendApprovalNumber() {
        return blendApprovalNumber;
    }

    public void setBlendApprovalNumber(String blendApprovalNumber) {
        this.blendApprovalNumber = blendApprovalNumber;
    }

    public String getBlendGoodsNm() {
        return blendGoodsNm;
    }

    public void setBlendGoodsNm(String blendGoodsNm) {
        this.blendGoodsNm = blendGoodsNm;
    }

    public String getBlendCommonNm() {
        return blendCommonNm;
    }

    public void setBlendCommonNm(String blendCommonNm) {
        this.blendCommonNm = blendCommonNm;
    }

    public String getBlendProduceManufacturer() {
        return blendProduceManufacturer;
    }

    public void setBlendProduceManufacturer(String blendProduceManufacturer) {
        this.blendProduceManufacturer = blendProduceManufacturer;
    }

    public String getBlendBatch() {
        return blendBatch;
    }

    public void setBlendBatch(String blendBatch) {
        this.blendBatch = blendBatch;
    }

    public String getBlendUsageAndDosage() {
        return blendUsageAndDosage;
    }

    public void setBlendUsageAndDosage(String blendUsageAndDosage) {
        this.blendUsageAndDosage = blendUsageAndDosage;
    }

    public String getBlendDrugUseTime() {
        return blendDrugUseTime;
    }

    public void setBlendDrugUseTime(String blendDrugUseTime) {
        this.blendDrugUseTime = blendDrugUseTime;
    }

    public String getBlendDrugUseReason() {
        return blendDrugUseReason;
    }

    public void setBlendDrugUseReason(String blendDrugUseReason) {
        this.blendDrugUseReason = blendDrugUseReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
