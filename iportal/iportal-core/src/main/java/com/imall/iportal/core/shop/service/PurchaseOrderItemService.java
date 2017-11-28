package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.PurchaseOrderItem;
import com.imall.iportal.core.shop.vo.PurchaseOrderItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderSearchParams;
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
public interface PurchaseOrderItemService {

    PurchaseOrderItem save(PurchaseOrderItem entity);

    /**
     * 根据采购订单找出采购订单项
     *
     * @param purchaseOrderId
     * @return
     */
    List<PurchaseOrderItem> findByPurchaseOrderId(@NotNull Long purchaseOrderId);

    /**
     * 根据采购订单ID和商品ID查找
     *
     * @param purchaseOrderId
     * @param goodsId
     * @return
     */
    PurchaseOrderItem findByPurchaseOrderIdAndGoodsId(@NotNull Long purchaseOrderId, @NotNull Long goodsId);

    /**
     * 根据采购订单ID，检查订单项是否全部已收货
     *
     * @param purchaseOrderId
     * @return
     */
    Boolean checkIsAllReceive(@NotNull Long purchaseOrderId);

    /**
     * 根据采购订单id和是否收货找出采购订单项
     *
     * @param purchaseOrderId
     * @return
     */
    List<PurchaseOrderItem> findByPurchaseOrderIdAndIsReceive(@NotNull Long purchaseOrderId, @NotEmpty String isReceive);

    /**
     * 采购记录明细查询
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<PurchaseOrderItemPageVo> query(@NotNull Pageable pageable, @Valid @NotNull PurchaseOrderSearchParams searchParams);
}
