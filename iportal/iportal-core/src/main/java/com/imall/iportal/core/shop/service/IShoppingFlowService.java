package com.imall.iportal.core.shop.service;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by ygw on 2017/3/1.
 * 购物流程
 */
@Validated
public interface IShoppingFlowService<T> {

    //清缓存
    void clearCartCache();

    /**
     * 会员：获取一个购物车，如果不存在，则创建一个并返回。
     *
     * @param shopId   子公司ID
     * @param weChatUserId 微信用户ID
     * @return
     */
    T getCartOrNewCart(@NotNull Long shopId,@NotNull Long weChatUserId);

    /**
     * 清空购物车
     * @param shopId 子公司ID
     * @param weChatUserId 微信用户ID
     * @return
     */
    T clearCart(@NotNull Long shopId, @NotNull Long weChatUserId);

    /**
     * 移除选中的项
     * @param shopId 子公司ID
     * @param weChatUserId 微门店为微信用户ID，POS为会员ID
     * @return
     */
    T clearCartBySelected(@NotNull Long shopId, @NotNull Long weChatUserId);

    /**
     * 删除购物车
     * @param shopId 子公司ID
     * @param weChatUserId 微信用户ID
     * @return
     */
    T dropCart(@NotNull Long shopId, @NotNull Long weChatUserId);

}
