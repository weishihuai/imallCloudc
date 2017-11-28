package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class ChineseMedicinePiecesCleaningBucketRecordSearchParam {

    /**
     * 搜索值(拼音码|名称|编码)
     */
    private String searchValue;
    /**
     * 批号
     */
    private String batch;
    /**
     *  清斗 人 姓名
     */
    private String cleaningBucketManNm;

    /**
     * 清斗时间
     */
    private String startTimeString;
    /**
     * 结束时间
     */
    private String endTimeString;
    /**
     * shopId
     */
    private Long shopId;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCleaningBucketManNm() {
        return cleaningBucketManNm;
    }

    public void setCleaningBucketManNm(String cleaningBucketManNm) {
        this.cleaningBucketManNm = cleaningBucketManNm;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
