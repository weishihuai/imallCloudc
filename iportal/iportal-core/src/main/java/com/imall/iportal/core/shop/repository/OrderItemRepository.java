
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OrderItemRepository extends  IBaseRepository<OrderItem, Long>,OrderItemRepositoryCustom {

    List<OrderItem> findByOrderId(Long orderId);

    @Query("SELECT SUM(quantity) FROM OrderItem WHERE order_id = ?1")
    Long sumQuantityByOrderId(Long orderId);
}

