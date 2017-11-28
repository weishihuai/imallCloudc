package com.imall.iportal.core.shop.commons.util;

import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.dicts.CartTypeCodeEnum;
import com.imall.commons.dicts.DeliveryTypeCodeEnum;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.shop.vo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygw on 2017/3/1.
 */
public class ShoppingCalc {

    public static Map<Long, Long> calcCategory(NormalShoppingCart shoppingCart){
        Map<Long, Long> categoryMap = new HashMap<>();
        for(CartItem cartItem: shoppingCart.getCartItems()){
            NormalCartItem normalCartItem = (NormalCartItem)cartItem;
            for(Long categoryId : normalCartItem.getCategoryId()) {
                Long quantity = BigDecimalUtil.add(categoryMap.get(categoryId), cartItem.getQuantity()).longValue();
                categoryMap.put(categoryId, quantity);
            }
        }
        return categoryMap;
    }

    public static CartItem calcItemAmount(ShoppingCart cart, CartItem cartItem){
        //这个物品总优惠金额 = 单品优惠价格 * 数量 ;
        cartItem.setDiscountTotalAmount(BigDecimalUtil.multiply(cartItem.getDiscountUnitPrice(), cartItem.getQuantity()));
        //物品总金额（小计）= 单价 * 数量 - 这个物品总优惠金额;
        cartItem.setTotalAmount(BigDecimalUtil.subtract(BigDecimalUtil.multiply(cartItem.getUnitPrice(), cartItem.getQuantity()), cartItem.getDiscountTotalAmount()));
        return cartItem;
    }

    public static ShoppingCart calcCartAmount(WeShop weShop, ShoppingCart shoppingCart){
        //计算商品总金额
        double goodsTotalAmount = 0d;
        List<CartItem> cartItemList = new ArrayList<>();
        switch (shoppingCart.getCartTypeCodeEnum()) {
            case SALES_POS:
                SalesPosShoppingCart posShoppingCart = (SalesPosShoppingCart)shoppingCart;
                cartItemList.addAll(posShoppingCart.getCartItems());
                break;

            case WEIXIN:
                NormalShoppingCart noramlShoppingCart = (NormalShoppingCart)shoppingCart;
                cartItemList.addAll(noramlShoppingCart.getCartItems());
                break;
        }


        for(CartItem cartItem : cartItemList){
            if(cartItem.getIsItemSelected()){
                goodsTotalAmount = BigDecimalUtil.add(goodsTotalAmount, cartItem.getTotalAmount());
            }
        }
        shoppingCart.setGoodsTotalAmount(goodsTotalAmount);
        shoppingCart.setFreightAmount(0d);

        if(shoppingCart.getCartTypeCodeEnum() == CartTypeCodeEnum.WEIXIN && (!cartItemList.isEmpty())){
            //配送费（运费）
            double freightAmount = 0d;
            if(DeliveryTypeCodeEnum.NEED_PAY == DeliveryTypeCodeEnum.fromCode(weShop.getDeliveryTypeCode())){
                freightAmount = weShop.getDeliveryAmount();
                ((NormalShoppingCart)shoppingCart).setDeliveryTypeCode(DeliveryTypeCodeEnum.NEED_PAY.toCode());
            }
            else if(DeliveryTypeCodeEnum.FULL_AMOUNT_NOT_PAY == DeliveryTypeCodeEnum.fromCode(weShop.getDeliveryTypeCode())){
                if(goodsTotalAmount < weShop.getDeliveryMinOrderAmount()){
                    freightAmount = weShop.getDeliveryAmount();
                }
                ((NormalShoppingCart)shoppingCart).setDeliveryTypeCode(DeliveryTypeCodeEnum.FULL_AMOUNT_NOT_PAY.toCode());
            }
            else {
                ((NormalShoppingCart)shoppingCart).setDeliveryTypeCode(DeliveryTypeCodeEnum.NEVER_PAY.toCode());
            }
            shoppingCart.setFreightAmount(freightAmount);
        }

        //计算订单总金额 = 商品总金额 - 订单折扣总金额 + 运费
        double orderTotalAmount = BigDecimalUtil.add(BigDecimalUtil.subtract(goodsTotalAmount, shoppingCart.getOrderDiscountTotalAmount()),  shoppingCart.getFreightAmount());
        shoppingCart.setOrderTotalAmount(orderTotalAmount);
        return shoppingCart;
    }

    public static ShoppingCart calcAmount(WeShop weShop, ShoppingCart shoppingCart){

        switch (shoppingCart.getCartTypeCodeEnum()) {
            case SALES_POS:
                SalesPosShoppingCart posShoppingCart = (SalesPosShoppingCart)shoppingCart;
                for(CartItem cartItem : posShoppingCart.getCartItems()){
                    posShoppingCart.updateCartItem(calcItemAmount(shoppingCart, cartItem));
                }
                return calcCartAmount(weShop, posShoppingCart);

            case WEIXIN:
                NormalShoppingCart noramlShoppingCart = (NormalShoppingCart)shoppingCart;
                for(CartItem cartItem : noramlShoppingCart.getCartItems()){
                    noramlShoppingCart.updateCartItem(calcItemAmount(shoppingCart, cartItem));
                }
                return calcCartAmount(weShop, noramlShoppingCart);
        }

        return calcCartAmount(weShop, shoppingCart);
    }
}
