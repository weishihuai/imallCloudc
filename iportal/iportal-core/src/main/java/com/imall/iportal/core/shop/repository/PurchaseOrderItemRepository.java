
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.PurchaseOrderItem;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseOrderItemRepository extends IBaseRepository<PurchaseOrderItem, Long>, PurchaseOrderItemRepositoryCustom {

    List<PurchaseOrderItem> findByPurchaseOrderId(Long purchaseOrderId);

    List<PurchaseOrderItem> findByPurchaseOrderIdAndGoodsId(Long purchaseOrderId, Long goodsId);

    List<PurchaseOrderItem> findByPurchaseOrderIdAndIsReceive(Long purchaseOrderId, String isReceive);
}

