
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.UseRecord;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface UseRecordRepository extends  IBaseRepository<UseRecord, Long>,UseRecordRepositoryCustom {

    @Query("SELECT U FROM UseRecord U WHERE U.shopId = ?1 AND U.id = ?2")
    UseRecord findOne(Long shopId, Long id);

}

