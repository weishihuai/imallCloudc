
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.MaintainingRecord;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface MaintainingRecordRepository extends  IBaseRepository<MaintainingRecord, Long>,MaintainingRecordRepositoryCustom {

    @Query("SELECT M FROM MaintainingRecord M WHERE M.shopId = ?1 AND M.id = ?2")
    MaintainingRecord findOne(Long shopId, Long id);

}

