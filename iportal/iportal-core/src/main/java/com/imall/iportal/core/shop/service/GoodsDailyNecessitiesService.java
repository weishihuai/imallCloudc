package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsDailyNecessities;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsDailyNecessitiesService {

    /**
     * 通过goodsId查找
     * @param id
     * @return
     */
    GoodsDailyNecessities findByGoodsId(@NotNull Long id);

    GoodsDailyNecessities save(GoodsDailyNecessities goodsDailyNecessities);

    void delete(Long id);
}
