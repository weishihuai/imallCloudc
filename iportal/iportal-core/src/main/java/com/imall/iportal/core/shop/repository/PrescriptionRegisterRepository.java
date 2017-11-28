
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.PrescriptionRegister;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PrescriptionRegisterRepository extends  IBaseRepository<PrescriptionRegister, Long>,PrescriptionRegisterRepositoryCustom {

    @Query("SELECT P FROM PrescriptionRegister P WHERE P.shopId =?1 AND P.id =?2")
    PrescriptionRegister findOne(Long shopId, Long id);

}

