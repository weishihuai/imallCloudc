package com.imall.iportal.core.shop.vo;

/**
 * 用于商品添加页面的商品分类下拉框
 * Created by caidapao on 2017/7/12.
 */
public class SalesCategorySimpleVo {

    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
