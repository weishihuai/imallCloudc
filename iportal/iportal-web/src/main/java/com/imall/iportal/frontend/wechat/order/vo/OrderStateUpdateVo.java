package com.imall.iportal.frontend.wechat.order.vo;

/**
 * 订单状态更新
 */
public class OrderStateUpdateVo {
    private Long id;

    private String orderStateCode;

    private String cancelOrderReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
