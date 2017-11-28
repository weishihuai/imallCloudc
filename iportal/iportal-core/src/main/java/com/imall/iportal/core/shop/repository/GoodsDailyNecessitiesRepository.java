
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsDailyNecessitiesRepository extends  IBaseRepository<GoodsDailyNecessities, Long>,GoodsDailyNecessitiesRepositoryCustom {


    /**
     * 通过GoodsId查找
     * @param id
     * @return
     */
    GoodsDailyNecessities findByGoodsId(Long id);
}

