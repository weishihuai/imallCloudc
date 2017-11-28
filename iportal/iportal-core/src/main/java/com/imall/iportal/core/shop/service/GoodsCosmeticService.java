package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsCosmetic;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsCosmeticService {

    /**
     * 通过goodsId查找
     * @param id
     * @return
     */
    GoodsCosmetic findByGoodsId(@NotNull Long id);

    GoodsCosmetic save(GoodsCosmetic goodsCosmetic);

    void delete(Long id);
}
