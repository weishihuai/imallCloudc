
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 销售 退货 订单(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SellReturnedPurchaseOrderRepository extends  IBaseRepository<SellReturnedPurchaseOrder, Long>,SellReturnedPurchaseOrderRepositoryCustom {


    List<SellReturnedPurchaseOrder> findByOrderId(Long id);
}

