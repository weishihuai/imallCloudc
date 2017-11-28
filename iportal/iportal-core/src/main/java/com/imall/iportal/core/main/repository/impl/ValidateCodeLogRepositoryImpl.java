package com.imall.iportal.core.main.repository.impl;


import com.imall.iportal.core.main.repository.ValidateCodeLogRepositoryCustom;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * Created by lxh on 2017/6/28.
 */
public class ValidateCodeLogRepositoryImpl implements ValidateCodeLogRepositoryCustom {
    @Resource
    private EntityManager entityManager;
}
