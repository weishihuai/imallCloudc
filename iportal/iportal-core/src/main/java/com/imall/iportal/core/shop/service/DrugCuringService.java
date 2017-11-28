package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DrugCuring;
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
public interface DrugCuringService {
    /**
     * 药品养护新增
     * @param drugCuringSaveVo   药品养护信息
     * @return
     */
    DrugCuring save(@NotNull @Valid DrugCuringSaveVo drugCuringSaveVo) throws ParseException;

    /**
     * 获取药品养护详情
     * @param shopId 门店 ID
     * @param id    药品养护 ID
     * @return
     */
    DrugCuringDetailVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 药品养护更新
     * @param drugCuringUpdateVo
     * @return
     */
    DrugCuring update(@NotNull @Valid DrugCuringUpdateVo drugCuringUpdateVo);

    /**
     * 分页查询药品养护信息
     * @param pageable
     * @param drugCuringSearchParam
     * @return
     */
    Page<DrugCuringPageVo> query(@NotNull Pageable pageable, @NotNull @Valid DrugCuringSearchParam drugCuringSearchParam);
}
