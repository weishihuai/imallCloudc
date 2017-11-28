package com.imall.iportal.core.shop.service;


import com.imall.commons.dicts.PayWayTypeCodeEnum;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.entity.Order;
import com.imall.iportal.core.shop.valid.NormalOrderPaySaveValid;
import com.imall.iportal.core.shop.valid.NormalOrderSaveValid;
import com.imall.iportal.core.shop.valid.SalesPosOrderSaveValid;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface OrderService{

    Order findOne(@NotNull Long id);

    /**
     * salesPos端 收银结算
     * @param currUserVo 开单人
     * @param shoppingCart 购物车
     * @param salesPosOrderSaveValid
     * @return
     */
    Long saveSalesPosOrder(@NotNull CurrUserVo currUserVo, @NotNull ShoppingCart shoppingCart, @NotNull @Valid SalesPosOrderSaveValid salesPosOrderSaveValid);

    /**
     * 微信订单 下单
     * @param shoppingCart 购物车
     * @param normalOrderSaveValid
     * @return
     */
    Order saveNormalOrder(@NotNull ShoppingCart shoppingCart, @NotNull @Valid NormalOrderSaveValid normalOrderSaveValid);

    /**
     * 微信下单，货到付款，支付，确认送达
     * @param openOrderManId 送货收款人
     * @return
     */
    Long saveCodPay(@NotNull Long openOrderManId, @NotNull @Valid NormalOrderPaySaveValid normalOrderPaySaveValid);


    /**
     * 查找订单数据插入到索引队列
     *
     * @return 插入索引队列订单数量
     */
    Integer queryOrderToQueue();

    /**
     * 分页查询订单信息（订单弹窗公共组件）
     * @param pageable
     * @param orderSearchParam
     * @return
     */
    Page<OrderPageVo> commonOrderQuery(@NotNull Pageable pageable, @NotNull @Valid OrderSearchParam orderSearchParam);

    /**
     * 销售pos端--销售报表：计算某个门店的订单总金额(实收金额)
     * @param orderSearchParam
     * @return
     */
    Double sumOrderTotalAmountByShopId(@NotNull @Valid OrderSearchParam orderSearchParam);

    /**
     * 销售pos端--收银交班：计算某个门店的订单总金额（收款金额）
     * @param shopId
     * @param openOrderManId
     * @param formCreateDate
     * @param toCreateDate
     * @return
     */
    Double sumOrderTotalAmountByShopIdAndPosMan(@NotNull Long shopId, @NotNull Long openOrderManId, @NotNull Date formCreateDate, @NotNull Date toCreateDate);

    /**
     * 销售pos端--收银交班：计算某个门店的订单总金额（收款金额）
     * @param shopId
     * @param openOrderManId
     * @param formCreateDate
     * @param toCreateDate
     * @return
     */
    Long sumOrderTotalQuantityByShopIdAndPosMan(@NotNull Long shopId, @NotNull Long openOrderManId, @NotNull Date formCreateDate, @NotNull Date toCreateDate);

    /**
     * 销售pos端--收银交班：计算某个门店的订单退货金额
     * @param shopId
     * @param openOrderManId
     * @param fromReturnedPurchaseTime
     * @param toReturnedPurchaseTime
     * @return
     */
    Double sumOrderReturnedPurchaseAmountByShopIdAndPosMan(@NotNull Long shopId, @NotNull Long openOrderManId, @NotNull Date fromReturnedPurchaseTime, @NotNull Date toReturnedPurchaseTime);

    /**
     * 销售pos端--收银交班：计算某个门店的订单的某种支付方式的总金额，注意：找零 另计算。
     * @param shopId
     * @param openOrderManId
     * @param formCreateDate
     * @param toCreateDate
     * @param payWayTypeCode
     * @return
     */
    Double sumOrderPayWayAmountByShopIdAndPosMan(@NotNull Long shopId, @NotNull Long openOrderManId, @NotNull Date formCreateDate, @NotNull Date toCreateDate, @NotNull PayWayTypeCodeEnum payWayTypeCode);


    /**
     * 销售pos端--收银交班：计算找零 。
     * @param shopId
     * @param openOrderManId
     * @param formCreateDate
     * @param toCreateDate
     * @return
     */
    Double sumOrderReturnSmallAmountByShopIdAndPosMan(@NotNull Long shopId, @NotNull Long openOrderManId, @NotNull Date formCreateDate, @NotNull Date toCreateDate);


    /**
     * 销售pos端--收银交班：计算某个门店的订单的医保收取的总金额
     * @param shopId
     * @param openOrderManId
     * @param formCreateDate
     * @param toCreateDate
     * @return
     */
    Double sumOrderMedicalInsuranceAmountByShopIdAndPosMan(@NotNull Long shopId, @NotNull Long openOrderManId, @NotNull Date formCreateDate, @NotNull Date toCreateDate);

    /**
     * 订单统计
     * @param searchParams
     */
    OrderStatVo getOrderStatVo(@NotNull @Valid SellSummarySearchParams searchParams);

    /**
     * 销售汇总查询
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<SellSummaryPageVo> querySellSummary(@NotNull Pageable pageable, @Valid @NotNull SellSummarySearchParams searchParams);

    /**
     * 门店或微店--统计销售情况
     * @param orderSourceCode   订单来源
     * @param id    门店或微店 ID
     * @return
     */
    OrderStatisticsVo getOrderStatistics(@NotBlank String orderSourceCode, Long id);

    /**
     * 根据微信用户ID查询订单信息(微信端使用)
     *
     * @param weChatUserId    微信用户ID
     * @param orderSourceCode 订单来源代码
     * @return
     */
    List<Order> listOrderByWeChatUserId(@NotNull Long weChatUserId,@NotBlank String orderSourceCode);


    /**
     * @param weShopId 微店 ID
     * @param id 订单 ID
     * @return
     */
    Order findOne(@NotNull Long weShopId, @NotNull Long id);

    /**
     * 更新
     * @param order
     */
    Order update(@NotNull Order order);

    Order save(@NotNull Order order);

    /**
     * 获取微信用户订单
     * @param wechatUserId
     * @return
     */
    Page<Order> query(@NotNull Pageable pageable, @NotNull Long wechatUserId);

    /**
     * 根据主键和微信用户ID查找
     * @param id
     * @param weChatUserId
     * @return
     */
    Order findByIdAndWeChatUserId(@NotNull Long id, @NotNull Long weChatUserId);

    /**
     * 查询交班期间订单
     * @param pageable
     * @param shopId
     * @param openOrderManId
     * @param formCreateDateString
     * @param toCreateDateString
     * @return
     */
    Page<OrderPageVo> findShiftRangeOrder(@NotNull Pageable pageable, @NotNull Long shopId, @NotNull Long openOrderManId, @NotBlank String formCreateDateString, @NotBlank String toCreateDateString) throws ParseException;

    /**
     * 拒收订单
     * @param order
     */
    void updateRejectOrder(Order order);
}
