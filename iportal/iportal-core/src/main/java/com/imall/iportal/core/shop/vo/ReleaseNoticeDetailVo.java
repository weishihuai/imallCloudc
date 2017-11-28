package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by caidapao on 2017/8/8.
 */
public class ReleaseNoticeDetailVo {
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
    private String docMakeTimeStr;
    /**
     * 停售时间
     */
    private String releaseDateStr;
    /**
     * 质量状况
     */
    private String qualityState;
    /**
     * 停售意见
     */
    private String releaseSuggest;

    /**
     * 批次商品信息
     */
    private List<ReleaseGoodsInfVo> releaseGoodsInfVos;

    public List<ReleaseGoodsInfVo> getReleaseGoodsInfVos() {
        return releaseGoodsInfVos;
    }

    public void setReleaseGoodsInfVos(List<ReleaseGoodsInfVo> releaseGoodsInfVos) {
        this.releaseGoodsInfVos = releaseGoodsInfVos;
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

    public String getDocMakeTimeStr() {
        return docMakeTimeStr;
    }

    public void setDocMakeTimeStr(String docMakeTimeStr) {
        this.docMakeTimeStr = docMakeTimeStr;
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
