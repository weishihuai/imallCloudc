package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.Sku;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SkuService {

    /**
     * 根据商品ID查询相应的Sku信息
     *
     * @param goodsId 商品ID
     * @return
     */
    Sku findByGoodsId(@NotNull Long goodsId);

    Sku findOne(@NotNull Long skuId);

    Sku save(Sku sku);

    Sku update(Sku sku);


    /**
     * 下单：上锁库存
     * @param id skuId
     * @param version 版本
     * @param quantity 数量
     * @return
     */
    Boolean updateByLockSkuStock(@NotNull Long id, @NotNull Long version, @NotNull Long quantity);

    /**
     * 关闭订单：解锁库存
     * @param id skuId
     * @param version 版本
     * @param quantity 数量
     * @return
     */
    Boolean updateByUnlockSkuStock(@NotNull Long id, @NotNull Long version, @NotNull Long quantity);

    /**
     * 订单发货：用锁
     * @param id
     * @param version
     * @param quantity
     * @return
     */
    Boolean updateByUselockStock(@NotNull Long id, @NotNull Long version, @NotNull Long quantity);

    /**
     * 取可卖库存
     * @param id
     * @return
     */
    Long getStockQuantity(@NotNull Long id);


    /**
     * 检查商品状态
     * @param skuId 商品SkuID
     */
    void checkGoodsState(Long skuId);

}
