package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/7/6.
 * 微信端商品列表搜索参数
 */
public class WeChatGoodsListSearchParam {

    /**
     * 拼音/商品编码/商品名称
     */
    private String keywords;

    /**
     * 默认按id排序 销量/价格
     */
    private String orderBy;

    /**
     * 排序方式 升序/降序
     */
    private String sortOrder;

    //销售分类ID
    private Long categoryId;

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
