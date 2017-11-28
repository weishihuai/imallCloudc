
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.SkuLockStock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * SKU_锁_库存(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SkuLockStockRepository extends  IBaseRepository<SkuLockStock, Long>,SkuLockStockRepositoryCustom {

    List<SkuLockStock> findByOrderNum(String orderNum);

}

