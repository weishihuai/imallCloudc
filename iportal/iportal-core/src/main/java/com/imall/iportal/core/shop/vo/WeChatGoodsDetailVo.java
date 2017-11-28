package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by caidapao on 2017/8/15.
 */
public class WeChatGoodsDetailVo {
    /**
     * 商品id
     */
    private Long id;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 图片路径
     */
    private List<String> imgUrlList;

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
     * 是否处方药
     */
    private String isPrescriptionDrugs;

    /**
     * 说明书
     */
    private String instructions;

    /**
     * 是否在营业时间
     */
    private String isSellTime;

    /**
     * 门店位置
     */
    private String shopNm;

    /**
     * 详细地址
     */
    private String detailLocation;

    /**
     * 门店 纬度
     */
    private Double shopLat;

    /**
     * 门店 经度
     */
    private Double shopLng;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 市场价
     */
    private Double marketPrice;

    //微门店ID
    private Long weShopId;

    //商品名称
    private String goodsNm;

    //是否正常营业
    private boolean isNormalSales;

    //生产厂商
    private String produceManufacturer;

    private Long shopId;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
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

    public String getIsPrescriptionDrugs() {
        return isPrescriptionDrugs;
    }

    public void setIsPrescriptionDrugs(String isPrescriptionDrugs) {
        this.isPrescriptionDrugs = isPrescriptionDrugs;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIsSellTime() {
        return isSellTime;
    }

    public void setIsSellTime(String isSellTime) {
        this.isSellTime = isSellTime;
    }

    public String getShopNm() {
        return shopNm;
    }

    public void setShopNm(String shopNm) {
        this.shopNm = shopNm;
    }

    public String getDetailLocation() {
        return detailLocation;
    }

    public void setDetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }

    public Double getShopLat() {
        return shopLat;
    }

    public void setShopLat(Double shopLat) {
        this.shopLat = shopLat;
    }

    public Double getShopLng() {
        return shopLng;
    }

    public void setShopLng(Double shopLng) {
        this.shopLng = shopLng;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public boolean isNormalSales() {
        return isNormalSales;
    }

    public void setNormalSales(boolean normalSales) {
        isNormalSales = normalSales;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
