
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecord;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseAcceptanceRecordRepositoryCustom{

    Page<PurchaseAcceptanceRecord> query(Pageable pageable, PurchaseAcceptanceRecordSearchParams searchParams);
}

