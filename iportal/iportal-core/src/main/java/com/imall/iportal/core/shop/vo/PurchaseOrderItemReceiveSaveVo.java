package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单收货 VO对象
 */
public class PurchaseOrderItemReceiveSaveVo {
    /**
     * 采购订单项ID
     */
    private Long id;
    /**
     * 收货数量
     */
    private Long receiveQuantity;
    /**
     * 拒绝收货原因
     */
    private String denyReceiveReason;

    public Long getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(Long receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public String getDenyReceiveReason() {
        return denyReceiveReason;
    }

    public void setDenyReceiveReason(String denyReceiveReason) {
        this.denyReceiveReason = denyReceiveReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
