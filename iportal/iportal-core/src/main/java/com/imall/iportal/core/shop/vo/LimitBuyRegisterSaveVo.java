package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by wsh on 2017/7/13.
 * 限购登记 - 保存Vo对象
 */
public class LimitBuyRegisterSaveVo {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 订单ID
     */
    @NotNull
    private Long orderId;
    /**
     * 订单编号
     */
    @NotBlank
    @Length(max = 32)
    private String sellOrderCode;
    /**
     * 会员 卡 号码
     */
    @Length(max = 32)
    private String memberCardNum;
    /**
     * 患者名称
     */
    @NotBlank
    @Length(max = 32)
    private String patientNm;
    /**
     * 身份证
     */
    @NotBlank
    @Length(max = 32)
    private String identityCard;
    /**
     * 性别代码
     */
    @Length(max = 32)
    private String sexCode;
    /**
     * 手机
     */
    @Length(max = 32)
    private String mobile;
    /**
     * 地址
     */
    @Length(max = 128)
    private String addr;

    /**
     * 登记日期
     */
    private String registerDateString;

    /**
     * 备注
     */
    @Length(max = 128)
    private String remark;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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

    public String getRegisterDateString() {
        return registerDateString;
    }

    public void setRegisterDateString(String registerDateString) {
        this.registerDateString = registerDateString;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getRegisterDate() {
        if(StringUtils.isBlank(this.getRegisterDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getRegisterDateString());
        }
    }
}
