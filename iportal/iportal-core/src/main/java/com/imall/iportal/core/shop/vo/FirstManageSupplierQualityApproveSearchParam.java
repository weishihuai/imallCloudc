package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class FirstManageSupplierQualityApproveSearchParam {

    /**
     * 拼音 码
     */
    private Long shopId;
    /**
     * 拼音 码
     */
    private String searchValue;
    /**
     * 审核 状态
     */
    private String state;
    /**
     * 开始时间
     */
    private String startTimeString;
    /**
     * 结束时间
     */
    private String endTimeString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
