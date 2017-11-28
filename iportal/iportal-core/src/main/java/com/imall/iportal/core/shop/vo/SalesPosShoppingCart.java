package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.CartTypeCodeEnum;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ygw on 2017/3/1.
 */
public class SalesPosShoppingCart extends ShoppingCart{

    private Map<String, CartItem> cartItems = new HashMap<>();

    public SalesPosShoppingCart() {
        super(CartTypeCodeEnum.SALES_POS);
    }

    public Collection<CartItem> getCartItems() {
        return cartItems.values();
    }

    public CartItem getCartItem(Long skuId, String batch){
        return cartItems.get(getCartItemKey(skuId, batch));
    }

    public void addCartItem(CartItem cartItem){
        cartItems.put(getCartItemKey(cartItem.getSkuId(), cartItem.getBatch()), cartItem);
    }

    public void updateCartItem(CartItem cartItem){
        cartItems.put(getCartItemKey(cartItem.getSkuId(), cartItem.getBatch()), cartItem);
    }

    public void removeCartItem(Long skuId, String batch){
        cartItems.remove(getCartItemKey(skuId, batch));
    }

    private String getCartItemKey(Long skuId, String batch){
        return skuId + "_key_" + batch;
    }
}
