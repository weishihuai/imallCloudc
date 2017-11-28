package com.imall.iportal.frontend.wechat.index.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxh on 2017/8/16.
 */
public class IndexShopProxy {
    //主键ID
    private Long weShopId;
    //门店ID
    private Long shopId;
    //是否可以切换门店
    private Boolean enableSwitchShop;
    //门店名称
    private String shopNm;
    //联系电话
    private String contactTel;
    //门店LOGO
    private String logoUrl;
    //店长微信二维码
    private String shopMgrWeChatUrl;
    //配送类型
    private String deliveryTypeCode;
    //配送费
    private Double deliveryAmount;
    //最下订单金额
    private Double deliveryMinOrderAmount;
    //是否正常营业
    private String isNormalSales;
    //营业开始时间
    private String sellStartTime;
    //营业结束时间
    private String sellEndTime;
    //配送范围
    private Long deliveryRange;
    //门店纬度
    private Double shopLat;
    //门店经度
    private Double shopLng;
    //详细位置
    private String detailLocation;
    //销售分类
    private List<SalesCategoryProxy> salesCategoryList = new ArrayList<>();

    public IndexShopProxy() {
    }

    public IndexShopProxy(boolean enableSwitchShop){
        this.enableSwitchShop = enableSwitchShop;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Boolean getEnableSwitchShop() {
        return enableSwitchShop;
    }

    public void setEnableSwitchShop(Boolean enableSwitchShop) {
        this.enableSwitchShop = enableSwitchShop;
    }

    public String getShopNm() {
        return shopNm;
    }

    public void setShopNm(String shopNm) {
        this.shopNm = shopNm;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getShopMgrWeChatUrl() {
        return shopMgrWeChatUrl;
    }

    public void setShopMgrWeChatUrl(String shopMgrWeChatUrl) {
        this.shopMgrWeChatUrl = shopMgrWeChatUrl;
    }

    public String getDeliveryTypeCode() {
        return deliveryTypeCode;
    }

    public void setDeliveryTypeCode(String deliveryTypeCode) {
        this.deliveryTypeCode = deliveryTypeCode;
    }

    public Double getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Double deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public Double getDeliveryMinOrderAmount() {
        return deliveryMinOrderAmount;
    }

    public void setDeliveryMinOrderAmount(Double deliveryMinOrderAmount) {
        this.deliveryMinOrderAmount = deliveryMinOrderAmount;
    }

    public String getIsNormalSales() {
        return isNormalSales;
    }

    public void setIsNormalSales(String isNormalSales) {
        this.isNormalSales = isNormalSales;
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

    public Long getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(Long deliveryRange) {
        this.deliveryRange = deliveryRange;
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

    public String getDetailLocation() {
        return detailLocation;
    }

    public void setDetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }

    public List<SalesCategoryProxy> getSalesCategoryList() {
        return salesCategoryList;
    }

    public void setSalesCategoryList(List<SalesCategoryProxy> salesCategoryList) {
        this.salesCategoryList = salesCategoryList;
    }
}
