
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseAcceptanceRecordItemRepository extends  IBaseRepository<PurchaseAcceptanceRecordItem, Long>,PurchaseAcceptanceRecordItemRepositoryCustom {

    List<PurchaseAcceptanceRecordItem> findByPurchaseAcceptanceRecordId(Long purchaseAcceptanceRecordId);
}

