package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/10.
 */
public class DrugLockPageVo {
    /**
     * 锁定 人 姓名
     */
    private String lockManName;
    /**
     * 锁定 时间
     */
    private String lockTimeString;
    /**
     *  商品 编码
     */
    private String goodsCode;
    /**
     *  商品 名称
     */
    private String goodsNm;
    /**
     *  通用 名称
     */
    private String commonNm;
    /**
     *  规格
     */
    private String spec;
    /**
     *  剂型
     */
    private String dosageForm;
    /**
     *  单位
     */
    private String unit;
    /**
     *  生产 厂商
     */
    private String produceManufacturer;
    /**
     *  产地
     */
    private String productionPlace;
    /**
     *  批准文号
     */
    private String approvalNumber;
    /**
     *  批号
     */
    private String batch;
    /**
     *  当前 库存
     */
    private Long currentStock;
    /**
     *  锁定 数量
     */
    private String lockQuantity;
    /**
     *   锁定 原因
     */
    private String lockReason;
    /**
     *   生产 时间
     */
    private String produceDateString;
    /**
     *   有效 日期
     */
    private String validDateString;
    /**
     *   货位
     */
    private String storageSpaceNm;

    public String getLockManName() {
        return lockManName;
    }

    public void setLockManName(String lockManName) {
        this.lockManName = lockManName;
    }

    public String getLockTimeString() {
        return lockTimeString;
    }

    public void setLockTimeString(String lockTimeString) {
        this.lockTimeString = lockTimeString;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
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

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public String getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(String lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
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
}
