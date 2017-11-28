
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OrderRepositoryCustom {


    /**
     * 查找订单数据插入到索引队列
     * @return
     */
    Integer queryOrderToQueue();

    /**
     * 统计下单数量
     * @param id 门店或微店 ID
     * @param orderSourceCode 订单来源
     * @param minCreateDate
     * @return
     */
    Integer countOrderQuantity(Long id, String orderSourceCode, Date minCreateDate);

    /**
     * 统计成交金额
     * @param id 门店或微店 ID
     * @param orderSourceCode 订单来源
     * @param minCreateDate
     * @return
     */
    Double countTotalAmount(Long id, String orderSourceCode, Date minCreateDate);

    /**
     * 统计订单状态数量
     * @param id 门店或微店 ID
     * @param orderSourceCode 订单来源
     * @param orderStateCode 订单状态
     * @return
     */
    Integer countOrderStateQuantity(Long id, String orderSourceCode, String orderStateCode);

    /**
     * 微信端订单列表
     * @param pageable
     * @param wechatUserId
     * @return
     */
    Page<Order> query(Pageable pageable, Long wechatUserId);
}

