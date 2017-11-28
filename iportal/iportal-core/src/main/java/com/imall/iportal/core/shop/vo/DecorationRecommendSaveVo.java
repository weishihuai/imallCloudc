package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/8/19.
 */
public class DecorationRecommendSaveVo {
    //商品ID
    @NotNull
    private Long goodsId;
    //sku id
    @NotNull
    private Long skuId;
    //分组ID
    @NotNull
    private Long decorationGroupId;
    //排序
    @NotNull
    private Long displayPosition;

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

    public Long getDecorationGroupId() {
        return decorationGroupId;
    }

    public void setDecorationGroupId(Long decorationGroupId) {
        this.decorationGroupId = decorationGroupId;
    }

    public Long getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(Long displayPosition) {
        this.displayPosition = displayPosition;
    }
}
