package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.SellReturnedPurchaseOrder;
import com.imall.iportal.core.shop.valid.SellReturnedPurchaseOrderSaveValid;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 销售 退货 订单(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SellReturnedPurchaseOrderService{

    SellReturnedPurchaseOrder findOne(@NotNull Long id);

    SellReturnedPurchaseOrder save(SellReturnedPurchaseOrder order);

    /**
     * 查询销售退货记录详情
     * @param id 退货订单ID
     * @param shopId 子公司ID
     * @return
     */
    SellReturnedPurchaseOrderDetailVo findDetail(@NotNull Long id, @NotNull Long shopId);

    /**
     * 查询销售退货记录
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<SellReturnedPurchaseOrderPageVo> query(@NotNull Pageable pageable, @Valid @NotNull SellReturnedPurchaseOrderSearchParam searchParam);

    /**
     * 查找销售退货订单数据插入到索引队列
     *
     * @return 插入索引队列订单数量
     */
    Integer queryOrderToQueue();


    /**
     * 计算退货金额
     * @param shopId
     * @param cashierId
     * @param fromReturnedPurchaseTime
     * @param toReturnedPurchaseTime
     * @return
     */
    Double sumReturnedAmountByShopIdAndCashierId(Long shopId, Long cashierId, Date fromReturnedPurchaseTime, Date toReturnedPurchaseTime);

    /**
     * 分页查询订单信息（订单弹窗公共组件）,可退货订单
     * @param pageable
     * @param orderSearchParam
     * @return
     */
    Page<SRPReadyOrderPageVo> commonOrderQuery(@NotNull Pageable pageable, @NotNull @Valid OrderSearchParam orderSearchParam);

    /**
     * 销售POS前端--退货：结算提交
     * @param returnedPurchaseOrderSaveValid
     */
    void saveReturnedCalc(@NotNull @Valid SellReturnedPurchaseOrderSaveValid returnedPurchaseOrderSaveValid);


}
