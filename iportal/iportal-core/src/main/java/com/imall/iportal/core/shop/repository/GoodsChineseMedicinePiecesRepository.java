
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.GoodsChineseMedicinePieces;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsChineseMedicinePiecesRepository extends  IBaseRepository<GoodsChineseMedicinePieces, Long>,GoodsChineseMedicinePiecesRepositoryCustom {

    /**
     * 通过商品id查找中药饮片
     * @param goodsId
     * @return
     */
    @Query("select g from GoodsChineseMedicinePieces g where g.goodsId = ?1")
    GoodsChineseMedicinePieces findByGoodsId(Long goodsId);
}

