
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.SalesCategory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 销售 分类(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SalesCategoryRepository extends  IBaseRepository<SalesCategory, Long>,SalesCategoryRepositoryCustom {

    /**
     * 通过门店id和是否启用查找销售分类
     * @param shopId
     * @param isEnable
     * @return
     */
    List<SalesCategory> findByShopIdAndIsEnable(Long shopId, String isEnable);

    /**
     * 通过分类名称查询销售分类
     * @param categoryName
     * @return
     */
    @Query("SELECT S FROM SalesCategory S WHERE S.shopId = ?1 AND S.categoryName = ?2")
    SalesCategory findByCategoryName(Long shopId, String categoryName);

    /**
     * 通过门店 ID 和销售分类 ID 获取销售分类信息
     * @param shopId
     * @param id
     * @return
     */
    @Query("SELECT S FROM SalesCategory S WHERE S.shopId = ?1 AND S.id = ?2")
    SalesCategory findOne(Long shopId, Long id);
}

