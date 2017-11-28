package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/8.
 */
public class ReleaseNoticePageVo {

    /**
     * 停售单id
     */
    private Long id;
    /**
     * 解停单号
     */
    private String releaseNum;
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


    public String getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(String releaseNum) {
        this.releaseNum = releaseNum;
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
