package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by ygw on 2017/3/1.
 */
public class NormalCartItem extends CartItem{

    //super类的objectId为SkuId

    /**
     * 销售分类ID
     */
    private List<Long> categoryId;

    //是否 处方药
    private Boolean isRx = false;

    public void setCategoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getCategoryId() {
        return categoryId;
    }

    public Boolean getIsRx() {
        return isRx;
    }

    public void setIsRx(Boolean rx) {
        isRx = rx;
    }
}
