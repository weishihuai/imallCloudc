package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrder;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ReturnedPurchaseOrderService{

    ReturnedPurchaseOrder findOne(@NotNull Long id);

    /**
     * 根据主键和门店ID获取购进退出单的打印模板内容
     * @param id
     * @param shopId
     * @return
     */
    ReturnedPurchasePrintTemplateVo getReturnedPurchasePrintTemplateVoByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 查找退货订单
     * @param pageable
     * @param params
     * @return
     */
    Page<ReturnedPurchaseOrderPageVo> query(@NotNull Pageable pageable, @Valid @NotNull ReturnedPurchaseOrderSearchParams params);

    /**
     * 根据主键和门店ID查找，详细页使用
     * @param id
     * @param shopId
     * @return
     */
    ReturnedPurchaseOrderVo findByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 保存退货订单
     * @param saveVo
     * @return
     */
    Long saveReturnedPurchaseOrder(@Valid @NotNull ReturnedPurchaseOrderSaveVo saveVo);
}
