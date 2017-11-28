package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.StockCheck;

/**
 * Created by wsh on 2017/7/25.
 * 盘点过账列表 VO对象
 */
public class StockPostingPageVo extends StockCheck {
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
     * 生产厂商
     */
    private String produceManufacturer;
    /**
     * 批准文号
     */
    private String approvalNumber;
    /**
     * 产地
     */
    private String productionPlace;
    /**
     * 批号
     */
    private String batch;
    /**
     * 货位名称
     */
    private String storageSpaceNm;
    /**
     * 生产日期
     */
    private String produceDateString;
    /**
     * 有效期
     */
    private String validDateString;
    /**
     * 库存金额
     */
    private Double stockPrice;

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

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
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

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
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

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }
}
