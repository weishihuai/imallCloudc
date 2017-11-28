
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DrugCheckItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCheckItemRepository extends  IBaseRepository<DrugCheckItem, Long>,DrugCheckItemRepositoryCustom {
    /**
     * 根据药品检查 ID 查询相关药品信息
     * @param drugCheckId
     * @return
     */
    @Query("select d from DrugCheckItem d where d.shopId = ?1 and d.drugCheckId = ?2")
    List<DrugCheckItem> listByDrugCheckId(Long shopId, Long drugCheckId);

    @Query("select d from DrugCheckItem d where d.shopId = ?1 and d.id = ?2")
    DrugCheckItem findOne(Long shopId, Long id);
}

