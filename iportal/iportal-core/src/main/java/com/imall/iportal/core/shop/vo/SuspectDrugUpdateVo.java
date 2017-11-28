package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 - 怀疑药品信息
 */
public class SuspectDrugUpdateVo {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * DRUG_INF_TYPE_CODE - 药品 信息 类型 代码
     */
    @NotBlank
    private String suspectDrugInfTypeCode;
    /**
     * APPROVAL_NUMBER - 批准文号
     */
    private String suspectApprovalNumber;
    /**
     * GOODS_NM - 商品 名称
     */
    private String suspectGoodsNm;
    /**
     * COMMON_NM - 通用 名称
     */
    private String suspectCommonNm;
    /**
     * PRODUCE_MANUFACTURER - 生产厂家
     */
    private String suspectProduceManufacturer;
    /**
     * BATCH - 生产 批号
     */
    private String suspectBatch;
    /**
     * USAGE_AND_DOSAGE - 用法用量
     */
    private String suspectUsageAndDosage;
    /**
     * DRUG_USE_TIME - 用药 起止 时间
     */
    private String suspectDrugUseTime;
    /**
     * DRUG_USE_REASON - 用药 原因
     */
    private String suspectDrugUseReason;

    public String getSuspectDrugInfTypeCode() {
        return suspectDrugInfTypeCode;
    }

    public void setSuspectDrugInfTypeCode(String suspectDrugInfTypeCode) {
        this.suspectDrugInfTypeCode = suspectDrugInfTypeCode;
    }

    public String getSuspectApprovalNumber() {
        return suspectApprovalNumber;
    }

    public void setSuspectApprovalNumber(String suspectApprovalNumber) {
        this.suspectApprovalNumber = suspectApprovalNumber;
    }

    public String getSuspectGoodsNm() {
        return suspectGoodsNm;
    }

    public void setSuspectGoodsNm(String suspectGoodsNm) {
        this.suspectGoodsNm = suspectGoodsNm;
    }

    public String getSuspectCommonNm() {
        return suspectCommonNm;
    }

    public void setSuspectCommonNm(String suspectCommonNm) {
        this.suspectCommonNm = suspectCommonNm;
    }

    public String getSuspectProduceManufacturer() {
        return suspectProduceManufacturer;
    }

    public void setSuspectProduceManufacturer(String suspectProduceManufacturer) {
        this.suspectProduceManufacturer = suspectProduceManufacturer;
    }

    public String getSuspectBatch() {
        return suspectBatch;
    }

    public void setSuspectBatch(String suspectBatch) {
        this.suspectBatch = suspectBatch;
    }

    public String getSuspectUsageAndDosage() {
        return suspectUsageAndDosage;
    }

    public void setSuspectUsageAndDosage(String suspectUsageAndDosage) {
        this.suspectUsageAndDosage = suspectUsageAndDosage;
    }

    public String getSuspectDrugUseTime() {
        return suspectDrugUseTime;
    }

    public void setSuspectDrugUseTime(String suspectDrugUseTime) {
        this.suspectDrugUseTime = suspectDrugUseTime;
    }

    public String getSuspectDrugUseReason() {
        return suspectDrugUseReason;
    }

    public void setSuspectDrugUseReason(String suspectDrugUseReason) {
        this.suspectDrugUseReason = suspectDrugUseReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
