package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.main.entity.FileMng;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lxh on 2017/8/4.
 */
public class WeShopUpdateVo {
    //主键
    @NotNull
    private Long id;
    //门店ID
    @NotNull
    private Long shopId;
    //门店名称
    @NotNull
    @Length(max = 32)
    private String shopNm;
    //门店简介
    private String shopBrief;
    //门店区域
    @NotNull
    private Long shopZone;
    //详细位置
    @NotNull
    @Length(max = 256)
    private String detailLocation;
    //门店纬度
    @NotNull
    private Double shopLat;
    //门店经度
    @NotNull
    private Double shopLng;
    //配送范围
    @NotNull
    private Long deliveryRange;
    //联系电话
    @NotNull
    @Length(max = 32)
    private String contactTel;
    //门店 承诺 送货 时间
    private Integer shopPromiseSendTime;
    //公告 信息
    private String placardInf;
    //营业 开始 时间
    @NotNull
    @Length(max = 32)
    private String sellStartTime;
    //营业 结束 时间
    @NotNull
    @Length(max = 32)
    private String sellEndTime;
    //是否 正常 营业
    @NotNull
    @Length(max = 2)
    private String isNormalSales;
    //配送 类型 代码
    @NotNull
    @Length(max = 32)
    private String deliveryTypeCode;
    //配送 金额
    private Double deliveryAmount;
    //配送 最小 订单 金额（满额必送）
    private Double deliveryMinOrderAmount;
    //店长微信
    @NotNull
    @Valid
    private FileMng shopMgrWeChatPict;
    //门店logo
    @NotNull
    @Valid
    private FileMng shopLogoPict;
    //门店图片
    private List<FileMng> shopPictList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getShopZone() {
        return shopZone;
    }

    public void setShopZone(Long shopZone) {
        this.shopZone = shopZone;
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

    public Integer getShopPromiseSendTime() {
        return shopPromiseSendTime;
    }

    public void setShopPromiseSendTime(Integer shopPromiseSendTime) {
        this.shopPromiseSendTime = shopPromiseSendTime;
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

    public FileMng getShopMgrWeChatPict() {
        return shopMgrWeChatPict;
    }

    public void setShopMgrWeChatPict(FileMng shopMgrWeChatPict) {
        this.shopMgrWeChatPict = shopMgrWeChatPict;
    }

    public FileMng getShopLogoPict() {
        return shopLogoPict;
    }

    public void setShopLogoPict(FileMng shopLogoPict) {
        this.shopLogoPict = shopLogoPict;
    }

    public List<FileMng> getShopPictList() {
        return shopPictList;
    }

    public void setShopPictList(List<FileMng> shopPictList) {
        this.shopPictList = shopPictList;
    }
}
