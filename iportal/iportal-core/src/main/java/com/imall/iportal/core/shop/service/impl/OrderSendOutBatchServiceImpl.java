package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.shop.entity.OrderSendOutBatch;
import com.imall.iportal.core.shop.repository.OrderSendOutBatchRepository;
import com.imall.iportal.core.shop.service.OrderSendOutBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class OrderSendOutBatchServiceImpl extends AbstractBaseService<OrderSendOutBatch, Long> implements OrderSendOutBatchService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private OrderSendOutBatchRepository getOrderSendOutBatchRepository() {
		return (OrderSendOutBatchRepository) baseRepository;
	}

	@Override
	public List<OrderSendOutBatch> findByOrderIdAndOrderItemId(Long orderId, Long orderItemId) {
		return getOrderSendOutBatchRepository().findByOrderIdAndOrderItemId(orderId, orderItemId);
	}

	@Override
	public List<OrderSendOutBatch> findByOrderId(Long orderId) {
		return getOrderSendOutBatchRepository().findByOrderId(orderId);
	}
}