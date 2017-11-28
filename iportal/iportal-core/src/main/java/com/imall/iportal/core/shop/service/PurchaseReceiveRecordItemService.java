package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.PurchaseReceiveRecordItem;
import com.imall.iportal.core.shop.vo.PurchaseReceiveRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseReceiveSearchParams;
import org.hibernate.validator.constraints.NotEmpty;
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
public interface PurchaseReceiveRecordItemService{

    PurchaseReceiveRecordItem save(PurchaseReceiveRecordItem entity);

    /**
     * 根据采购收货记录ID获取采购收货记录项
     *
     * @param purchaseReceiveRecordId
     * @return
     */
    List<PurchaseReceiveRecordItem> findByPurchaseReceiveRecordId(@NotNull Long purchaseReceiveRecordId);

    /**
     * 根据采购收货记录ID&是否验收查找
     * @param purchaseReceiveRecordId
     * @param isAcceptance
     * @return
     */
    List<PurchaseReceiveRecordItem> findByPurchaseReceiveRecordIdAndIsAcceptance(@NotNull Long purchaseReceiveRecordId, @NotEmpty String isAcceptance);

    PurchaseReceiveRecordItem findOne(@NotNull Long id);

    /**
     * 根据采购订单&是否验收查找
     * @param purchaseOrderId
     * @return
     */
    List<PurchaseReceiveRecordItem> findByPurchaseOrderIdAndIsAcceptance(@NotNull Long purchaseOrderId, @NotEmpty String isAcceptance);

    /**
     * 收货记录项搜索
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<PurchaseReceiveRecordItemPageVo> query(@NotNull Pageable pageable, @Valid @NotNull PurchaseReceiveSearchParams searchParams);
}
