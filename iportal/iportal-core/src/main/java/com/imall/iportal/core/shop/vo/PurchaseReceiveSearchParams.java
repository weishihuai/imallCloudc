package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/7/13.
 */
public class PurchaseReceiveSearchParams {
    @NotNull
    private Long shopId;//门店ID

    private Long supplierId;//供应商ID

    private String receiveOrderNum;//收货单编号

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

    public String getReceiveOrderNum() {
        return receiveOrderNum;
    }

    public void setReceiveOrderNum(String receiveOrderNum) {
        this.receiveOrderNum = receiveOrderNum;
    }
}
