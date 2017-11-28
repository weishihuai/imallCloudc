
package com.imall.iportal.core.platform.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.platform.entity.Shop;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShopRepository extends  IBaseRepository<Shop, Long>,ShopRepositoryCustom {

    Shop findByOrgId(Long orgId);
}

