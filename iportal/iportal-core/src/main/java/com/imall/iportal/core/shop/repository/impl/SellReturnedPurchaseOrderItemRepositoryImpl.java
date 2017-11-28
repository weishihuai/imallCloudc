
package com.imall.iportal.core.shop.repository.impl;

import com.imall.iportal.core.shop.repository.SellReturnedPurchaseOrderItemRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * 销售 退货 订单 项(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SellReturnedPurchaseOrderItemRepositoryImpl implements SellReturnedPurchaseOrderItemRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
