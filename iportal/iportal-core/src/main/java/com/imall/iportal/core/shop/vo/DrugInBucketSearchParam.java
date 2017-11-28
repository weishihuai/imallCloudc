package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/12.
 * 商品装斗列表 搜索参数
 */
public class DrugInBucketSearchParam {
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
     * 装斗人姓名
     */
    private String inBucketManName;
    /**
     * 装斗时间(开始时间)
     */
    private String inBucketStartTimeString;
    /**
     * 装斗时间(结束时间)
     */
    private String inBucketEndTimeString;

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

    public String getInBucketManName() {
        return inBucketManName;
    }

    public void setInBucketManName(String inBucketManName) {
        this.inBucketManName = inBucketManName;
    }

    public String getInBucketStartTimeString() {
        return inBucketStartTimeString;
    }

    public void setInBucketStartTimeString(String inBucketStartTimeString) {
        this.inBucketStartTimeString = inBucketStartTimeString;
    }

    public String getInBucketEndTimeString() {
        return inBucketEndTimeString;
    }

    public void setInBucketEndTimeString(String inBucketEndTimeString) {
        this.inBucketEndTimeString = inBucketEndTimeString;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
