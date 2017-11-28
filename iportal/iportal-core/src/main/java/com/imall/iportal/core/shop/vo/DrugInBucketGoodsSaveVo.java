package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/12.
 * 商品装斗保存时 商品集合 VO对象
 */
public class DrugInBucketGoodsSaveVo {
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
     * 装斗数量
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


    /**
     *批号
     */
    private String batch;
    /**
     * 供应商id
     */
    private java.lang.Long supplierId;
    /**
     * 采购 价格
     */
    private java.lang.Double purchasePrice;
    /**
     * 生产 日期
     */
    private String produceDateString;
    /**
     * 批次 状态
     */
    private String batchState;
    /**
     * 当前 库存
     */
    private java.lang.Long currentStock;
    /**
     * 有效期
     */
    private String validDateString;


    public String getValidDateString() {
        return validDateString;
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }

    public String getBatchState() {
        return batchState;
    }

    public void setBatchState(String batchState) {
        this.batchState = batchState;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public String getProduceDateString() {
        return produceDateString;
    }

    public void setProduceDateString(String produceDateString) {
        this.produceDateString = produceDateString;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public String getIsQualityQualified() {
        return isQualityQualified;
    }

    public void setIsQualityQualified(String isQualityQualified) {
        this.isQualityQualified = isQualityQualified;
    }
}
