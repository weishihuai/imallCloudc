
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.BadReactionRep;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface BadReactionRepRepository extends IBaseRepository<BadReactionRep, Long>, BadReactionRepRepositoryCustom {

    @Query("SELECT a FROM BadReactionRep a where a.id =?1 and a.shopId = ?2")
    BadReactionRep findByIdAndShopId(Long id, Long shopId);

}

