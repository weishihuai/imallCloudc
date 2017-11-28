package com.imall.iportal.frontend.wechat.index.proxy;

/**
 * Created by lxh on 2017/8/17.
 */
public class SalesCategoryProxy {
    //主键
    private Long id;
    //分类名称
    private String categoryName;
    //图标路径
    private String logoUrl;

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
