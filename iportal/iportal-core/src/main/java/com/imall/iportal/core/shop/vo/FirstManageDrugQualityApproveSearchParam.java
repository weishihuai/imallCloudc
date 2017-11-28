package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/2.
 */
public class FirstManageDrugQualityApproveSearchParam {

    /**
     * 审核状态代码
     */
    private String approveStateCode;
    /**
     * 拼音/商品名称/通用名称/商品编码
     */
    private String keyWords;
    /**
     * 审核时间
     */
    private String fromDateString;
    private String toDateString;

    public String getApproveStateCode() {
        return approveStateCode;
    }

    public void setApproveStateCode(String approveStateCode) {
        this.approveStateCode = approveStateCode;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getFromDateString() {
        return fromDateString;
    }

    public void setFromDateString(String fromDateString) {
        this.fromDateString = fromDateString;
    }

    public String getToDateString() {
        return toDateString;
    }

    public void setToDateString(String toDateString) {
        this.toDateString = toDateString;
    }
}
