package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/25.
 * 货位移动保存时 商品集合 VO对象
 */
public class StorageSpaceMoveGoodsSaveVo {
    /**
     * 商品批次ID
     */
    @NotNull
    private Long batchId;
    /**
     * 原货位ID
     */
    @NotNull
    private Long storageSpaceId;
    /**
     * 目标货位ID
     */
    @NotNull
    private Long targetStorageSpaceId;
    /**
     * 移动数量
     */
    @NotNull
    private Long quantity;
    /**
     * 商品 id
     */
    @NotNull
    private Long goodsId;
    /**
     * skuId
     */
    @NotNull
    private Long skuId;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public Long getTargetStorageSpaceId() {
        return targetStorageSpaceId;
    }

    public void setTargetStorageSpaceId(Long targetStorageSpaceId) {
        this.targetStorageSpaceId = targetStorageSpaceId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}
