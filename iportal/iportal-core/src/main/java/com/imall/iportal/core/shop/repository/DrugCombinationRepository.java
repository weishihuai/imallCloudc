
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DrugCombination;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCombinationRepository extends  IBaseRepository<DrugCombination, Long>,DrugCombinationRepositoryCustom {

    /**
     * 统计使用某一联合用药分类的使用次数
     * @param drugCombinationCategoryId
     * @return
     */
    @Query("SELECT COUNT(D) FROM DrugCombination D WHERE D.drugCombinationCategoryId = ?1")
    Integer countByCategoryId(Long drugCombinationCategoryId);

    @Query("SELECT D FROM DrugCombination D WHERE D.orgId = ?1 AND D.id = ?2")
    DrugCombination findOne(Long orgId, Long id);

    @Query("SELECT D FROM DrugCombination D WHERE D.id = ?1")
    DrugCombination findOne(Long id);
}

