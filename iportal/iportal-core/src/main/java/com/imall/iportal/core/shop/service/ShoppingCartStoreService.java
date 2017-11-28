package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ShoppingCartStore;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ShoppingCartStoreService{

    //清空
    void clearCart(@NotBlank String cartUniqueKey);

    //新增
    void addCartItem(@NotNull ShoppingCartStore store);

    //批量新增
    void batchAddCartItems(@NotEmpty List<ShoppingCartStore> storeList);

    //删除
    void removeCartItemByBatchId(@NotBlank String cartUniqueKey, @NotNull Long goodsBatchId);

    //批量删除
    void batchRemoveCartItemByBatchId(@NotBlank String cartUniqueKey, @NotEmpty List<Long> goodsBatchIds);

    //删除
    void removeCartItemBySkuId(@NotBlank String cartUniqueKey, @NotNull Long skuId);

    //批量删除
    void batchRemoveCartItemBySkuId(@NotBlank String cartUniqueKey, @NotEmpty List<Long> skuIds);

    //根据购物车的唯一key，查找到所有的项
    List<ShoppingCartStore> findByCartUniqueKey(@NotBlank String cartUniqueKey);

    void delete(@NotNull Long id);
}
