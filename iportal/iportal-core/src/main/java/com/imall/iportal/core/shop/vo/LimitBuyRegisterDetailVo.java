package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/7/22.
 */
public class LimitBuyRegisterDetailVo {
    private Long id;

    /**
     * 订单 ID
     */
    private Long orderId;
    /**
     * 销售 订单 编码
     */
    private String sellOrderCode;

    /**
     * 会员 卡 号码
     */
    private String memberCardNum;

    /**
     * 患者 名称
     */
    private String patientNm;

    /**
     * 身份证
     */
    private String identityCard;

    /**
     * 性别 代码
     */
    private String sexCode;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 地址
     */
    private String addr;

    /**
     * 备注
     */
    private String remark;

    /**
     * 登记日期
     */
    private Date registerDate;

    private List<LimitBuyRegisterItemVo> limitBuyRegisterItemVoList;

    public String getSellOrderCode() {
        return sellOrderCode;
    }

    public void setSellOrderCode(String sellOrderCode) {
        this.sellOrderCode = sellOrderCode;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getPatientNm() {
        return patientNm;
    }

    public void setPatientNm(String patientNm) {
        this.patientNm = patientNm;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<LimitBuyRegisterItemVo> getLimitBuyRegisterItemVoList() {
        return limitBuyRegisterItemVoList;
    }

    public void setLimitBuyRegisterItemVoList(List<LimitBuyRegisterItemVo> limitBuyRegisterItemVoList) {
        this.limitBuyRegisterItemVoList = limitBuyRegisterItemVoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSexName(){
        if(StringUtils.isBlank(this.getSexCode())){
            return "";
        }else{
            return UserSexCodeEnum.fromCode(this.getSexCode()).toName();
        }
    }

    public String getRegisterDateString(){
        if(this.getRegisterDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getRegisterDate());
        }
    }
}
