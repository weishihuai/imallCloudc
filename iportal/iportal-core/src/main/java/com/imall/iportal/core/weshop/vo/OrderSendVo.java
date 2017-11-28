package com.imall.iportal.core.weshop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 订单发货信息
 */
public class OrderSendVo {
    /**
     * 订单 ID
     */
    @NotNull
    private Long id;

    /**
     * 微门店 ID
     */
    @NotNull
    private java.lang.Long weShopId;

    /**
     * 订单项信息
     */
    @NotEmpty
    private List<OrderItemSendVo> itemList;

    //开单人
    @NotNull
    private Long openOrderManId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public List<OrderItemSendVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemSendVo> itemList) {
        this.itemList = itemList;
    }

    public Long getOpenOrderManId() {
        return openOrderManId;
    }

    public void setOpenOrderManId(Long openOrderManId) {
        this.openOrderManId = openOrderManId;
    }
}
