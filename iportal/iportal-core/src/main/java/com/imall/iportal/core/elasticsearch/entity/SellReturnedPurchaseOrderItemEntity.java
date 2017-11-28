package com.imall.iportal.core.elasticsearch.entity;

/**
 * Created by cx on 2017/9/15.
 */
public class SellReturnedPurchaseOrderItemEntity {
    //商品编码
    public static final String GOODS_CODING = "itemList.goodsCoding";
    //通用名称
    public static final String COMMON_NM = "itemList.commonNm";
    //商品名称
    public static final String GOODS_NM = "itemList.goodsNm";
    //通用名称首字母
    public static final String GOODS_PINYIN= "itemList.goodsPinyin";
    //条形码
    public static final String BAR_CODE = "itemList.barCode";
    private String goodsCoding;
    private String commonNm;
    private String goodsPinyin;
    private String goodsNm;
    private String barCode;


    public String getGoodsCoding() {
        return goodsCoding;
    }

    public void setGoodsCoding(String goodsCoding) {
        this.goodsCoding = goodsCoding;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getGoodsPinyin() {
        return goodsPinyin;
    }

    public void setGoodsPinyin(String goodsPinyin) {
        this.goodsPinyin = goodsPinyin;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
