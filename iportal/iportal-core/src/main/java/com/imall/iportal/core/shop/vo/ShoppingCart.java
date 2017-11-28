package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.CartTypeCodeEnum;

/**
 * Created by ygw on 2017/3/1.
 */
public abstract class ShoppingCart {


    /**
     * 购物车唯一的标识
     */
    protected String uniqueKey;

    protected CartTypeCodeEnum cartTypeCodeEnum;

    /**
     * 门店Id
     */
    protected Long shopId;

    /**
     * 会员ID
     */
    protected Long memberId;

    /**
     * 配送费
     */
    protected Double freightAmount = 0D;

    /**
     * 商品总金额
     */
    protected Double goodsTotalAmount = 0D;

    /**
     * 订单折扣总金额
     */
    protected Double orderDiscountTotalAmount = 0D;  //todo:营销策略、营销规则 未开始

    /**
     * 订单总金额
     */
    protected Double orderTotalAmount = 0D;


    public ShoppingCart(CartTypeCodeEnum cartTypeCodeEnum){
        this.cartTypeCodeEnum = cartTypeCodeEnum;
    }

    public CartTypeCodeEnum getCartTypeCodeEnum() {
        return cartTypeCodeEnum;
    }

    public void setCartTypeCodeEnum(CartTypeCodeEnum cartTypeCodeEnum) {
        this.cartTypeCodeEnum = cartTypeCodeEnum;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }


    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Double getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(Double goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public Double getOrderDiscountTotalAmount() {
        return orderDiscountTotalAmount;
    }

    public void setOrderDiscountTotalAmount(Double orderDiscountTotalAmount) {
        this.orderDiscountTotalAmount = orderDiscountTotalAmount;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

}
