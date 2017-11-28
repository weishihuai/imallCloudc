package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.DrugCuringRecordPageVo;
import com.imall.iportal.core.shop.vo.DrugCuringRecordSaveVo;
import com.imall.iportal.core.shop.vo.DrugCuringRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DrugCuringRecordService{

    /**
     * 保存养护记录
     * @param drugCuringRecordSaveVo
     */
    void save(@NotNull @Valid DrugCuringRecordSaveVo drugCuringRecordSaveVo);

    /**
     * 分页查询药品养护记录
     * @param pageable
     * @param drugCuringRecordSearchParam
     * @return
     */
    Page<DrugCuringRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid DrugCuringRecordSearchParam drugCuringRecordSearchParam);
}
