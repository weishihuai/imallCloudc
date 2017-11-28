package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单项保存VO对象
 */
public class PurchaseOrderItemSaveVo {
    //数量
    @NotNull
    @Min(1)
    private Long purchaseQuantity;
    //采购单价
    @NotNull
    @Min(0)
    private Double purchaseUnitPrice;
    //商品ID
    @NotNull
    private Long goodsId;

    public Long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Long purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Double getPurchaseUnitPrice() {
        return purchaseUnitPrice;
    }

    public void setPurchaseUnitPrice(Double purchaseUnitPrice) {
        this.purchaseUnitPrice = purchaseUnitPrice;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
