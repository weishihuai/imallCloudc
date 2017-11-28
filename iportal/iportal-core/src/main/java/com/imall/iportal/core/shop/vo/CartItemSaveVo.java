package com.imall.iportal.core.shop.vo;

/**
 * Created by ygw on 2017/3/1.
 */
public class CartItemSaveVo {

    /**
     * 物品id 批次ID
     */
    private Long objectId;

    /**
     * 数量
     */
    private Long quantity;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
