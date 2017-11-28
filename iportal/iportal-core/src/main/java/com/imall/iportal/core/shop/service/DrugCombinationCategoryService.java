package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DrugCombinationCategory;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DrugCombinationCategoryService{

    /**
     * 新增联合用药分类
     * @param drugCombinationCategorySaveVo
     * @return
     */
    DrugCombinationCategory save(@NotNull @Valid DrugCombinationCategorySaveVo drugCombinationCategorySaveVo);

    /**
     * 获取联合用药分类详情
     * @param id
     * @return
     */
    DrugCombinationCategoryDetailVo findById(@NotNull Long id);

    /**
     * 更新联合用药分类
     * @param drugCombinationCategoryUpdateVo
     * @return
     */
    DrugCombinationCategory update(@NotNull @Valid DrugCombinationCategoryUpdateVo drugCombinationCategoryUpdateVo);

    /**
     * 删除联合用药分类
     * @param orgId
     * @param id
     */
    void delete(@NotNull Long orgId, @NotNull Long id);

    /**
     * 分页查询联合用药分类
     * @param pageable
     * @param drugCombinationCategorySearchParam
     * @return
     */
    Page<DrugCombinationCategoryPageVo> query(Pageable pageable, @NotNull @Valid DrugCombinationCategorySearchParam drugCombinationCategorySearchParam);

    /**
     * 获取机构所有的联合用药分类
     * @param orgId
     * @return
     */
    List<DrugCombinationCategoryDetailVo> listAllByOrgId(@NotNull Long orgId);

    /**
     * 判断分类是否存在
     * @param orgId
     * @param id
     * @param categoryNm
     * @return
     */
    Boolean checkByCategoryNm(@NotNull Long orgId, Long id, @NotEmpty String categoryNm);
}
