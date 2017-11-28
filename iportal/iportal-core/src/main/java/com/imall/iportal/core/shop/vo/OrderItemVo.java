package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.OrderItem;

/**
 * Created by ygw on 2017/7/27.
 */
public class OrderItemVo extends OrderItem {

    private String batch;

    private String sellerName;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
