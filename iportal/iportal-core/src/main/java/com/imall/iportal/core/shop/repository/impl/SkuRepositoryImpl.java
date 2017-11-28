
package com.imall.iportal.core.shop.repository.impl;

import com.imall.iportal.core.shop.repository.SkuRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SkuRepositoryImpl implements SkuRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
