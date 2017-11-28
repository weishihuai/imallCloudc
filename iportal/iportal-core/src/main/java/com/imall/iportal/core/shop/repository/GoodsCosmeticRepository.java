
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.GoodsCosmetic;
/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsCosmeticRepository extends  IBaseRepository<GoodsCosmetic, Long>,GoodsCosmeticRepositoryCustom {


    /**
     * 通过goodsId查找
     * @param id
     * @return
     */
    GoodsCosmetic findByGoodsId(Long id);
}

