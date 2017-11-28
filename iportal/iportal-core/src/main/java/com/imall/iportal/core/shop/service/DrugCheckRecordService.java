package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.DrugCheckRecordPageVo;
import com.imall.iportal.core.shop.vo.DrugCheckRecordSaveVo;
import com.imall.iportal.core.shop.vo.DrugCheckRecordSearchParam;
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
public interface DrugCheckRecordService{

    /**
     * 保存检查记录
     * @param drugCheckRecordSaveVo
     */
    void save(@NotNull @Valid DrugCheckRecordSaveVo drugCheckRecordSaveVo);

    /**
     * 分页查询药品检查记录
     * @param pageable
     * @param drugCheckRecordSearchParam
     * @return
     */
    Page<DrugCheckRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid DrugCheckRecordSearchParam drugCheckRecordSearchParam);
}
