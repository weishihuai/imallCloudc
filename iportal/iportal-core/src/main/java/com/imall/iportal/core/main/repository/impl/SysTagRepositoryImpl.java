
package com.imall.iportal.core.main.repository.impl;

import com.imall.iportal.core.main.repository.SysTagRepositoryCustom;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * 标签(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysTagRepositoryImpl implements SysTagRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
