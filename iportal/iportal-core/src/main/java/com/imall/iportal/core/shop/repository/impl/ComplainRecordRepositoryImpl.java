
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.ComplainRecord;
import com.imall.iportal.core.shop.repository.ComplainRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.ComplainRecordSearchParam;
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
 * @author by imall core generator
 * @version 1.0.0
 */
public class ComplainRecordRepositoryImpl implements ComplainRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<ComplainRecord> query(Pageable pageable, ComplainRecordSearchParam searchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_complain_record a where 1=1 ");
        sql.append(" and a.SHOP_ID = :shopId");
        paramMap.put(ComplainRecord.SHOP_ID, searchParam.getShopId());

        if(searchParam.getFromDate() != null) {
            sql.append(" and a.COMPLAIN_DATE >= :startDate");
            paramMap.put("startDate", searchParam.getFromDate());
        }

        if(searchParam.getToDate() != null) {
            sql.append(" and a.COMPLAIN_DATE < :endDate");
            paramMap.put("endDate", searchParam.getToDate());

        }

        if(StringUtils.isNotBlank(searchParam.getCustomerName())) {
            sql.append(" and a.CUSTOMER_NAME like :customerName");
            paramMap.put(ComplainRecord.CUSTOMER_NAME, "%" + searchParam.getCustomerName() + "%");

        }

        if (StringUtils.isNotBlank(searchParam.getSearchFields())) {
            sql.append(" and (a.GOODS_CODE = :searchFields or a.GOODS_NM like :searchFields1 or a.GOODS_PINYIN like :searchFields1) ");
            paramMap.put("searchFields", searchParam.getSearchFields());
            paramMap.put("searchFields1", "%" + searchParam.getSearchFields() + "%");
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select a.* " + sqlStr, ComplainRecord.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<ComplainRecord>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());

    }
}
