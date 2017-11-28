package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/12.
 * 商品清斗保存时商品集合 VO对象
 */
public class DrugClearBucketGoodsSaveVo {
    /**
     * 商品批次ID
     */
    @NotNull
    private Long batchId;
    /**
     * 货位ID
     */
    @NotNull
    private Long storageSpaceId;
    /**
     * 清斗数量
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
    /**
     * 是否质量合格
     */
    @NotBlank
    private String isQualityQualified;


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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getIsQualityQualified() {
        return isQualityQualified;
    }

    public void setIsQualityQualified(String isQualityQualified) {
        this.isQualityQualified = isQualityQualified;
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
