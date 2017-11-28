package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsOther;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsOtherService {

    /**
     * 通过goodsId查找
     * @param id
     * @return
     */
    GoodsOther findByGoodsId(@NotNull Long id);


    GoodsOther save(GoodsOther goodsOther);

    void delete(Long id);
}
