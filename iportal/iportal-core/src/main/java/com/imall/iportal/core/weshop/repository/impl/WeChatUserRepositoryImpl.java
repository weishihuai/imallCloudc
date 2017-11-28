
package com.imall.iportal.core.weshop.repository.impl;

import com.imall.iportal.core.weshop.repository.WeChatUserRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class WeChatUserRepositoryImpl implements WeChatUserRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
