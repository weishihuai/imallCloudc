package com.imall.iportal.frontend.wechat.member.proxy;

/**
 * Created by lxh on 2017/8/14.
 */
public class ReceiverAddrProxy {
    //主键ID
    private Long id;
    //微信用户ID
    private Long weChatUserId;
    //收货人
    private String receiverName;
    //联系电话
    private String contactTel;
    //配送地址
    private String deliveryAddr;
    //位置名称
    private String positionName;
    //详细地址
    private String detailAddr;
    //地址纬度
    private Double addrLat;
    //地址经度
    private Double addrLng;
    //是否默认地址
    private String isDefault;
    //城市名称
    private String cityName;
    //是否在配送范围
    private boolean inDeliveryRange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeChatUserId() {
        return weChatUserId;
    }

    public void setWeChatUserId(Long weChatUserId) {
        this.weChatUserId = weChatUserId;
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

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isInDeliveryRange() {
        return inDeliveryRange;
    }

    public void setInDeliveryRange(boolean inDeliveryRange) {
        this.inDeliveryRange = inDeliveryRange;
    }
}
