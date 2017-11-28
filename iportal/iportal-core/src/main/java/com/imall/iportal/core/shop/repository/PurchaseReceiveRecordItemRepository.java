
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.PurchaseReceiveRecordItem;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseReceiveRecordItemRepository extends IBaseRepository<PurchaseReceiveRecordItem, Long>, PurchaseReceiveRecordItemRepositoryCustom {

    List<PurchaseReceiveRecordItem> findByPurchaseReceiveRecordId(Long purchaseReceiveRecordId);

    List<PurchaseReceiveRecordItem> findByPurchaseReceiveRecordIdAndIsAcceptance(Long purchaseReceiveRecordId, String isAcceptance);

    List<PurchaseReceiveRecordItem> findByPurchaseOrderIdAndIsAcceptance(Long purchaseOrderId, String isAcceptance);
}

