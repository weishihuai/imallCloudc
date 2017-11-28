package com.imall.iportal.core.shop.service;


import com.imall.commons.base.service.IBaseService;
import com.imall.iportal.core.shop.entity.DrugLock;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DrugLockService extends IBaseService<DrugLock, Long> {
    /**
     * 药品锁定列表
     *
     * @param pageable               分页对象
     * @param problemDrugSearchParam 搜索参数
     * @return
     */
    Page<DrugLockPageVo> query(Pageable pageable, @NotNull @Valid ProblemDrugSearchParam problemDrugSearchParam);

    /**
     * 药品处理列表
     *
     * @param pageable               分页对象
     * @param problemDrugSearchParam 搜索参数
     * @return
     */
    Page<DrugLockPageVo> queryDrugLockDeal(Pageable pageable, @NotNull @Valid ProblemDrugSearchParam problemDrugSearchParam);

    /**
     * 药品锁定 保存
     *
     * @param drugLockSaveVo 保存Vo对象
     */
    void saveDrugLock(@NotNull @Valid DrugLockSaveVo drugLockSaveVo) throws ParseException;

    /**
     * 对锁定药品进行处理
     *
     * @param drugLockDealUpdateVo 处理更新对象
     * @return
     */
    void updateDrugLockDeal(@NotNull @Valid DrugLockDealUpdateVo drugLockDealUpdateVo) throws ParseException;

    /**
     * 锁定商品列表
     *
     * @param pageable                     分页对象
     * @param drugLockDealGoodsSearchParam 搜索参数
     * @param shopId                       门店ID
     * @return
     */
    Page<DrugLockDealGoodsBatchPageVo> queryDrugLockDealGoodsBatchList(Pageable pageable, DrugLockDealGoodsSearchParam drugLockDealGoodsSearchParam, @NotNull Long shopId);

    /**
     * 药品销毁列表
     *
     * @param pageable               分页对象
     * @param problemDrugSearchParam 搜索参数
     * @return
     */
    Page<DrugDestroyPageVo> queryDrugDestroyPage(Pageable pageable, @NotNull @Valid ProblemDrugSearchParam problemDrugSearchParam);

    /**
     * 药品销毁
     *
     * @param id                主键ID
     * @param drugLockDestroyVo 销毁对象Vo
     * @return
     */
    void drugLockDestroy(@NotNull Long id, @NotNull @Valid DrugLockDestroyVo drugLockDestroyVo) throws ParseException;

}
