package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by ou on 2017/7/10.
 */
public class ProblemDrugSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     *  批号
     */
    private String batch;
    /**
     *  搜索值
     */
    private String keyword;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
