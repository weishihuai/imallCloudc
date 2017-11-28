package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by caidapao on 2017/8/8.
 */
public class StopSaleGoodsInfSaveVo {

    /**
     * 批次id
     */
    @NotNull
    private Long batchId;

    /**
     * 药品 停售 通知单 ID
     */
    private Long drugStopSaleNoticeId;
    /**
     * 商品 名称
     */
    @NotBlank
    private String goodsNm;
    /**
     * 商品 编码
     */
    @NotBlank
    private String goodsCode;
    /**
     * 通用 名称
     */
    @NotBlank
    private String commonNm;
    /**
     * 商品 拼音
     */
    @NotBlank
    private String goodsPinyin;
    /**
     * 规格
     */
    @NotBlank
    private String spec;
    /**
     * DOSAGE_FORM - 剂型
     */
    private String dosageForm;
    /**
     * UNIT - 单位
     */
    @NotBlank
    private String unit;
    /**
     * PRODUCE_MANUFACTURER - 生产厂商
     */
    @NotBlank
    private String produceManufacturer;
    /**
     * PRODUCTION_PLACE - 产地
     */
    private String productionPlace;
    /**
     * APPROVAL_NUMBER - 批准文号
     */
    private String approvalNumber;
    /**
     * BATCH - 批号
     */
    @NotBlank
    private String batch;
    /**
     * VALID_DATE - 有效期至
     */
    @NotBlank
    private String validDateStr;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDrugStopSaleNoticeId() {
        return drugStopSaleNoticeId;
    }

    public void setDrugStopSaleNoticeId(Long drugStopSaleNoticeId) {
        this.drugStopSaleNoticeId = drugStopSaleNoticeId;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getGoodsPinyin() {
        return goodsPinyin;
    }

    public void setGoodsPinyin(String goodsPinyin) {
        this.goodsPinyin = goodsPinyin;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getValidDateStr() {
        return validDateStr;
    }

    public void setValidDateStr(String validDateStr) {
        this.validDateStr = validDateStr;
    }
}
