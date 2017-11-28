package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/21.
 * 库存盘点保存Vo对象
 */
public class StockCheckGoodsSaveVo {
    /**
     * 商品ID
     */
    @NotNull
    private Long goodsId;
    /**
     * sku ID
     */
    @NotNull
    private Long skuId;
    /**
     * 商品批次ID
     */
    @NotNull
    private Long goodsBatchId;

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

    public Long getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(Long goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }

}
