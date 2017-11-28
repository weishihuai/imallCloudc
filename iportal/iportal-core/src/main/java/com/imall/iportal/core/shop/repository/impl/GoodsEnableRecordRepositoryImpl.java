
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.entity.GoodsEnableRecord;
import com.imall.iportal.core.shop.repository.GoodsEnableRecordRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class GoodsEnableRecordRepositoryImpl implements GoodsEnableRecordRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<GoodsEnableRecord> query(Pageable pageable, Long id, Long shopId) {

        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_goods_enable_record where 1=1 AND SHOP_ID =:shopId AND GOODS_ID=:goodsId  ");
        paramMap.put(GoodsEnableRecord.SHOP_ID, shopId);
        paramMap.put(GoodsEnableRecord.GOODS_ID, id);

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;

        String sqlStr = sql.append(" order by id ").toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, GoodsEnableRecord.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<GoodsEnableRecord>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());

    }
}
