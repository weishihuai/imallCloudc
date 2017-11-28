package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by caidapao on 2017/8/8.
 */
public class ReleaseSaleNoticeSaveVo {
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
    @NotNull
    private String releaseDateStr;
    /**
     * 质量状况
     */
    private String qualityState;
    /**
     * 停售意见
     */
    private String releaseSuggest;

    @NotNull
    @Valid
    private List<ReleaseGoodsInfSaveVo> releaseGoodsInfSaveVos;

    public List<ReleaseGoodsInfSaveVo> getReleaseGoodsInfSaveVos() {
        return releaseGoodsInfSaveVos;
    }

    public void setReleaseGoodsInfSaveVos(List<ReleaseGoodsInfSaveVo> releaseGoodsInfSaveVos) {
        this.releaseGoodsInfSaveVos = releaseGoodsInfSaveVos;
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



    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public String getQualityState() {
        return qualityState;
    }

    public void setQualityState(String qualityState) {
        this.qualityState = qualityState;
    }

    public String getReleaseSuggest() {
        return releaseSuggest;
    }

    public void setReleaseSuggest(String releaseSuggest) {
        this.releaseSuggest = releaseSuggest;
    }
}
