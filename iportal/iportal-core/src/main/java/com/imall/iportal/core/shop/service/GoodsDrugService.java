package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsDrug;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsDrugService {

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    GoodsDrug findOne(@NotNull Long id);

    /**
     * 保存对象
     *
     * @param goodsDrug
     * @return
     */
    GoodsDrug save(GoodsDrug goodsDrug);

    /**
     * 通过商品id查找药品对象
     *
     * @param goodsId 商品id
     * @return
     */
    GoodsDrug findByGoodsId(@NotNull Long goodsId);

    void delete(Long id);
}
