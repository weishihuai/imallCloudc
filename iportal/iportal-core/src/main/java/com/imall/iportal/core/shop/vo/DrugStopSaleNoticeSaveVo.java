package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by caidapao on 2017/8/8.
 */
public class DrugStopSaleNoticeSaveVo {
    /**
     * 门店id
     */
    @NotNull
    private Long shopId;
    /**
     * 制单人姓名
     */
    @NotBlank
    private String docMakerNm;
    /**
     * 复核人id
     */
    @NotNull
    private Long approveManId;
    /**
     * 停售时间
     */
    @NotBlank
    private String stopSaleDateStr;
    /**
     * 质量状况
     */
    private String qualityState;
    /**
     * 停售意见
     */
    private String stopSaleSuggest;

    @NotNull
    @Valid
    private List<StopSaleGoodsInfSaveVo> stopSaleGoodsInfSaveVoList;

    public List<StopSaleGoodsInfSaveVo> getStopSaleGoodsInfSaveVoList() {
        return stopSaleGoodsInfSaveVoList;
    }

    public void setStopSaleGoodsInfSaveVoList(List<StopSaleGoodsInfSaveVo> stopSaleGoodsInfSaveVoList) {
        this.stopSaleGoodsInfSaveVoList = stopSaleGoodsInfSaveVoList;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public String getDocMakerNm() {
        return docMakerNm;
    }

    public void setDocMakerNm(String docMakerNm) {
        this.docMakerNm = docMakerNm;
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
