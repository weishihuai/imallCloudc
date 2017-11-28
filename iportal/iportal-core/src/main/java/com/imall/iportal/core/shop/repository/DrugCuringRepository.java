
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DrugCuring;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCuringRepository extends  IBaseRepository<DrugCuring, Long>,DrugCuringRepositoryCustom {

    @Query("select d from DrugCuring d where d.shopId = ?1 and d.id = ?2")
    DrugCuring findOne(Long shopId, Long id);

}

