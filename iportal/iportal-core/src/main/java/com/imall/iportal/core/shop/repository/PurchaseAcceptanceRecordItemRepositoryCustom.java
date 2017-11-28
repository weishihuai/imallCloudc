
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PurchaseAcceptanceRecordItemRepositoryCustom{

    Page<PurchaseAcceptanceRecordItem> queryReturnableItem(Pageable pageable, PurchaseAcceptanceRecordItemSearchParams searchParams);

    Page<PurchaseAcceptanceRecordItemPageVo> query(Pageable pageable, Long shopId, Long supplierId, String acceptanceOrderNum);
}

