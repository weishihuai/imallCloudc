
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseOrderRepository extends  IBaseRepository<PurchaseOrder, Long>,PurchaseOrderRepositoryCustom {

    PurchaseOrder findByIdAndShopId(Long id, Long shopId);

    @Query("select count(*) from PurchaseOrder o where o.createDate >= ?1 and o.createDate <= ?2 and o.shopId=?3")
    Long countByCreateDate(Date start, Date end, Long shopId);
}

