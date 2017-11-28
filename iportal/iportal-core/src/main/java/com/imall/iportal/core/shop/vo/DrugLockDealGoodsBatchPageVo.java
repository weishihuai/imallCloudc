package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/8/3.
 */
public class DrugLockDealGoodsBatchPageVo {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodsNm;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 生产厂家
     */
    private String produceManufacturer;

    /**
     * 通用名称
     */
    private String commonNm;

    /**
     * 规格
     */
    private String spec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 剂型
     */
    private String dosageForm;

    /**
     * 批号
     */
    private String batch;

    /**
     * 产地
     */
    private String productionPlace;

    /**
     * 当前库存
     */
    private Long currentStock;

    /**
     * 货位名称
     */
    private String storageSpaceNm;

    /**
     * sku Id
     */
    private Long skuId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 生产日期
     */
    private String produceDateString;

    /**
     * 有效日期
     */
    private String validDateString;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 锁定数量
     */
    private Long lockQuantity;

    /**
     * 锁定原因
     */
    private String lockReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Long getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Long lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }
}
