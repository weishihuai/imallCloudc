package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.DrugInBucket;

/**
 * Created by wsh on 2017/7/12.
 * 商品装斗 VO对象
 */
public class DrugInBucketVo extends DrugInBucket {
    /**
     * 审核人姓名
     */
    private String approveManNm;

    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsNm;
    /**
     * 通用名称
     */
    private String commonNm;
    /**
     * 规格
     */
    private String spec;
    /**
     * 生产产商
     */
    private String produceManufacturer;
    /**
     * 产地
     */
    private String productionPlace;
    /**
     * 批次
     */
    private String batch;
    /**
     * 生产日期
     */
    private String produceDateString;
    /**
     * 有效期
     */
    private String validDateString;
    /**
     * 货位名称
     */
    private String storageSpaceNm;
    /**
     * 单位
     */
    private String unit;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 批准文号
     */
    private String approvalNumber;
    /**
     * 装斗时间(String)
     */
    private String inBucketTimeString;

    public String getApproveManNm() {
        return approveManNm;
    }

    public void setApproveManNm(String approveManNm) {
        this.approveManNm = approveManNm;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getProduceDateString() {
        return produceDateString;
    }

    public void setProduceDateString(String produceDateString) {
        this.produceDateString = produceDateString;
    }

    public String getValidDateString() {
        return validDateString;
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    @Override
    public String getInBucketTimeString() {
        return inBucketTimeString;
    }

    @Override
    public void setInBucketTimeString(String inBucketTimeString) {
        this.inBucketTimeString = inBucketTimeString;
    }
}
