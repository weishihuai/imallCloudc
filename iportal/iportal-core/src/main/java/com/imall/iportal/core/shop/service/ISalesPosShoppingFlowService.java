package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.SalesPosShoppingCart;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by ygw on 2017/3/1.
 */
@Validated
public interface ISalesPosShoppingFlowService extends IShoppingFlowService<SalesPosShoppingCart>{

    /**
     * 非会员：获取一个购物车，如果不存在，则创建一个并返回。
     *
     * @param shopId   子公司ID
     * @return
     */
    SalesPosShoppingCart getCartOrNewCart(@NotNull Long shopId);

    /**
     * 获取一个购物车
     *
     * @param uniqueKey   购物车唯一标识key
     * @return
     */
    SalesPosShoppingCart getCart(@NotBlank String uniqueKey);

    /**
     * 清空购物车
     * @param uniqueKey 购物车唯一标识key
     * @return
     */
    SalesPosShoppingCart clearCart(@NotNull Long shopId, @NotBlank String uniqueKey);

    /**
     * 移除选中的项
     * @param shopId 子公司ID
     * @param uniqueKey
     * @return
     */
    SalesPosShoppingCart clearCartBySelected(@NotNull Long shopId, @NotNull String uniqueKey);

    /**
     * 删除购物车
     * @param uniqueKey 购物车唯一标识key
     * @return
     */
    SalesPosShoppingCart dropCart(@NotNull Long shopId, @NotBlank String uniqueKey);

    /**
     * 添加物品项
     * @param batch 批次
     * @param quantity
     * @param shopId
     * @param memberId
     * @param skuId
     * @param isModify true表示数量覆盖，false 表示数量在原有的数量上 加或减
     * @return
     */
    SalesPosShoppingCart addCartItem(@NotNull Long shopId, @NotNull Long memberId, @NotNull Long skuId, @NotBlank String batch, @NotNull Long quantity, boolean isModify);


    /**
     * 添加物品项
     * @param batch 批号
     * @param quantity
     * @param uniqueKey 购物车唯一标识key
     * @return
     */
    SalesPosShoppingCart addCartItem(@NotBlank String uniqueKey, @NotNull Long skuId, @NotBlank String batch, @NotNull Long quantity, boolean isModify);

    /**
     * 移除物品项
     * @param skuId 批次ID
     * @param shopId
     * @param memberId
     * @param batch 批次
     * @return
     */
    SalesPosShoppingCart removeCartItem(@NotNull Long shopId, @NotNull Long memberId, @NotNull Long skuId, @NotBlank String batch);

    /**
     * 移除物品项
     * @param objectId 批次ID
     * @param uniqueKey 购物车唯一标识key
     * @return
     */
    SalesPosShoppingCart removeCartItem(@NotBlank String uniqueKey, @NotNull Long objectId, @NotBlank String batch);

    //非会员的购物车、转会员购物车
    SalesPosShoppingCart convert(@NotBlank String uniqueKey, Long memberId);

    //会员的购物车、转非会员购物车
    SalesPosShoppingCart convertWithoutMember(String uniqueKey);
}
