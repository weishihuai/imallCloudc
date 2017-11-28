package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsMedicalInstruments;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsMedicalInstrumentsService {

    /**
     * 通过goodsId查找
     * @param id
     * @return
     */
    GoodsMedicalInstruments findByGoodsId(@NotNull Long id);


    GoodsMedicalInstruments save(GoodsMedicalInstruments goodsMedicalInstruments);

    void delete(Long id);
}
