
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.GoodsSplitZero;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsSplitZeroRepository extends  IBaseRepository<GoodsSplitZero, Long>,GoodsSplitZeroRepositoryCustom {

    List<GoodsSplitZero> findByGoodsId(Long goodsId);

    @Query("SELECT G FROM GoodsSplitZero G WHERE G.shopId = ?1 AND G.id = ?2")
    GoodsSplitZero findOne(Long shopId, Long id);
}

