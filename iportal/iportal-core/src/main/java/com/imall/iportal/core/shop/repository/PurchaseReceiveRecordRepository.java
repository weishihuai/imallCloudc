
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseReceiveRecordRepository extends IBaseRepository<PurchaseReceiveRecord, Long>, PurchaseReceiveRecordRepositoryCustom {

    PurchaseReceiveRecord findByIdAndShopId(Long id, Long shopId);

    @Query("select count(*) from PurchaseReceiveRecord r where r.createDate >= ?1 and r.createDate <= ?2 and r.shopId=?3")
    Long countByCreateDate(Date start, Date end, Long shopId);

    @Query("select r.id from PurchaseReceiveRecord r where r.purchaseOrderId=?1")
    List<Long> getIdListByPurchaseOrderId(Long purchaseOrderId);
}

