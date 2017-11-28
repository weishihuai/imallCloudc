package com.imall.iportal.frontend.wechat.member.proxy;

/**
 * Created by wsh on 2017/8/18.
 * 微信用户 相关信息Proxy
 */
public class WeChatUserInfoProxy {
    /**
     * 微信用户ID
     */
    private Long id;
    /**
     * 微信用户 昵称
     */
    private String nickName;
    /**
     * 微信ID
     */
    private String openId;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 需求单笔数
     */
    private Long demandTotal;
    /**
     * 收货地址总数
     */
    private Long receiveAddressTotal;
    /**
     * 图片路径
     */
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getDemandTotal() {
        return demandTotal;
    }

    public void setDemandTotal(Long demandTotal) {
        this.demandTotal = demandTotal;
    }

    public Long getReceiveAddressTotal() {
        return receiveAddressTotal;
    }

    public void setReceiveAddressTotal(Long receiveAddressTotal) {
        this.receiveAddressTotal = receiveAddressTotal;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
