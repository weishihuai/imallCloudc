
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.OutInStockLog;
import com.imall.iportal.core.shop.repository.OutInStockLogRepositoryCustom;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class OutInStockLogRepositoryImpl implements OutInStockLogRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public OutInStockLog findFirstLogAfterDate(Long shopId, Long goodsId, Date date) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from t_shp_out_in_stock_log a where 1=1");
        Map<String, Object> paramMap = new HashMap<>();

        sql.append(" and a.SHOP_ID = :shopId");
        paramMap.put(OutInStockLog.SHOP_ID, shopId);
        sql.append(" and a.GOODS_ID = :goodsId");
        paramMap.put(OutInStockLog.GOODS_ID, goodsId);

        if(date != null) {
            sql.append(" and a.CREATE_DATE >= :createDate");
            paramMap.put(OutInStockLog.CREATE_DATE, date);
        }

        sql.append(" order by a.CREATE_DATE asc");
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, OutInStockLog.class)
                .setFirstResult(0)
                .setMaxResults(1);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        List<OutInStockLog> resultList = resultQ.getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public Integer queryOutInStockLogToQueue() {
        Query query = entityManager.createNativeQuery("insert into t_pt_es_index_queue(object_id,index_type_code,execute_state,version)  select id,'out_in_stock_log','UN_PROCESSED',0 FROM t_shp_out_in_stock_log");
        return query.executeUpdate();
    }

}
