
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OrderRepository extends  IBaseRepository<Order, Long>,OrderRepositoryCustom {

    /**
     * 根据微信用户ID查询订单信息(微信端使用)
     *
     * @param weChatUserId    微信用户ID
     * @param orderSourceCode 订单来源代码
     * @return
     */
    @Query("select o from Order o where o.wechatUserId =?1 and o.orderSourceCode= ?2")
    List<Order> listOrderByWeChatUserId(Long weChatUserId, String orderSourceCode);

    @Query("SELECT O FROM Order O WHERE O.weShopId = ?1 AND O.id = ?2")
    Order findOne(Long weShopId, Long id);

    Order findByIdAndWechatUserId(Long id, Long weChatUserId);
}

