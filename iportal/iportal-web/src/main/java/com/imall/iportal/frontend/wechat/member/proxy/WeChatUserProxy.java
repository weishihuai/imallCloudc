package com.imall.iportal.frontend.wechat.member.proxy;

/**
 * Created by lxh on 2017/8/15.
 */
public class WeChatUserProxy {
    //微信 用户 ID
    private Long id;
    //openID
    private String openId;
    //手机号
    private String mobile;
    //是否会员
    private Boolean isMember;
    //是否关注公众号
    private String isSubscribe;
    //公众号二维码ticket，只有在未关注公众号的情况下，才设置此值
    private String qrCodeTicket;
    //昵称
    private String nickName;
    //用户名称
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getMember() {
        return isMember;
    }

    public void setMember(Boolean member) {
        isMember = member;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getQrCodeTicket() {
        return qrCodeTicket;
    }

    public void setQrCodeTicket(String qrCodeTicket) {
        this.qrCodeTicket = qrCodeTicket;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
