package com.imall.iportal.core.weshop.vo;

/**
 * 订单项批次信息
 */
public class OrderSendOutBatchDetailVo {
    private Long id;
    
    /**
     * 批号
     */
    private String batch;

    /**
     * 数量
     */
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
