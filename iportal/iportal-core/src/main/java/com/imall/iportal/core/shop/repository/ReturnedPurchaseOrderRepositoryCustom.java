
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrder;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ReturnedPurchaseOrderRepositoryCustom{

    Page<ReturnedPurchaseOrder> query(Pageable pageable, ReturnedPurchaseOrderSearchParams params);
}

