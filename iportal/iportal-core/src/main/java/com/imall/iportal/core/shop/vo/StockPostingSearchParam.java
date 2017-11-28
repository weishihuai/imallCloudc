package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/27.
 * 盘点过账 搜索参数
 */
public class StockPostingSearchParam {
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
     * 盘点单号
     */
    private String checkOrderNum;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getCheckOrderNum() {
        return checkOrderNum;
    }

    public void setCheckOrderNum(String checkOrderNum) {
        this.checkOrderNum = checkOrderNum;
    }
}
