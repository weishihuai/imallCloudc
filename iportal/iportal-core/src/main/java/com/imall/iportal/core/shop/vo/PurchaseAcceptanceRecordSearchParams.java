package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/7/31.
 */
public class PurchaseAcceptanceRecordSearchParams {
    //门店ID
    @NotNull
    private Long shopId;
    //供应商ID
    private Long supplierId;
    //验收单号
    private String acceptanceOrderNum;

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

    public String getAcceptanceOrderNum() {
        return acceptanceOrderNum;
    }

    public void setAcceptanceOrderNum(String acceptanceOrderNum) {
        this.acceptanceOrderNum = acceptanceOrderNum;
    }
}
