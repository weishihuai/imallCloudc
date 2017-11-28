
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.DestroyRecord;
import com.imall.iportal.core.shop.repository.DestroyRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.DestroyRecordSearchParam;
import org.apache.commons.lang.StringUtils;
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
public class DestroyRecordRepositoryImpl implements DestroyRecordRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DestroyRecord> query(Pageable pageable, DestroyRecordSearchParam destroyRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_destroy_record R where 1=1 ");

        if (destroyRecordSearchParam.getShopId() != null) {
            sql.append(" AND R.SHOP_ID = :shopId ");
            paramMap.put(DestroyRecord.SHOP_ID, destroyRecordSearchParam.getShopId());
        }

        //商品编码、拼音码、名称
        if (StringUtils.isNotBlank(destroyRecordSearchParam.getKeyword())) {
            sql.append(" AND (R.GOODS_PINYIN LIKE :keyword OR R.COMMON_NM LIKE :keyword OR R.GOODS_NM LIKE :keyword OR R.GOODS_CODE like:goodsCode) ");
            paramMap.put("keyword", "%" + destroyRecordSearchParam.getKeyword().toLowerCase() + "%");
            paramMap.put("goodsCode","%" + destroyRecordSearchParam.getKeyword().toLowerCase() + "%");
        }

        //批号
        if (StringUtils.isNotBlank(destroyRecordSearchParam.getBatch())) {
            sql.append(" AND R.BATCH like:batch ");
            paramMap.put(DestroyRecord.BATCH,  "%" + destroyRecordSearchParam.getBatch() + "%");
        }

        //销毁时间
        if (StringUtils.isNotBlank(destroyRecordSearchParam.getFromDestroyTimeString())) {
            sql.append(" AND R.DESTROY_TIME >= :fromDestroyTime ");
            paramMap.put("fromDestroyTime", DateTimeUtils.getDayStartTime(destroyRecordSearchParam.getFromDestroyTimeString()));
        }

        if (StringUtils.isNotBlank(destroyRecordSearchParam.getToDestroyTimeString())) {
            sql.append(" AND R.DESTROY_TIME <= :toDestroyTime ");
            paramMap.put("toDestroyTime", DateTimeUtils.getDayEndTime(destroyRecordSearchParam.getToDestroyTimeString()));
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, DestroyRecord.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<DestroyRecord>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
