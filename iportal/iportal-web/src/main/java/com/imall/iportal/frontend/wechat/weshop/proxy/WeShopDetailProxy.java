package com.imall.iportal.frontend.wechat.weshop.proxy;

import java.util.List;

/**
 * Created by wsh on 2017/8/22.
 * 微信端 -  微门店详情Proxy对象
 */
public class WeShopDetailProxy {
    /**
     * SHOP_ID - 门店 ID
     */
    private Long shopId;
    /**
     * SHOP_NM - 门店 名称
     */
    private String shopNm;
    /**
     * SHOP_BRIEF - 门店 简介
     */
    private String shopBrief;
    /**
     * SHOP_LAT - 门店 纬度
     */
    private Double shopLat;
    /**
     * SHOP_LNG - 门店 经度
     */
    private Double shopLng;
    /**
     * DELIVERY_RANGE - 配送 范围
     */
    private Long deliveryRange;
    /**
     * CONTACT_TEL - 联系 电话
     */
    private String contactTel;
    /**
     * PLACARD_INF - 公告 信息
     */
    private String placardInf;
    /**
     * SELL_START_TIME - 营业 开始 时间
     */
    private String sellStartTime;
    /**
     * SELL_END_TIME - 营业 结束 时间
     */
    private String sellEndTime;
    /**
     * IS_NORMAL_SALES - 是否 正常 营业
     */
    private String isNormalSales;
    /**
     * DELIVERY_AMOUNT - 配送 金额
     */
    private Double deliveryAmount;
    /**
     * 微店图片路径
     */
    private List<String> imgUrlList;
    /**
     * 微店Logo 图片路径
     */
    private String logoUrl;
    /**
     * 店长微信 图片路径
     */
    private String shopMgrWeiXinUrl;

    /**
     * 详细地址
     */
    private String detailLocation;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopNm() {
        return shopNm;
    }

    public void setShopNm(String shopNm) {
        this.shopNm = shopNm;
    }

    public String getShopBrief() {
        return shopBrief;
    }

    public void setShopBrief(String shopBrief) {
        this.shopBrief = shopBrief;
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

    public Long getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(Long deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getPlacardInf() {
        return placardInf;
    }

    public void setPlacardInf(String placardInf) {
        this.placardInf = placardInf;
    }

    public String getSellStartTime() {
        return sellStartTime;
    }

    public void setSellStartTime(String sellStartTime) {
        this.sellStartTime = sellStartTime;
    }

    public String getSellEndTime() {
        return sellEndTime;
    }

    public void setSellEndTime(String sellEndTime) {
        this.sellEndTime = sellEndTime;
    }

    public String getIsNormalSales() {
        return isNormalSales;
    }

    public void setIsNormalSales(String isNormalSales) {
        this.isNormalSales = isNormalSales;
    }

    public Double getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Double deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getShopMgrWeiXinUrl() {
        return shopMgrWeiXinUrl;
    }

    public void setShopMgrWeiXinUrl(String shopMgrWeiXinUrl) {
        this.shopMgrWeiXinUrl = shopMgrWeiXinUrl;
    }

    public String getDetailLocation() {
        return detailLocation;
    }

    public void setDetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }
}
