package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/25.
 * 库存预警 搜索参数
 */
public class StockWarningSearchParam {
    /**
     * 商品名称、编码、拼音码
     */
    private String searchFields;
    /**
     * 商品批号
     */
    private String batch;
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
