package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.OrderItem;
import com.imall.iportal.core.shop.vo.OrderItemVo;
import com.imall.iportal.core.shop.vo.SellDetailPageVo;
import com.imall.iportal.core.shop.vo.SellSummarySearchParams;
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
public interface OrderItemService {

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    OrderItem findOne(@NotNull Long id);

    /**
     * 找出订单里面所有的商品数量
     * @param orderId
     * @return
     */
    Long findOrderTotalGoodsQuantity(@NotNull Long orderId);

    /**
     * 根据订单ID查找订单项
     * @param orderId 订单ID
     * @return
     */
    List<OrderItem> findByOrderId(@NotNull Long orderId);

    /**
     * 根据订单ID查找订单项
     * @param orderId 订单ID
     * @return
     */
    List<OrderItemVo> findVoByOrderId(@NotNull Long orderId);

    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    OrderItem save(OrderItem orderItem);

    /**
     * 销售明细查询
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<SellDetailPageVo> querySellDetailPageVo(@NotNull Pageable pageable, @Valid @NotNull SellSummarySearchParams searchParams);
}
