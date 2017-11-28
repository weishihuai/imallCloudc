package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/8/14.
 */
public class ReceiverAddrSaveVo {
    //微信用户ID
    @NotNull
    private Long weChatUserId;
    //微信ID
    @NotBlank
    private String openId;
    //城市名称
    @NotBlank
    @Length(max = 64)
    private String cityName;
    //收货人姓名
    @NotBlank
    @Length(max = 32)
    private String receiverName;
    //收货人电话
    @NotBlank
    @Length(max = 32)
    private String contactTel;
    //配送地址
    @NotBlank
    @Length(max = 64)
    private String deliveryAddr;
    //位置名称
    @NotBlank
    @Length(max = 64)
    private String positionName;
    //详细地址
    @NotBlank
    @Length(max = 128)
    private String detailAddr;
    //地址纬度
    @NotNull
    private Double addrLat;
    //地址经度
    @NotNull
    private Double addrLng;

    public Long getWeChatUserId() {
        return weChatUserId;
    }

    public void setWeChatUserId(Long weChatUserId) {
        this.weChatUserId = weChatUserId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public Double getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(Double addrLat) {
        this.addrLat = addrLat;
    }

    public Double getAddrLng() {
        return addrLng;
    }

    public void setAddrLng(Double addrLng) {
        this.addrLng = addrLng;
    }
}
