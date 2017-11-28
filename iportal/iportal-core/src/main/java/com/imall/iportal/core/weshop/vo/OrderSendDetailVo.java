package com.imall.iportal.core.weshop.vo;

import java.util.List;

/**
 * 订单发货详情
 */
public class OrderSendDetailVo {
    private Long id;

    /**
     * 是否可以发货
     * 所有订单项都必须有可以发货的批次才可以发货，
     * 可以发货的批次有：处于正常状态且有库存的批次
     */
    private Boolean isCanSend;

    /**
     * 订单项发货详情
     */
    private List<OrderItemSendDetailVo> itemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCanSend() {
        return isCanSend;
    }

    public void setCanSend(Boolean canSend) {
        isCanSend = canSend;
    }

    public List<OrderItemSendDetailVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemSendDetailVo> itemList) {
        this.itemList = itemList;
    }
}
