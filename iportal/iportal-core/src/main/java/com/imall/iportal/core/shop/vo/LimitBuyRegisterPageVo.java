package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;

import java.util.Date;

/**
 * Created by frt on 2017/7/22.
 */
public class LimitBuyRegisterPageVo {
    private Long id;

    /**
     * 销售 订单 编码
     */
    private String sellOrderCode;

    /**
     * 患者 名称
     */
    private String patientNm;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 身份证
     */
    private String identityCard;

    /**
     * 会员 卡 号码
     */
    private String memberCardNum;

    /**
     * 登记 日期
     */
    private java.util.Date registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellOrderCode() {
        return sellOrderCode;
    }

    public void setSellOrderCode(String sellOrderCode) {
        this.sellOrderCode = sellOrderCode;
    }

    public String getPatientNm() {
        return patientNm;
    }

    public void setPatientNm(String patientNm) {
        this.patientNm = patientNm;
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

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterDateString(){
        if(this.getRegisterDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getRegisterDate());
        }
    }
}
