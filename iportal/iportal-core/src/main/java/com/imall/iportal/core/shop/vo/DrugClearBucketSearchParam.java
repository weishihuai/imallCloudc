package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/12.
 * 商品清斗列表 搜索参数
 */
public class DrugClearBucketSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 商品名称、编码、拼音码
     */
    private String searchFields;
    /**
     * 商品批号
     */
    private String batch;
    /**
     * 清斗人姓名
     */
    private String clearBucketManName;
    /**
     * 清斗时间(开始时间)
     */
    private String  clearBucketStartTimeString;
    /**
     * 清斗时间(结束时间)
     */
    private String clearBucketEndTimeString;

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getClearBucketManName() {
        return clearBucketManName;
    }

    public void setClearBucketManName(String clearBucketManName) {
        this.clearBucketManName = clearBucketManName;
    }

    public String getClearBucketStartTimeString() {
        return clearBucketStartTimeString;
    }

    public void setClearBucketStartTimeString(String clearBucketStartTimeString) {
        this.clearBucketStartTimeString = clearBucketStartTimeString;
    }

    public String getClearBucketEndTimeString() {
        return clearBucketEndTimeString;
    }

    public void setClearBucketEndTimeString(String clearBucketEndTimeString) {
        this.clearBucketEndTimeString = clearBucketEndTimeString;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
