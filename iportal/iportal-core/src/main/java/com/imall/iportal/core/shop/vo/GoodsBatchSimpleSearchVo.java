package com.imall.iportal.core.shop.vo;

/**
 * Created by lxh on 2017/7/22.
 */
public class GoodsBatchSimpleSearchVo {
    /**
     *批号
     */
    private String batch;
    /**
     *商品id
     */
    private Long goodsId;
    /**
     * 货位ID
     */
    private java.lang.Long storageSpaceId;
    /**
     * 供应商id
     */
    private java.lang.Long supplierId;
    /**
     * 采购 价格
     */
    private java.lang.Double purchasePrice;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
