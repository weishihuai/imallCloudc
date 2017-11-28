package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.SalesCategory;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 销售 分类(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SalesCategoryService{
    /**
     * 保存销售分类信息
     * @param salesCategorySaveVo 销售分类信息
     * @return
     */
    SalesCategory save(@NotNull @Valid SalesCategorySaveVo salesCategorySaveVo);

    /**
     * 更新销售分类信息
     * @param salesCategoryUpdateVo 销售分类信息
     * @return
     */
    SalesCategory update(@NotNull @Valid SalesCategoryUpdateVo salesCategoryUpdateVo);

    /**
     * 根据ID查询销售分类信息
     * @param id 销售分类ID
     * @return
     */
    SalesCategoryDetailVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 分页查询销售分类信息
     * @param pageable
     * @param salesCategorySearchParam 搜索条件实体
     * @return 分页对象
     */
    Page<SalesCategoryPageVo> query(@NotNull Pageable pageable, @NotNull @Valid SalesCategorySearchParam salesCategorySearchParam);

    /**
     * 根据门店ID，初始化门店销售分类
     * @param shopId 门店ID
     */
    void saveSalesCategoryForShopCreate(@NotNull Long shopId);

    /**
     * 查找当前门店已经启用的销售分类
     * @param shopId
     * @return
     */
    List<SalesCategorySimpleVo> listForGoodsAdd(@NotNull Long shopId);


    SalesCategory findOne(@NotNull Long id);

    /**
     * 判断销售分类是否存在
     * @param id
     * @param shopId
     * @param categoryName
     * @return
     */
    Boolean checkSalesCategory(Long id, @NotNull Long shopId, @NotEmpty String categoryName);

    /**
     * 查找当前门店已经启用的销售分类
     * @param shopId
     * @param isEnable
     * @param isFrontendShow
     * @return
     */
    List<SalesCategorySimpleVo> listSalesCategory(@NotNull Long shopId, String isEnable, String isFrontendShow);
}
