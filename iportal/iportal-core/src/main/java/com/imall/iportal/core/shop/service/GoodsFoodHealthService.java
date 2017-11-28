package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsFoodHealth;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsFoodHealthService {

    /**
     * 通过goodsId查找
     * @param id
     * @return
     */
    GoodsFoodHealth findByGoodsId(@NotNull Long id);


    GoodsFoodHealth save(GoodsFoodHealth goodsFoodHealth);

    void delete(Long id);
}
