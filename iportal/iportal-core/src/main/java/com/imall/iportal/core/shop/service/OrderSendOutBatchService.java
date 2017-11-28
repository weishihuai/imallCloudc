package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.OrderSendOutBatch;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface OrderSendOutBatchService{

    OrderSendOutBatch findOne(@NotNull Long id);

    OrderSendOutBatch save(OrderSendOutBatch orderSendOutBatch);

    List<OrderSendOutBatch> findByOrderIdAndOrderItemId(Long orderId, Long orderItemId);

    /**
     * 通过订单ID查找
     * @param orderId
     * @return
     */
    List<OrderSendOutBatch> findByOrderId(Long orderId);
}
