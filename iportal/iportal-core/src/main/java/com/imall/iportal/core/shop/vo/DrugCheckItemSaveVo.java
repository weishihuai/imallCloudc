package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCheckItemSaveVo {
    /**
     * 商品 ID
     */
    @NotNull
    private Long goodsId;

    /**
     * 商品 批次 ID
     */
    @NotNull
    private Long goodsBatchId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(Long goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }
}
