package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/6.
 */
public class SalesCategorySearchParam {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 销售分类名称
     */
    private String categoryName;

    public SalesCategorySearchParam(){

    }

    public SalesCategorySearchParam(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
