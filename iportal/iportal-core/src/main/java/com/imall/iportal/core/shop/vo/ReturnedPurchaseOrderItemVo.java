package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrderItem;

/**
 * Created by lxh on 2017/7/14.
 */
public class ReturnedPurchaseOrderItemVo extends ReturnedPurchaseOrderItem{
    //商品编码
    private String goodsCode;
    //商品名称
     private String goodsNm;
    //通用名称
    private String commonNm;
    //规格
    private String spec;
    //剂型
    private String dosageForm;
    //单位
    private String unit;
    //生产厂商
    private String produceManufacturer;
    //批准文号
    private String approvalNumber;
    //产地
    private String productionPlace;
    //入库时间
    private String inStorageTimeString;

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

    public String getInStorageTimeString() {
        return inStorageTimeString;
    }

    public void setInStorageTimeString(String inStorageTimeString) {
        this.inStorageTimeString = inStorageTimeString;
    }
}
