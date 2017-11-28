package com.imall.iportal.core.shop.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ygw on 2017/7/14.
 */
public class SalesPosOrderSaveValid {


    /** 医保 支付 金额 */
    @NotNull
    private Double medicalInsurancePaymentAmount;

    /** 支付 方式 代码 */
    @NotBlank
    @Length(max = 32)
    private String paymentWayCode;

    /** 实收 现金 金额 */
    @NotNull
    private Double realGeveCashAmount;

    public Double getMedicalInsurancePaymentAmount() {
        return medicalInsurancePaymentAmount;
    }

    public void setMedicalInsurancePaymentAmount(Double medicalInsurancePaymentAmount) {
        this.medicalInsurancePaymentAmount = medicalInsurancePaymentAmount;
    }

    public String getPaymentWayCode() {
        return paymentWayCode;
    }

    public void setPaymentWayCode(String paymentWayCode) {
        this.paymentWayCode = paymentWayCode;
    }

    public Double getRealGeveCashAmount() {
        return realGeveCashAmount;
    }

    public void setRealGeveCashAmount(Double realGeveCashAmount) {
        this.realGeveCashAmount = realGeveCashAmount;
    }
}
