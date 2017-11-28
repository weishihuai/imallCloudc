package com.imall.iportal.core.weshop.vo;

/**
 * 批次信息
 */
public class GoodsBatchDetailVo {
    private Long id;

    private Long skuId;

    /**
     * 批号
     */
    private String batch;

    /**
     * 当前 库存
     */
    private Long currentStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }
}
