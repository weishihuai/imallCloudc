package com.imall.iportal.frontend.wechat.goods.proxy;

/**
 * Created by lxh on 2017/8/12.
 */
public class GoodsPageProxy {

    private Long id;
    /**
     * 通用名称
     */
    private String commonNm;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 规格
     */
    private String spec;

    /**
     * 零售价
     */
    private Double retailPrice;

    /**
     * 会员价
     */
    private Double memberPrice;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 是否处方药
     */
    private String isPrescriptionDrugs;

    /**
     * 是否会员
     */
    private String isMember;

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

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
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
