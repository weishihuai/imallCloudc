package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DrugCombination;
import com.imall.iportal.core.shop.vo.*;
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
public interface DrugCombinationService{

    /**
     * 检查门店联合用药分类下是否存在联合用药信息
     * @param orgId    机构 ID
     * @param drugCombinationCategoryId 联合用药分类 ID
     * @return 存在返回 true；不存在返回 false
     */
    Boolean checkDrugCombination(@NotNull Long orgId, @NotNull Long drugCombinationCategoryId);

    /**
     * 新增
     * @param drugCombinationSaveVo
     * @return
     */
    DrugCombination save(@NotNull @Valid DrugCombinationSaveVo drugCombinationSaveVo);

    /**
     * 获取联合用药详情
     * @param id
     * @return
     */
    DrugCombinationDetailVo findById(@NotNull Long id);

    /**
     * 分页查询联合用药
     * @param pageable
     * @param drugCombinationSearchParam
     * @return
     */
    Page<DrugCombinationPageVo> query(Pageable pageable, DrugCombinationSearchParam drugCombinationSearchParam);

    /**
     * 删除
     * @param id
     */
    void delete(@NotNull Long orgId, @NotNull Long id);

    /**
     * 更新
     * @param drugCombinationUpdateVo
     */
    void update(@NotNull @Valid DrugCombinationUpdateVo drugCombinationUpdateVo);
}
