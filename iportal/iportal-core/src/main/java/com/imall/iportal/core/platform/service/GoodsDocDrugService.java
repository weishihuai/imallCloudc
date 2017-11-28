package com.imall.iportal.core.platform.service;


import com.imall.iportal.core.platform.entity.GoodsDocDrug;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsDocDrugService{

    /**
     * 通过商品档案ID查找
     * @param id
     * @return
     */
    GoodsDocDrug findByGoodsDocId(Long id);

    /**
     * 保存对象
     * @param goodsDocDrug
     * @return
     */
    GoodsDocDrug save(@NotNull GoodsDocDrug goodsDocDrug);

    void delete(Long id);
}
