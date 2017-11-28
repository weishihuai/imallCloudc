
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.PurchaseOrderItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseOrderItemRepositoryCustom{

    Page<PurchaseOrderItemPageVo> query(Pageable pageable, PurchaseOrderSearchParams searchParams);
}

