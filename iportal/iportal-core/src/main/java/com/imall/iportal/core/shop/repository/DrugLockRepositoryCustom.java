
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DrugLock;
import com.imall.iportal.core.shop.vo.DrugLockDealGoodsSearchParam;
import com.imall.iportal.core.shop.vo.ProblemDrugSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugLockRepositoryCustom {

    /**
     * 药品锁定列表
     *
     * @param pageable               分页对象
     * @param problemDrugSearchParam 查询参数
     * @return
     */
    Page<DrugLock> query(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam);

    /**
     * 药品处理列表
     *
     * @param pageable               分页对象
     * @param problemDrugSearchParam 查询参数
     * @return
     */
    Page<DrugLock> queryDrugLockDeal(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam);

    /**
     * 锁定商品列表
     *
     * @param pageable                     分页对象
     * @param drugLockDealGoodsSearchParam 搜索参数
     * @param shopId                       门店ID
     * @return
     */
    Page<DrugLock> queryDrugLockDealGoodsBatchList(Pageable pageable, DrugLockDealGoodsSearchParam drugLockDealGoodsSearchParam, Long shopId);

    /**
     * 药品销毁列表
     *
     * @param pageable               分页对象
     * @param problemDrugSearchParam 查询参数
     * @return
     */
    Page<DrugLock> queryDrugDestroyPage(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam);

}

