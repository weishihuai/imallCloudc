package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface PurchaseAcceptanceRecordItemService {

    PurchaseAcceptanceRecordItem save(PurchaseAcceptanceRecordItem entity);

    /**
     * 根据采购验收主键获取采购验收项
     *
     * @param purchaseAcceptanceRecordId
     * @return
     */
    List<PurchaseAcceptanceRecordItem> findByPurchaseAcceptanceRecordId(@NotNull Long purchaseAcceptanceRecordId);

    /**
     * 查找可退货项
     *
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<PurchaseAcceptanceRecordItemPageVo> queryReturnableItem(@NotNull Pageable pageable, @Valid @NotNull PurchaseAcceptanceRecordItemSearchParams searchParams);

    PurchaseAcceptanceRecordItem findOne(@NotNull Long id);

    /**
     * 验收记录查询
     * @param pageable
     * @param shopId
     * @param supplierId
     * @param acceptanceOrderNum
     * @return
     */
    Page<PurchaseAcceptanceRecordItemPageVo> query(@NotNull Pageable pageable, @NotNull Long shopId, Long supplierId, String acceptanceOrderNum);
}
