
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.PurchaseReceiveRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseReceiveSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseReceiveRecordItemRepositoryCustom{

    Page<PurchaseReceiveRecordItemPageVo> query(Pageable pageable, PurchaseReceiveSearchParams searchParams);
}

