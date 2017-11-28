
package com.imall.iportal.core.main.repository.impl;

import com.imall.iportal.core.main.repository.FileMngRepositoryCustom;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class FileMngRepositoryImpl implements FileMngRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
