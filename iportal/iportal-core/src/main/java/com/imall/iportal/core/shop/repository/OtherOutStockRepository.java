
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.OtherOutStock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OtherOutStockRepository extends IBaseRepository<OtherOutStock, Long>, OtherOutStockRepositoryCustom {

    /**
     * 查询出库信息
     *
     * @param outStockCode 出库单号
     * @param shopId       门店ID
     * @return
     */
    @Query("SELECT a FROM OtherOutStock a where a.outStockCode =?1 and a.shopId = ?2")
    List<OtherOutStock> listByOutStockCode(String outStockCode, Long shopId);

}

