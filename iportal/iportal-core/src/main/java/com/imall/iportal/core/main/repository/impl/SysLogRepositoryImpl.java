
package com.imall.iportal.core.main.repository.impl;

import com.imall.iportal.core.main.repository.SysLogRepositoryCustom;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * 系统 日志(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysLogRepositoryImpl implements SysLogRepositoryCustom {

    @Resource
    private EntityManager entityManager;

}
