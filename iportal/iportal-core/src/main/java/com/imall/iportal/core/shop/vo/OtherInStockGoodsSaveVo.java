package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/29.
 * 其他入库保存时 商品集合 VO对象
 */
public class OtherInStockGoodsSaveVo {
    /**
     * 商品批号
     */
    @NotNull
    @Length(max = 32)
    private String goodsBatch;
    /**
     * 商品批次ID
     */
    private Long goodsBatchId;
    /**
     * 货位ID
     */
    @NotNull
    private Long storageSpaceId;
    /**
     * 数量
     */
    @NotNull
    private Long quantity;
    /**
     * 单位价格
     */
    @NotNull
    private Double unitPrice;
    /**
     * 商品 id
     */
    @NotNull
    private Long goodsId;
    /**
     * 生产日期
     */
    private String productionDateString;
    /**
     * 有效期
     */
    private String validityString;

    public Long getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(Long goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getProductionDateString() {
        return productionDateString;
    }

    public void setProductionDateString(String productionDateString) {
        this.productionDateString = productionDateString;
    }

    public String getValidityString() {
        return validityString;
    }

    public void setValidityString(String validityString) {
        this.validityString = validityString;
    }

    public String getGoodsBatch() {
        return goodsBatch;
    }

    public void setGoodsBatch(String goodsBatch) {
        this.goodsBatch = goodsBatch;
    }
}
