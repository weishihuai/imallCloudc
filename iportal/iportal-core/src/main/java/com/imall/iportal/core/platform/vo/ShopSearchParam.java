package com.imall.iportal.core.platform.vo;

/**
 * Created by ou on 2017/7/24.
 */
public class ShopSearchParam {

    /**
     * 企业 名称
     */
    private String entNm;
    /**
     *门店 状态 (是否 启用)
     */
    private String isEnable;
    /**
     * 开始时间
     */
    private String startTimeString;
    /**
     * 结束时间
     */
    private String endTimeString;


    public String getEntNm() {
        return entNm;
    }

    public void setEntNm(String entNm) {
        this.entNm = entNm;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }
}
