package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.PurchaseOrderStateCodeEnum;
import com.imall.iportal.core.shop.entity.PurchaseReceiveRecord;

/**
 * Created by lxh on 2017/7/20.
 * 采购收货记录Vo
 */
public class PurchaseReceiveRecordPageVo extends PurchaseReceiveRecord {
    //供应商名称
    private String supplierNm;
    //采购订单编号
    private String purchaseOrderNum;
    //采购单订单状态
    private String purchaseOrderState;
    //采购金额
    private Double purchaseTotalAmount;

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getPurchaseOrderNum() {
        return purchaseOrderNum;
    }

    public void setPurchaseOrderNum(String purchaseOrderNum) {
        this.purchaseOrderNum = purchaseOrderNum;
    }

    public String getPurchaseOrderState() {
        return purchaseOrderState;
    }

    public void setPurchaseOrderState(String purchaseOrderState) {
        this.purchaseOrderState = purchaseOrderState;
    }

    public Double getPurchaseTotalAmount() {
        return purchaseTotalAmount;
    }

    public void setPurchaseTotalAmount(Double purchaseTotalAmount) {
        this.purchaseTotalAmount = purchaseTotalAmount;
    }

    public String getPurchaseOrderStateName() {
        return PurchaseOrderStateCodeEnum.fromCode(getPurchaseOrderState()).toName();
    }
}
