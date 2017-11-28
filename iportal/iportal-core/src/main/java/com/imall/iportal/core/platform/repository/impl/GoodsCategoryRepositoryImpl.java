
package com.imall.iportal.core.platform.repository.impl;

import com.imall.iportal.core.platform.repository.GoodsCategoryRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;

/**
 * 商品 分类(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class GoodsCategoryRepositoryImpl implements GoodsCategoryRepositoryCustom{

    @Resource
    private EntityManager entityManager;

}
