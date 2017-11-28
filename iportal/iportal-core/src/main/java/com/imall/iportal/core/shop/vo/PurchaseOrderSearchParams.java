package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/7/13.
 */
public class PurchaseOrderSearchParams {
    @NotNull
    private Long shopId;//门店ID

    private Long supplierId;//供应商ID

    private String purchaseOrderType;//采购订单类型

    private String purchaseOrderState;//订单状态

    private String purchaseOrderNum;//采购订单编号

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getPurchaseOrderType() {
        return purchaseOrderType;
    }

    public void setPurchaseOrderType(String purchaseOrderType) {
        this.purchaseOrderType = purchaseOrderType;
    }

    public String getPurchaseOrderState() {
        return purchaseOrderState;
    }

    public void setPurchaseOrderState(String purchaseOrderState) {
        this.purchaseOrderState = purchaseOrderState;
    }

    public String getPurchaseOrderNum() {
        return purchaseOrderNum;
    }

    public void setPurchaseOrderNum(String purchaseOrderNum) {
        this.purchaseOrderNum = purchaseOrderNum;
    }
}
