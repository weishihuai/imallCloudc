package com.imall.iportal.core.shop.valid;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ygw on 2017/7/17.
 */
public class NormalOrderPaySaveValid {

    //订单Id
    @NotNull
    private Long orderId;

    //医保支持的金额
    @NotNull
    private Double medicalInsurancePaymentAmount;

    //支付方式
    @NotBlank
    private String paymentWayCode;

    //实收 现金 金额
    @NotNull
    private Double realGeveCashAmount;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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
