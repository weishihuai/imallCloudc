
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.OrderItem;
import com.imall.iportal.core.shop.vo.SellSummarySearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OrderItemRepositoryCustom{

    Page<OrderItem> querySellDetailPageVo(Pageable pageable, SellSummarySearchParams searchParams);
}

