
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;

/**
 * 实体类
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_member")
public class Member extends BaseEntity<Long> {

    public static final String SHOP_ID = "shopId";
    public static final String NAME = "name";
    public static final String MOBILE = "mobile";
    public static final String SEX_CODE = "sexCode";
    public static final String HOME_ADDR = "homeAddr";
    public static final String PROFESSION = "profession";
    public static final String BIRTHDAY = "birthday";
    public static final String DISEASE_HISTORY = "diseaseHistory";
    public static final String COMMONLY_USED_DRUGS = "commonlyUsedDrugs";
    public static final String REMARK = "remark";
    public static final String IS_GIVE_CARD = "isGiveCard";
    public static final String MEMBER_CARD_NUM = "memberCardNum";
    public static final String IS_MEDICAL_INSURANCE_CARD = "isMedicalInsuranceCard";
    public static final String CARD_USE_STATE_CODE = "cardUseStateCode";
    public static final String GIVE_CARD_MAN = "giveCardMan";
    public static final String GIVE_CARD_TIME = "giveCardTime";
    public static final String EFFECT_TIME = "effectTime";
    public static final String EXPIRE_TIME = "expireTime";
    public static final String MEMBER_STATE_CODE = "memberStateCode";

    /**
     * SHOP_ID - 门店 ID
     */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /**
     * NAME - 姓名
     */
    @Column(name = "NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String name;
    /**
     * MOBILE - 手机
     */
    @Column(name = "MOBILE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String mobile;
    /**
     * SEX_CODE - 性别 代码
     */
    @Column(name = "SEX_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String sexCode;
    /**
     * HOME_ADDR - 住址
     */
    @Column(name = "HOME_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String homeAddr;
    /**
     * PROFESSION - 职业
     */
    @Column(name = "PROFESSION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String profession;
    /**
     * BIRTHDAY - 生日
     */
    @Column(name = "BIRTHDAY", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date birthday;
    /**
     * DISEASE_HISTORY - 病史
     */
    @Column(name = "DISEASE_HISTORY", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    private String diseaseHistory;
    /**
     * COMMONLY_USED_DRUGS - 常用药
     */
    @Column(name = "COMMONLY_USED_DRUGS", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    private String commonlyUsedDrugs;
    /**
     * REMARK - 备注
     */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    private String remark;
    /**
     * IS_GIVE_CARD - 是否 发卡
     */
    @Column(name = "IS_GIVE_CARD", unique = false, nullable = false, insertable = true, updatable = true, length = 2)
    private String isGiveCard;
    /**
     * MEMBER_CARD_NUM - 会员 卡 号码
     */
    @Column(name = "MEMBER_CARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String memberCardNum;
    /**
     * IS_MEDICAL_INSURANCE_CARD - 是否 医保卡
     */
    @Column(name = "IS_MEDICAL_INSURANCE_CARD", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
    private String isMedicalInsuranceCard;
    /**
     * CARD_USE_STATE_CODE - 卡 使用 状态 代码
     */
    @Column(name = "CARD_USE_STATE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String cardUseStateCode;
    /**
     * GIVE_CARD_MAN - 发 卡 人
     */
    @Column(name = "GIVE_CARD_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String giveCardMan;
    /**
     * GIVE_CARD_TIME - 发卡 时间
     */
    @Column(name = "GIVE_CARD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date giveCardTime;
    /**
     * EFFECT_TIME - 生效 时间
     */
    @Column(name = "EFFECT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date effectTime;
    /**
     * EXPIRE_TIME - 失效 时间
     */
    @Column(name = "EXPIRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date expireTime;
    /**
     * MEMBER_STATE_CODE - 会员 状态 代码
     */
    @Column(name = "MEMBER_STATE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String memberStateCode;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setMobile(String value) {
        this.mobile = value;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setSexCode(String value) {
        this.sexCode = value;
    }

    public String getSexCode() {
        return this.sexCode;
    }

    public void setHomeAddr(String value) {
        this.homeAddr = value;
    }

    public String getHomeAddr() {
        return this.homeAddr;
    }

    public void setProfession(String value) {
        this.profession = value;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setBirthday(java.util.Date value) {
        this.birthday = value;
    }

    public java.util.Date getBirthday() {
        return this.birthday;
    }

    public void setDiseaseHistory(String value) {
        this.diseaseHistory = value;
    }

    public String getDiseaseHistory() {
        return this.diseaseHistory;
    }

    public void setCommonlyUsedDrugs(String value) {
        this.commonlyUsedDrugs = value;
    }

    public String getCommonlyUsedDrugs() {
        return this.commonlyUsedDrugs;
    }

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setIsGiveCard(String value) {
        this.isGiveCard = value;
    }

    public String getIsGiveCard() {
        return this.isGiveCard;
    }

    public void setMemberCardNum(String value) {
        this.memberCardNum = value;
    }

    public String getMemberCardNum() {
        return this.memberCardNum;
    }

    public void setIsMedicalInsuranceCard(String value) {
        this.isMedicalInsuranceCard = value;
    }

    public String getIsMedicalInsuranceCard() {
        return this.isMedicalInsuranceCard;
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

    public void setGiveCardTime(java.util.Date value) {
        this.giveCardTime = value;
    }

    public java.util.Date getGiveCardTime() {
        return this.giveCardTime;
    }

    public void setEffectTime(java.util.Date value) {
        this.effectTime = value;
    }

    public java.util.Date getEffectTime() {
        return this.effectTime;
    }

    public void setExpireTime(java.util.Date value) {
        this.expireTime = value;
    }

    public java.util.Date getExpireTime() {
        return this.expireTime;
    }

    public String getMemberStateCode() {
        return memberStateCode;
    }

    public void setMemberStateCode(String memberStateCode) {
        this.memberStateCode = memberStateCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ID", getId())
                .append("SHOP_ID", getShopId())
                .append("NAME", getName())
                .append("MOBILE", getMobile())
                .append("SEX_CODE", getSexCode())
                .append("HOME_ADDR", getHomeAddr())
                .append("PROFESSION", getProfession())
                .append("BIRTHDAY", getBirthday())
                .append("DISEASE_HISTORY", getDiseaseHistory())
                .append("COMMONLY_USED_DRUGS", getCommonlyUsedDrugs())
                .append("REMARK", getRemark())
                .append("IS_GIVE_CARD", getIsGiveCard())
                .append("MEMBER_CARD_NUM", getMemberCardNum())
                .append("IS_MEDICAL_INSURANCE_CARD", getIsMedicalInsuranceCard())
                .append("CARD_USE_STATE_CODE", getCardUseStateCode())
                .append("GIVE_CARD_MAN", getGiveCardMan())
                .append("GIVE_CARD_TIME", getGiveCardTime())
                .append("EFFECT_TIME", getEffectTime())
                .append("EXPIRE_TIME", getExpireTime())
                .append("MEMBER_STATE_CODE", getMemberStateCode())
                .append("CREATE_DATE", getCreateDate())
                .append("CREATE_BY", getCreateBy())
                .append("LAST_MODIFIED_DATE", getLastModifiedDate())
                .append("LAST_MODIFIED_BY", getLastModifiedBy())
                .append("VERSION", getVersion())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (this.getId() == null) {
            return false;
        }
        if (!(obj instanceof Member)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Member other = (Member) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    public String getBirthdayString() {
        return DateTimeUtils.convertDateToString(this.getBirthday());
    }

    public void setBirthdayString(String value) throws ParseException {
        this.setBirthday(DateTimeUtils.convertStringToDate(value));
    }

    public String getEffectTimeString() {
        return DateTimeUtils.convertDateToString(this.getEffectTime());
    }

    public void setEffectTimeString(String value) throws ParseException {
        this.setEffectTime(DateTimeUtils.convertStringToDate(value));
    }

    public String getGiveCardTimeString() {
        return DateTimeUtils.convertDateToString(this.getGiveCardTime());
    }

    public void setGiveCardTimeString(String value) throws ParseException {
        this.setGiveCardTime(DateTimeUtils.convertStringToDate(value));
    }

    public String getExpireTimeString() {
        return DateTimeUtils.convertDateToString(this.getExpireTime());
    }

    public void setExpireTimeString(String value) throws ParseException {
        this.setExpireTime(DateTimeUtils.convertStringToDate(value));
    }

    public String getCreateDateString() {
        return DateTimeUtils.convertDateToString(getCreateDate());
    }

    public void setCreateDateString(String value) throws ParseException {
        setCreateDate(DateTimeUtils.convertStringToDate(value));
    }

}

