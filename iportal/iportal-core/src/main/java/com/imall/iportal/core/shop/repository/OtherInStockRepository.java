
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.OtherInStock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OtherInStockRepository extends IBaseRepository<OtherInStock, Long>, OtherInStockRepositoryCustom {

    /**
     * 查询入库信息
     *
     * @param outStockCode 入库单号
     * @param shopId       门店ID
     * @return
     */
    @Query("SELECT a FROM OtherInStock a where a.inStockCode =?1 and a.shopId = ?2")
    List<OtherInStock> listByInStockCode(String outStockCode, Long shopId);

    /**
     * 查询批次入库信息
     * @param shopId    门店 ID
     * @param goodsBatchId  批次 ID
     * @return
     */
    @Query("SELECT a FROM OtherInStock a where a.shopId =?1 and a.goodsBatchId = ?2 order by a.id asc")
    List<OtherInStock> queryByGoodsBatchId(Long shopId, Long goodsBatchId);
}

