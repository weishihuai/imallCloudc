package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.PurchaseOrder;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface PurchaseOrderService {

    /**
     * 采购订单列表查询
     *
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<PurchaseOrderPageVo> query(@NotNull Pageable pageable, @NotNull @Valid PurchaseOrderSearchParams searchParam);

    /**
     * 根据主键和门店ID获取采购订单的打印模板内容
     *
     * @param id
     * @param shopId
     * @return
     */
    PurchaseOrderPrintTemplateVo getPurchaseOrderPrintTemplateVoByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 根据ID和shopId查找，详细页使用
     *
     * @param id
     * @param shopId
     * @return
     */
    PurchaseOrderVo findByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 取消采购订单
     *
     * @param id
     * @param shopId
     */
    Long updateStatToCancel(@NotNull Long id, @NotNull Long shopId);

    /**
     * 保存采购订单
     *
     * @param saveVo
     * @return
     */
    Long savePurchaseOrder(@NotNull @Valid PurchaseOrderSaveVo saveVo);

    PurchaseOrder save(PurchaseOrder purchaseOrder);

    /**
     * 快速收货结口
     */
    Long saveFastReceive(@Valid @NotNull FastReceiveSaveVo saveVo) throws ParseException;

    /**
     * 采购收货时，找出采购订单信息
     *
     * @param id
     * @param shopId
     */
    PurchaseOrderVo findPurchaseReceiveInfo(@NotNull Long id, @NotNull Long shopId);

    /**
     * 根据ID和shopId查找
     *
     * @param id
     * @param shopId
     * @return
     */
    PurchaseOrder findPurchaseOrder(@NotNull Long id, @NotNull Long shopId);

    PurchaseOrder findOne(@NotNull Long id);
}
