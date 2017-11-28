package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by ygw on 2017/3/1.
 */
public class SalesPosCartItem extends CartItem{

    //super类的objectId为GoodsBatchId

    /**
     * 当前商品批次的库存
     */
    private Long currentQuantity;

    /**
     * 销售分类ID
     */
    private List<Long> categoryId;

    public Long getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Long currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public List<Long> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
    }

}
