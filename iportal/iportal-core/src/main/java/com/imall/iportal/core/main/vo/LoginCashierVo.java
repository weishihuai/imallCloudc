package com.imall.iportal.core.main.vo;

/**
 * Created by HWJ on 2017/8/26.
 */
public class LoginCashierVo {

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 合计金额
     */
    private Double addUpAmount;

    /**
     * 现金
     */
    private Double cashAmount;

    /**
     * 上次交班时间
     */
    private String succeedTimeString;

    //退货金额
    private Double returnedPurchaseAmount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAddUpAmount() {
        return addUpAmount;
    }

    public void setAddUpAmount(Double addUpAmount) {
        this.addUpAmount = addUpAmount;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getSucceedTimeString() {
        return succeedTimeString;
    }

    public void setSucceedTimeString(String succeedTimeString) {
        this.succeedTimeString = succeedTimeString;
    }

    public Double getReturnedPurchaseAmount() {
        return returnedPurchaseAmount;
    }

    public void setReturnedPurchaseAmount(Double returnedPurchaseAmount) {
        this.returnedPurchaseAmount = returnedPurchaseAmount;
    }
}
