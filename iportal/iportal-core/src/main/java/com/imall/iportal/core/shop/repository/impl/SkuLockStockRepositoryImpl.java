
package com.imall.iportal.core.shop.repository.impl;

import com.imall.iportal.core.shop.repository.SkuLockStockRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * SKU_锁_库存(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SkuLockStockRepositoryImpl implements SkuLockStockRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
