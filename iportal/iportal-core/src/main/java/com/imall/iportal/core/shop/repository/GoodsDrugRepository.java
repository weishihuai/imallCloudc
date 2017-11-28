
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.GoodsDrug;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsDrugRepository extends IBaseRepository<GoodsDrug, Long>, GoodsDrugRepositoryCustom {
    /**
     * 根据goodsId查看对象
     *
     * @param goodsId 商品id
     * @return
     */
    @Query("select g from GoodsDrug g where g.goodsId = ?1")
    GoodsDrug findByGoodsId(Long goodsId);
}

