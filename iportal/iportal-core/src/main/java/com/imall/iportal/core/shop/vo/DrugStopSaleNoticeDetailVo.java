package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by caidapao on 2017/8/8.
 */
public class DrugStopSaleNoticeDetailVo {
    /**
     * 制单人姓名
     */
    private String docMakerNm;
    /**
     * 复核人名称
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

    /**
     * 批次商品信息
     */
    private List<StopSaleGoodsInfVo> stopSaleGoodsInfVos;

    public List<StopSaleGoodsInfVo> getStopSaleGoodsInfVos() {
        return stopSaleGoodsInfVos;
    }

    public void setStopSaleGoodsInfVos(List<StopSaleGoodsInfVo> stopSaleGoodsInfVos) {
        this.stopSaleGoodsInfVos = stopSaleGoodsInfVos;
    }

    public String getApproveManName() {
        return approveManName;
    }

    public void setApproveManName(String approveManName) {
        this.approveManName = approveManName;
    }

    public String getDocMakerNm() {
        return docMakerNm;
    }

    public void setDocMakerNm(String docMakerNm) {
        this.docMakerNm = docMakerNm;
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
