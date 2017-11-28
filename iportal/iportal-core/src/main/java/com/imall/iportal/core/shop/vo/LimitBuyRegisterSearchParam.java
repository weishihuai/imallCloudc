package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by wsh on 2017/7/13.
 * 限购登记搜索参数
 */
public class LimitBuyRegisterSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 订单编号
     */
    private String sellOrderCode;
    /**
     * 患者名称
     */
    private String patientNm;
    /**
     * 登记日期(开始时间)
     */
    private String registerStartDateString;
    /**
     * 登记日期(结束时间)
     */
    private String registerEndDateString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public String getRegisterStartDateString() {
        return registerStartDateString;
    }

    public void setRegisterStartDateString(String registerStartDateString) {
        this.registerStartDateString = registerStartDateString;
    }

    public String getRegisterEndDateString() {
        return registerEndDateString;
    }

    public void setRegisterEndDateString(String registerEndDateString) {
        this.registerEndDateString = registerEndDateString;
    }

    public Date getRegisterStartDate(){
        if(StringUtils.isBlank(this.getRegisterStartDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getRegisterStartDateString());
        }
    }

    public Date getRegisterEndDate(){
        if(StringUtils.isBlank(this.getRegisterEndDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getRegisterEndDateString());
        }
    }
}
