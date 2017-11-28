package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/8.
 */
public class DrugStopSaleNoticePageVo {

    /**
     * 停售单id
     */
    private Long id;
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
    private String makeTimeStr;
    /**
     * 停售时间
     */
    private String stopSaleDateStr;
    /**
     * 质量状况
     */
    private String qualityState;
    /**
     * 停售意见
     */
    private String stopSaleSuggest;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMakeTimeStr() {
        return makeTimeStr;
    }

    public void setMakeTimeStr(String makeTimeStr) {
        this.makeTimeStr = makeTimeStr;
    }

    public String getStopSaleDateStr() {
        return stopSaleDateStr;
    }

    public void setStopSaleDateStr(String stopSaleDateStr) {
        this.stopSaleDateStr = stopSaleDateStr;
    }

    public String getQualityState() {
        return qualityState;
    }

    public void setQualityState(String qualityState) {
        this.qualityState = qualityState;
    }

    public String getStopSaleSuggest() {
        return stopSaleSuggest;
    }

    public void setStopSaleSuggest(String stopSaleSuggest) {
        this.stopSaleSuggest = stopSaleSuggest;
    }
}
