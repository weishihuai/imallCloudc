package com.imall.iportal.core.weshop.service;

import com.imall.iportal.core.shop.vo.OrderStatisticsVo;
import com.imall.iportal.core.weshop.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface WeShopOrderService {
    /**
     * 分页查询订单信息
     * @param pageable
     * @param orderSearchParam
     * @return
     */
    Page<OrderPageVo> query(@NotNull Pageable pageable, @NotNull @Valid OrderSearchParam orderSearchParam) throws ParseException;

    /**
     * 微店--统计销售情况
     * @param weShopId    微店 ID
     * @return
     */
    OrderStatisticsVo getOrderStatistics(Long weShopId);

    /**
     * 订单详情
     * @param weShopId
     * @param id
     * @return
     */
    OrderDetailVo findById(@NotNull Long weShopId, @NotNull Long id);

    /**
     * 订单状态更新
     * @param orderStateSaveVo
     */
    void updateOrderState(@NotNull @Valid OrderStateSaveVo orderStateSaveVo);

    /**
     * 更新订单备注
     * @param orderRemarkSaveVo
     */
    void updateOrderRemark(@NotNull @Valid OrderRemarkSaveVo orderRemarkSaveVo);

    /**
     * 获取待发货订单信息
     * @param weShopId
     * @param orderId
     * @return
     */
    OrderSendDetailVo findOrderItemSendDetail(@NotNull Long weShopId, @NotNull Long orderId);

    /**
     * 订单发货
     * @param orderSendVo
     */
    void updateOrderToSend(@NotNull @Valid OrderSendVo orderSendVo);

    /**
     * 打印订单小票
     * @param orderId   订单 ID
     * @param userName  操作人姓名
     */
    void printOrderSmallTicket(@NotNull Long orderId, @NotBlank String userName);

    /**
     * 获取微信用户订单
     * @param wechatUserId
     * @return
     */
    Page<OrderPageVo> query(@NotNull Pageable pageable, @NotNull Long wechatUserId);

    /**
     * 根据主键和微信用户ID查找
     * @param id
     * @param weChatUserId
     * @return
     */
    OrderDetailVo findByIdAndWeChatUserId(@NotNull Long id, @NotNull Long weChatUserId);
}
