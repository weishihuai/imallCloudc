package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.CartTypeCodeEnum;
import com.imall.iportal.core.shop.entity.ReceiverAddr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ygw on 2017/3/1.
 */
public class NormalShoppingCart extends ShoppingCart{

    //微信用户ID
    private Long weChatUserId;
    /**
     * 微门店Id
     */
    protected Long weShopId;

    /**
     * 门店名称
     */
    protected String weShopName;

    /**
     * 门店 承诺 送货 时间
     */
    private Integer shopPromiseSendTime;

    /**
     * 收货地址ID
     */
    protected Long receiverAddrId;

    //
    protected String deliveryTypeCode;

    //满额免费
    protected Double deliveryMinOrderAmount;

    protected ReceiverAddr receiverAddr;

    private Map<Long, CartItem> cartItems = new HashMap<>();

    public NormalShoppingCart() {
        super(CartTypeCodeEnum.WEIXIN);
    }

    public Long getWeChatUserId() {
        return weChatUserId;
    }

    public void setWeChatUserId(Long weChatUserId) {
        this.weChatUserId = weChatUserId;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weshopId) {
        this.weShopId = weshopId;
    }


    public Long getReceiverAddrId() {
        return receiverAddrId;
    }

    public void setReceiverAddrId(Long receiverAddrId) {
        this.receiverAddrId = receiverAddrId;
    }

    public String getDeliveryTypeCode() {
        return deliveryTypeCode;
    }

    public void setDeliveryTypeCode(String deliveryTypeCode) {
        this.deliveryTypeCode = deliveryTypeCode;
    }

    public Double getDeliveryMinOrderAmount() {
        return deliveryMinOrderAmount;
    }

    public void setDeliveryMinOrderAmount(Double deliveryMinOrderAmount) {
        this.deliveryMinOrderAmount = deliveryMinOrderAmount;
    }

    public String getWeShopName() {
        return weShopName;
    }

    public void setWeShopName(String weShopName) {
        this.weShopName = weShopName;
    }

    public ReceiverAddr getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(ReceiverAddr receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public Integer getShopPromiseSendTime() {
        return shopPromiseSendTime;
    }

    public void setShopPromiseSendTime(Integer shopPromiseSendTime) {
        this.shopPromiseSendTime = shopPromiseSendTime;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems.values();
    }

/*    public void setCartItems(Map<Long, NormalCartItem> cartItems) {
        this.cartItems = cartItems;
    }*/

    public CartItem getCartItem(Long objectId){
        return cartItems.get(objectId);
    }

    public void addCartItem(CartItem cartItem){
        cartItems.put(cartItem.getObjectId(), cartItem);
    }

    public void updateCartItem(CartItem cartItem){
        cartItems.put(cartItem.getObjectId(), cartItem);
    }

    public void removeCartItem(Long objectId){
        cartItems.remove(objectId);
    }

}
