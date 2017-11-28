package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrderItem;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderItemPageVo;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ReturnedPurchaseOrderItemService{

    ReturnedPurchaseOrderItem save(ReturnedPurchaseOrderItem entity);

    /**
     * 根据退货单主键查找退货项
     * @param returnedPurchaseOrderId
     * @return
     */
    List<ReturnedPurchaseOrderItem> findByReturnedPurchaseOrderId(@NotNull Long returnedPurchaseOrderId);

    /**
     * 查询退货项
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<ReturnedPurchaseOrderItemPageVo> query(@NotNull Pageable pageable, @Valid @NotNull ReturnedPurchaseOrderSearchParams searchParams);
}
