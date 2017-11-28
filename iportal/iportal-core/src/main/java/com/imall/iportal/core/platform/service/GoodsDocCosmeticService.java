package com.imall.iportal.core.platform.service;


import com.imall.iportal.core.platform.entity.GoodsDocCosmetic;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsDocCosmeticService{

    GoodsDocCosmetic save(GoodsDocCosmetic goodsDocCosmetic);

    /**
     * 通过商品档案id 查找
     * @param id
     * @return
     */
    GoodsDocCosmetic findByGoodsDocId(@NotNull Long id);

    void delete(Long id);
}
