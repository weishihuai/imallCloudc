package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/6.
 * 会员更新 - Vo对象
 */
public class MemberUpdateVo {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;

    @NotNull
    private Long id;
    /**
     * 会员姓名
     */
    @NotBlank
    @Length(max = 32)
    private String name;
    /**
     * 手机号码
     */
    @NotBlank
    @Length(max = 32)
    private String mobile;
    /**
     * 会员卡号
     */
    private String memberCardNum;
    /**
     * 性别
     */
    private String sexCode;
    /**
     * 是否医保卡
     */
    private String isMedicalInsuranceCard;
    /**
     * 职业
     */
    private String profession;
    /**
     * 生日
     */
    private String birthdayString;
    /**
     * 病史
     */
    private String diseaseHistory;
    /**
     * 常用药
     */
    private String commonlyUsedDrugs;
    /**
     * 备注
     */
    private String remark;
    /**
     * 住址
     */
    private String homeAddr;
    /**
     * 是否发卡
     */
    private String isGiveCard;
    /**
     * 卡使用状态
     */
    private String cardUseStateCode;
    /**
     * 发卡人
     */
    private String giveCardMan;
    /**
     * 发卡时间
     */
    private String giveCardTimeString;
    /**
     * 生效时间
     */
    private String effectTimeString;
    /**
     * 失效时间
     */
    private String expireTimeString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getIsMedicalInsuranceCard() {
        return isMedicalInsuranceCard;
    }

    public void setIsMedicalInsuranceCard(String isMedicalInsuranceCard) {
        this.isMedicalInsuranceCard = isMedicalInsuranceCard;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
    }

    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    public String getCommonlyUsedDrugs() {
        return commonlyUsedDrugs;
    }

    public void setCommonlyUsedDrugs(String commonlyUsedDrugs) {
        this.commonlyUsedDrugs = commonlyUsedDrugs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getIsGiveCard() {
        return isGiveCard;
    }

    public void setIsGiveCard(String isGiveCard) {
        this.isGiveCard = isGiveCard;
    }

    public String getCardUseStateCode() {
        return cardUseStateCode;
    }

    public void setCardUseStateCode(String cardUseStateCode) {
        this.cardUseStateCode = cardUseStateCode;
    }

    public String getGiveCardMan() {
        return giveCardMan;
    }

    public void setGiveCardMan(String giveCardMan) {
        this.giveCardMan = giveCardMan;
    }

    public String getGiveCardTimeString() {
        return giveCardTimeString;
    }

    public void setGiveCardTimeString(String giveCardTimeString) {
        this.giveCardTimeString = giveCardTimeString;
    }

    public String getEffectTimeString() {
        return effectTimeString;
    }

    public void setEffectTimeString(String effectTimeString) {
        this.effectTimeString = effectTimeString;
    }

    public String getExpireTimeString() {
        return expireTimeString;
    }

    public void setExpireTimeString(String expireTimeString) {
        this.expireTimeString = expireTimeString;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
