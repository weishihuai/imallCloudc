package com.imall.iportal.core.platform.service;


import com.imall.iportal.core.platform.entity.GoodsDocOther;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsDocOtherService{

    GoodsDocOther save(GoodsDocOther goodsDocOther);

    /**
     * 通过商品档案id查找
     * @param id
     * @return
     */
    GoodsDocOther findByGoodsDocId(@NotNull Long id);

    void delete(Long id);
}
