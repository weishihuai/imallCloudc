
package com.imall.iportal.core.shop.repository.impl;

import com.imall.iportal.core.shop.repository.ReceiverAddrRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * 收货 地址(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class ReceiverAddrRepositoryImpl implements ReceiverAddrRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
