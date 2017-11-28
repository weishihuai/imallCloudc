package com.imall.iportal.core.shop.vo;

/**
 * 商品批次库存信息
 * Created by HWJ on 2017/7/13.
 */
public class GoodsBatchStockInfo {

    /**
     * 当前库存
     */
    public Long currentStock;

    /**
     * 库存金额
     */
    public Double totalAmount;

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
