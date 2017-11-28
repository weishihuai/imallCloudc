package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.DecorationRecommend;

/**
 * Created by lxh on 2017/8/19.
 */
public class DecorationRecommendVo extends DecorationRecommend {
    //商品名称
    private String goodsNm;
    //是否处方药
    private Boolean isPrescription;
    //是否麻黄碱
    private Boolean isEphedrine;
    //零售价
    private Double retailPrice;

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public Boolean getPrescription() {
        return isPrescription;
    }

    public void setPrescription(Boolean prescription) {
        isPrescription = prescription;
    }

    public Boolean getEphedrine() {
        return isEphedrine;
    }

    public void setEphedrine(Boolean ephedrine) {
        isEphedrine = ephedrine;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }
}
