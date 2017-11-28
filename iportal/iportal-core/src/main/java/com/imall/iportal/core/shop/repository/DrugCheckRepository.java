
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DrugCheck;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugCheckRepository extends  IBaseRepository<DrugCheck, Long>,DrugCheckRepositoryCustom {

    @Query("select d from DrugCheck d where d.shopId = ?1 and d.id = ?2")
    DrugCheck findOne(Long shopId, Long id);

}

