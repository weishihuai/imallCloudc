package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/8.
 */
public class ReleaseNoticeSearchParam {

    /**
     * 停售单号
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
    private String fromDocMakeTimeStr;
    private String toDocMakeTimeStr;
    /**
     * 解停时间
     */
    private String fromReleaseDateStr;
    private String toReleaseDateStr;


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

    public String getFromDocMakeTimeStr() {
        return fromDocMakeTimeStr;
    }

    public void setFromDocMakeTimeStr(String fromDocMakeTimeStr) {
        this.fromDocMakeTimeStr = fromDocMakeTimeStr;
    }

    public String getToDocMakeTimeStr() {
        return toDocMakeTimeStr;
    }

    public void setToDocMakeTimeStr(String toDocMakeTimeStr) {
        this.toDocMakeTimeStr = toDocMakeTimeStr;
    }

    public String getToReleaseDateStr() {
        return toReleaseDateStr;
    }

    public void setToReleaseDateStr(String toReleaseDateStr) {
        this.toReleaseDateStr = toReleaseDateStr;
    }

    public String getFromReleaseDateStr() {
        return fromReleaseDateStr;
    }

    public void setFromReleaseDateStr(String fromReleaseDateStr) {
        this.fromReleaseDateStr = fromReleaseDateStr;
    }
}
