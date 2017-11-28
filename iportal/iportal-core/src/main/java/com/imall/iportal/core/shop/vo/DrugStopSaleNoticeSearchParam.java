package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/8.
 */
public class DrugStopSaleNoticeSearchParam {

    /**
     * 停售单号
     */
    private String stopSaleNum;
    /**
     * 制单人姓名
     */
    private String docMakerNm;
    /**
     * 复核人姓名
     */
    private String approveManName;
    /**
     * 制单时间
     */
    private String fromMakeTimeStr;
    private String toMakeTimeStr;
    /**
     * 停售时间
     */
    private String toStopSaleDateStr;
    private String fromStopSaleDateStr;


    public String getStopSaleNum() {
        return stopSaleNum;
    }

    public void setStopSaleNum(String stopSaleNum) {
        this.stopSaleNum = stopSaleNum;
    }

    public String getDocMakerNm() {
        return docMakerNm;
    }

    public void setDocMakerNm(String docMakerNm) {
        this.docMakerNm = docMakerNm;
    }

    public String getApproveManName() {
        return approveManName;
    }

    public void setApproveManName(String approveManName) {
        this.approveManName = approveManName;
    }

    public String getFromMakeTimeStr() {
        return fromMakeTimeStr;
    }

    public void setFromMakeTimeStr(String fromMakeTimeStr) {
        this.fromMakeTimeStr = fromMakeTimeStr;
    }

    public String getToMakeTimeStr() {
        return toMakeTimeStr;
    }

    public void setToMakeTimeStr(String toMakeTimeStr) {
        this.toMakeTimeStr = toMakeTimeStr;
    }

    public String getToStopSaleDateStr() {
        return toStopSaleDateStr;
    }

    public void setToStopSaleDateStr(String toStopSaleDateStr) {
        this.toStopSaleDateStr = toStopSaleDateStr;
    }

    public String getFromStopSaleDateStr() {
        return fromStopSaleDateStr;
    }

    public void setFromStopSaleDateStr(String fromStopSaleDateStr) {
        this.fromStopSaleDateStr = fromStopSaleDateStr;
    }
}
