package com.imall.iportal.core.platform.service;


import com.imall.iportal.core.platform.entity.GoodsDocChineseMedicinePieces;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsDocChineseMedicinePiecesService {

    /**
     * 通过商品档案ID查询
     *
     * @param id
     * @return
     */
    GoodsDocChineseMedicinePieces findByGoodsDocId(@NotNull Long id);

    GoodsDocChineseMedicinePieces save(GoodsDocChineseMedicinePieces goodsDocChineseMedicinePieces);

    void delete(Long id);
}
