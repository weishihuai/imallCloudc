package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;

/**
 * Created by lxh on 2017/7/15.
 */
public class PurchaseAcceptanceRecordItemPageVo extends PurchaseAcceptanceRecordItem {
    //采购订单ID
    private Long purchaseOrderId;
    //采购订单编号
    private String purchaseOrderNum;
    //验收单编号
    private String acceptanceOrderNum;
    //供应商名称
    private String supplierNm;
    //验收人
    private String acceptanceMan;
    //通用名称
    private String goodsCommonNm;
    //规格
    private String spec;
    //单位
    private String unit;
    //剂型
    private String dosageForm;
    //批准文号
    private String approvalNumber;
    //采购税率
    private String purchaseTaxRate;
    //产地
    private String productionPlace;

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getPurchaseOrderNum() {
        return purchaseOrderNum;
    }

    public void setPurchaseOrderNum(String purchaseOrderNum) {
        this.purchaseOrderNum = purchaseOrderNum;
    }

    public String getAcceptanceOrderNum() {
        return acceptanceOrderNum;
    }

    public void setAcceptanceOrderNum(String acceptanceOrderNum) {
        this.acceptanceOrderNum = acceptanceOrderNum;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public String getGoodsCommonNm() {
        return goodsCommonNm;
    }

    public void setGoodsCommonNm(String goodsCommonNm) {
        this.goodsCommonNm = goodsCommonNm;
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

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(String purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }
}
