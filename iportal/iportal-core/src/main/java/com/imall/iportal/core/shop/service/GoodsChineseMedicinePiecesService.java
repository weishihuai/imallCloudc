package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsChineseMedicinePieces;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsChineseMedicinePiecesService {

    GoodsChineseMedicinePieces save(GoodsChineseMedicinePieces goodsChineseMedicinePieces);

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    GoodsChineseMedicinePieces findOne(@NotNull Long id);

    /**
     * 通过商品id 查找中药饮片
     *
     * @param goodsId 商品id
     * @return
     */
    GoodsChineseMedicinePieces findByGoodsId(Long goodsId);

    void delete(Long id);
}
