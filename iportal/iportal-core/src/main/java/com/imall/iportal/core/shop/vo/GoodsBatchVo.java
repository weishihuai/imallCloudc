package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.GoodsBatch;

/**
 * Created by wsh on 2017/7/6.
 * 商品批号 - VO对象
 */
public class GoodsBatchVo extends GoodsBatch {
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
}
