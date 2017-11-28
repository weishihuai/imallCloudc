package com.imall.iportal.core.weshop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 订单状态更新
 */
public class OrderStateSaveVo {
    @NotNull
    private Long id;

    /**
     * 微门店 ID
     */
    @NotNull
    private Long weShopId;

    /**
     * 订单 状态 代码
     */
    @NotEmpty
    private String orderStateCode;

    /**
     * 关闭 原因
     */
    private String cancelOrderReason;

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

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getCancelOrderReason() {
        return cancelOrderReason;
    }

    public void setCancelOrderReason(String cancelOrderReason) {
        this.cancelOrderReason = cancelOrderReason;
    }
}
