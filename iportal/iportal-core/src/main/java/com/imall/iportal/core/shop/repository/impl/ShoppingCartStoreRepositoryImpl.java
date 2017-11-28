
package com.imall.iportal.core.shop.repository.impl;

import com.imall.iportal.core.shop.repository.ShoppingCartStoreRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class ShoppingCartStoreRepositoryImpl implements ShoppingCartStoreRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
