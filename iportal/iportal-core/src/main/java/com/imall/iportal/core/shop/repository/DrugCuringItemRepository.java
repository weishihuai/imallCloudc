
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DrugCuringItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCuringItemRepository extends  IBaseRepository<DrugCuringItem, Long>,DrugCuringItemRepositoryCustom {

    /**
     * 根据药品养护 ID 查询相关药品信息
     * @param drugCuringId
     * @return
     */
    @Query("select d from DrugCuringItem d where d.shopId = ?1 and d.drugCuringId = ?2")
    List<DrugCuringItem> listByDrugCuringId(Long shopId, Long drugCuringId);

    @Query("select d from DrugCuringItem d where d.shopId = ?1 and d.id = ?2")
    DrugCuringItem findOne(Long shopId, Long id);
}

