
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.SellReturnedPurchaseOrderItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 销售 退货 订单 项(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SellReturnedPurchaseOrderItemRepository extends  IBaseRepository<SellReturnedPurchaseOrderItem, Long>,SellReturnedPurchaseOrderItemRepositoryCustom {


    @Query("select sum(o.returnedPurchaseQuantity) from SellReturnedPurchaseOrderItem o where o.orderItemId=?1")
    Long calcReturnedQuantity(Long orderItemId);

    List<SellReturnedPurchaseOrderItem> findByReturnedPurchaseOrderId(Long returnedPurchaseOrderId);

    @Query("select sum(returnedPurchaseQuantity) from SellReturnedPurchaseOrderItem  where returnedPurchaseOrderId=?1")
    Long sumSellReturnPurchaseGoodsQuantity(Long sellReturnPurchaseOrderId);
}

