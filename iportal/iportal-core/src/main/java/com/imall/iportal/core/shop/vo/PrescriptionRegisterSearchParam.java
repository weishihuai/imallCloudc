package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/8/1.
 */
public class PrescriptionRegisterSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 处方 登记 状态
     */
    private String prescriptionRegisterState;

    /**
     * 处方 销售 订单 编码
     */
    private String prescriptionSellOrderCode;

    /**
     * 患者 名称
     */
    private String patientNm;

    /**
     * 处方 日期
     */
    private String startPrescriptionDateString;

    /**
     * 处方 日期
     */
    private String endPrescriptionDateString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPrescriptionRegisterState() {
        return prescriptionRegisterState;
    }

    public void setPrescriptionRegisterState(String prescriptionRegisterState) {
        this.prescriptionRegisterState = prescriptionRegisterState;
    }

    public String getPrescriptionSellOrderCode() {
        return prescriptionSellOrderCode;
    }

    public void setPrescriptionSellOrderCode(String prescriptionSellOrderCode) {
        this.prescriptionSellOrderCode = prescriptionSellOrderCode;
    }

    public String getPatientNm() {
        return patientNm;
    }

    public void setPatientNm(String patientNm) {
        this.patientNm = patientNm;
    }

    public String getStartPrescriptionDateString() {
        return startPrescriptionDateString;
    }

    public void setStartPrescriptionDateString(String startPrescriptionDateString) {
        this.startPrescriptionDateString = startPrescriptionDateString;
    }

    public String getEndPrescriptionDateString() {
        return endPrescriptionDateString;
    }

    public void setEndPrescriptionDateString(String endPrescriptionDateString) {
        this.endPrescriptionDateString = endPrescriptionDateString;
    }

    public Date getStartPrescriptionDate(){
        if(StringUtils.isBlank(this.getStartPrescriptionDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getStartPrescriptionDateString());
        }
    }

    public Date getEndPrescriptionDate(){
        if(StringUtils.isBlank(this.getEndPrescriptionDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getEndPrescriptionDateString());
        }
    }
}
