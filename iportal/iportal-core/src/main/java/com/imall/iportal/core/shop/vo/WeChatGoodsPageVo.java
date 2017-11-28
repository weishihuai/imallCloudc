package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/7/6.
 * 微信端商品列表搜索参数
 */
public class WeChatGoodsPageVo {


    /**
     * 商品id
     */
    private Long id;

    /**
     * skuId
     */
    private Long skuId;
    /**
     * 通用名称
     */
    private String commonNm;

    /**
     * 规格
     */
    private String spec;

    /**
     * 零售价
     */
    private Double retailPrice;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 是否处方药
     */
    private String isPrescriptionDrugs;

    /**
     * 会员价
     */
    private Double memberPrice;

    //商品名称
    private String goodsNm;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsPrescriptionDrugs() {
        return isPrescriptionDrugs;
    }

    public void setIsPrescriptionDrugs(String isPrescriptionDrugs) {
        this.isPrescriptionDrugs = isPrescriptionDrugs;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }
}
