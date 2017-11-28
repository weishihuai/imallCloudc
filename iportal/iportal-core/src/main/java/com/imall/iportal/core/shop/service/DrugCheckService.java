package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DrugCheck;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DrugCheckService {
    /**
     * 药品检查新增
     * @param drugCheckVo   药品检查信息
     * @return
     */
    DrugCheck save(@NotNull @Valid DrugCheckSaveVo drugCheckVo) throws ParseException;

    /**
     * 获取药品检查详情
     * @param shopId 门店 ID
     * @param id    药品检查 ID
     * @return
     */
    DrugCheckDetailVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 药品检查更新
     * @param drugCheckUpdateVo
     * @return
     */
    DrugCheck update(@NotNull @Valid DrugCheckUpdateVo drugCheckUpdateVo);

    /**
     * 分页查询药品检查信息
     * @param pageable
     * @param drugCheckSearchParam
     * @return
     */
    Page<DrugCheckPageVo> query(@NotNull Pageable pageable, @NotNull @Valid DrugCheckSearchParam drugCheckSearchParam);
}
