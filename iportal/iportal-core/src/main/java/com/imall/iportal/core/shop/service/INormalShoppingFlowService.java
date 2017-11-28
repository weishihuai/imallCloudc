package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.Sku;
import com.imall.iportal.core.shop.vo.NormalShoppingCart;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ygw on 2017/3/1.
 */
@Validated
public interface INormalShoppingFlowService extends IShoppingFlowService<NormalShoppingCart>{

    /**
     * 保存收货地址
     * @param shopId 子公司ID
     * @param weChatUserId 微信用户ID
     * @param receiverAddrId
     * @return
     */
    NormalShoppingCart saveReceiverAddr(@NotNull Long shopId, @NotNull Long weChatUserId, @NotNull Long receiverAddrId);

    /**
     * 保存默认收货地址
     * @param shopId 子公司ID
     * @param weChatUserId 微信用户ID
     * @return
     */
    NormalShoppingCart saveDefaultReceiverAddr(@NotNull Long shopId, @NotNull Long weChatUserId);

    /**
     * 改变选中状态
     * @param shopId
     * @param weChatUserId 微信用户ID
     * @param skuId
     * @return
     */
    NormalShoppingCart selectedChange(@NotNull Long shopId, @NotNull Long weChatUserId, @NotNull Long skuId);

    /**
     * 全选
     * @param shopId
     * @param weChatUserId 微信用户ID
     * @return
     */
    NormalShoppingCart selectAll(@NotNull Long shopId, @NotNull Long weChatUserId);


    /**
     * 全不选
     * @param shopId
     * @param weChatUserId 微信用户ID
     * @return
     */
    NormalShoppingCart unselectAll(@NotNull Long shopId, @NotNull Long weChatUserId);


    /**
     * 会员：获取一个最新的购物车
     *
     * @param shopId   子公司ID
     * @param weChatUserId 微信用户ID
     * @return
     */
    NormalShoppingCart getCartByNewest(@NotNull Long shopId,@NotNull Long weChatUserId);

    /**
     * 获取商品数量
     * @param shopId
     * @param weChatUserId 微信用户ID
     * @return
     */
    Long getGoodsCount(@NotNull Long shopId,@NotNull Long weChatUserId);

    /**
     * 商品价格修改之后，更新缓存里的价格
     * @param sku
     */
    void updateCartAfterPriceChange(@NotNull Sku sku);


    /**
     * 添加物品项
     * @param objectId 批次ID
     * @param quantity
     * @param shopId
     * @param weChatUserId
     * @param isModify true表示数量覆盖，false 表示数量在原有的数量上 加或减
     * @return
     */
    NormalShoppingCart addCartItem(@NotNull Long shopId, @NotNull Long weChatUserId, @NotNull Long objectId, @NotNull Long quantity, boolean isModify);


    /**
     * 移除物品项
     * @param objectId 批次ID
     * @param shopId
     * @param weChatUserId
     * @return
     */
    NormalShoppingCart removeCartItem(@NotNull Long shopId, @NotNull Long weChatUserId, @NotNull Long objectId);

    /**
     * 批量移除购物车项
     * @param shopId
     * @param weChatUserId
     * @param objectIds
     * @return
     */
    NormalShoppingCart batchRemoveCartItem(@NotNull Long shopId, @NotNull Long weChatUserId, @NotEmpty List<Long> objectIds);
}
