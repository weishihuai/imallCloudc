package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/7/14.
 * 退货单搜索参数
 */
public class ReturnedPurchaseOrderSearchParams {
    //门店ID
    @NotNull
    private Long shopId;
    //供应商ID
    private Long supplierId;
    //退货单号
    private String returnedPurchaseOrderNum;
    //是否已结款
    private String isPayed;
    //退货类型
    private String returnedPurchaseType;

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

    public String getReturnedPurchaseOrderNum() {
        return returnedPurchaseOrderNum;
    }

    public void setReturnedPurchaseOrderNum(String returnedPurchaseOrderNum) {
        this.returnedPurchaseOrderNum = returnedPurchaseOrderNum;
    }

    public String getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(String isPayed) {
        this.isPayed = isPayed;
    }

    public String getReturnedPurchaseType() {
        return returnedPurchaseType;
    }

    public void setReturnedPurchaseType(String returnedPurchaseType) {
        this.returnedPurchaseType = returnedPurchaseType;
    }
}
