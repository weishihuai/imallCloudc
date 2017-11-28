
package com.imall.iportal.core.shop.repository.impl;

import com.imall.iportal.core.shop.repository.WeShopRepositoryCustom;
import javax.persistence.EntityManager;
import javax.annotation.Resource;
import javax.persistence.Query;

/**
 * 微门店(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class WeShopRepositoryImpl implements WeShopRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Integer queryWeShopToQueue() {
        Query query = entityManager.createNativeQuery("insert into t_pt_es_index_queue(object_id,index_type_code,execute_state,version)  select id,'we_shop','UN_PROCESSED',0 FROM t_shp_we_shop");
        return query.executeUpdate();
    }
}
