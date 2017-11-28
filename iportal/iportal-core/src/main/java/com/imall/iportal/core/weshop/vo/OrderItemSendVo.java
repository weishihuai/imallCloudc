package com.imall.iportal.core.weshop.vo;

import javax.validation.constraints.NotNull;

/**
 * 订单项发货信息
 */
public class OrderItemSendVo {
    /**
     * 订货单项 ID
     */
    @NotNull
    private Long id;

    /**
     * 批次 ID
     */
    @NotNull
    private Long batchId;

    /**
     * 发货数量
     */
    @NotNull
    private Long sendQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSendQuantity() {
        return sendQuantity;
    }

    public void setSendQuantity(Long sendQuantity) {
        this.sendQuantity = sendQuantity;
    }
}
