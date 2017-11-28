package com.imall.iportal.core.weshop.vo;

/**
 * Created by wsh on 2017/8/16.
 * 微店粉丝 列表对象
 */
public class FansPageVo {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * SHOP_ID - 门店 ID
     */
    private Long shopId;
    /**
     * WE_CHAT_USER_ID - 微信 用户 ID
     */
    private Long weChatUserId;
    /**
     * FANS_NAME - 粉丝 姓名
     */
    private String fansName;
    /**
     * MOBILE - 手机
     */
    private String mobile;
    /**
     * WECHAT_ID - 微信 ID
     */
    private String openId;
    /**
     * WECHAT_NAME - 微信 称昵
     */
    private String nickName;
    /**
     * FANS_SOURCE_CODE - 粉丝 来源 代码
     */
    private String fansSourceCode;
    /**
     * IS_MEMBER - 是否 会员
     */
    private String isMember;
    /**
     * MEMBER_ID - 会员 ID
     */
    private Long memberId;
    /**
     * BUY_TIMES - 购买 次数
     */
    private Long buyTimes;
    /**
     * SEX_CODE - 性别 代码
     */
    private String sexCode;
    /**
     * MEMBER_CARD_NUM - 会员 卡 号码
     */
    private String memberCardNum;
    /**
     * HOME_ADDR - 住址
     */
    private String homeAddr;
    /**
     * 备注
     */
    private String remark;

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

    public Long getWeChatUserId() {
        return weChatUserId;
    }

    public void setWeChatUserId(Long weChatUserId) {
        this.weChatUserId = weChatUserId;
    }

    public String getFansName() {
        return fansName;
    }

    public void setFansName(String fansName) {
        this.fansName = fansName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFansSourceCode() {
        return fansSourceCode;
    }

    public void setFansSourceCode(String fansSourceCode) {
        this.fansSourceCode = fansSourceCode;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBuyTimes() {
        return buyTimes;
    }

    public void setBuyTimes(Long buyTimes) {
        this.buyTimes = buyTimes;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
