package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by HWJ on 2017/7/19.
 */
public class ConsultServiceSaveVo {

    /** 门店 ID */
    @NotNull
    private Long shopId;

    /** 会员 卡号 */
    @Length(max = 32)
    private String memberCardNum;

    /** 患者 姓名 */
    @NotNull
    @Length(max = 32)
    private String patientName;

    /** 年龄 */
    private Integer age;

    /** 性别 */
    @Length(max = 32)
    private String sex;

    /** 手机 */
    @Length(max = 32)
    private String mobile;

    /** 身份证 */
    @Length(max = 32)
    private String identityCard;

    /** 身高 */
    private Integer height;

    /** 体重 */
    private Double weight;

    /** 地址 */
    @Length(max = 128)
    private String addr;

    /** 肾功能 */
    @Length(max = 64)
    private String rebakFunction;

    /** 是否 怀孕 */
    @Length(max = 1)
    private String isPregnant;

    /** 过往 病史 */
    @Length(max = 128)
    private String prevMedicalHistory;

    /** 咨询 药师 */
    @Length(max = 32)
    private String consultPharmacist;

    /** 咨询 时间 */
    private java.util.Date consultTime;

    /** 问题 描述 */
    @Length(max = 128)
    private String questionDescr;

    /** 药师 解答 */
    @Length(max = 128)
    private String expertAnswer;

    /** 咨询商品 */
    @NotEmpty
    private List<Long> goodsIdList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getRebakFunction() {
        return rebakFunction;
    }

    public void setRebakFunction(String rebakFunction) {
        this.rebakFunction = rebakFunction;
    }

    public String getIsPregnant() {
        return isPregnant;
    }

    public void setIsPregnant(String isPregnant) {
        this.isPregnant = isPregnant;
    }

    public String getPrevMedicalHistory() {
        return prevMedicalHistory;
    }

    public void setPrevMedicalHistory(String prevMedicalHistory) {
        this.prevMedicalHistory = prevMedicalHistory;
    }

    public String getConsultPharmacist() {
        return consultPharmacist;
    }

    public void setConsultPharmacist(String consultPharmacist) {
        this.consultPharmacist = consultPharmacist;
    }

    public Date getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(Date consultTime) {
        this.consultTime = consultTime;
    }

    public String getQuestionDescr() {
        return questionDescr;
    }

    public void setQuestionDescr(String questionDescr) {
        this.questionDescr = questionDescr;
    }

    public String getExpertAnswer() {
        return expertAnswer;
    }

    public void setExpertAnswer(String expertAnswer) {
        this.expertAnswer = expertAnswer;
    }

    public List<Long> getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(List<Long> goodsIdList) {
        this.goodsIdList = goodsIdList;
    }
}
