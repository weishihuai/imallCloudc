
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.LimitBuyRegister;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface LimitBuyRegisterRepository extends  IBaseRepository<LimitBuyRegister, Long>,LimitBuyRegisterRepositoryCustom {

    @Query("SELECT L FROM LimitBuyRegister L WHERE L.id = ?1 AND L.shopId = ?2")
    LimitBuyRegister findOne(Long id, Long shopId);

}

