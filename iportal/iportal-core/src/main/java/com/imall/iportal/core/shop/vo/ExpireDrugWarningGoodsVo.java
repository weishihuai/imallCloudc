package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.GoodsBatch;

/**
 * Created by wsh on 2017/7/14.
 * 过期药品预警 - VO对象
 */
public class ExpireDrugWarningGoodsVo extends GoodsBatch {
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
     * 货位名称
     */
    private String storageSpaceNm;
    /**
     * 产地
     */
    private String productionPlace;
    /**
     * 供应商名称
     */
    private String supplierNm;
    /**
     * 库存金额
     */
    private Double stockMoney;
    /**
     * 生产日期
     */
    private String produceDateString;
    /**
     * 有效期
     */
    private String validDateString;

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

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public Double getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(Double stockMoney) {
        this.stockMoney = stockMoney;
    }

    @Override
    public String getProduceDateString() {
        return produceDateString;
    }

    @Override
    public void setProduceDateString(String produceDateString) {
        this.produceDateString = produceDateString;
    }

    @Override
    public String getValidDateString() {
        return validDateString;
    }

    @Override
    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }
}
