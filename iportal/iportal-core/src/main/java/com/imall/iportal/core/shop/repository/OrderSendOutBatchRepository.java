
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.OrderSendOutBatch;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OrderSendOutBatchRepository extends  IBaseRepository<OrderSendOutBatch, Long>,OrderSendOutBatchRepositoryCustom {


    List<OrderSendOutBatch> findByOrderIdAndOrderItemId(Long orderId, Long orderItemId);

    List<OrderSendOutBatch> findByOrderId(Long orderId);
}

