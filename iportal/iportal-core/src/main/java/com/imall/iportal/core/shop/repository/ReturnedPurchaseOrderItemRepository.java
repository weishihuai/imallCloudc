
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrderItem;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ReturnedPurchaseOrderItemRepository extends  IBaseRepository<ReturnedPurchaseOrderItem, Long>,ReturnedPurchaseOrderItemRepositoryCustom {

    List<ReturnedPurchaseOrderItem> findByReturnedPurchaseOrderId(Long returnedPurchaseOrderId);
}

