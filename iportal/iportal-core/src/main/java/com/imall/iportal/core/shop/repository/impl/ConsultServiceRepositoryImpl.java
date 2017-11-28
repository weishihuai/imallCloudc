
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.ConsultService;
import com.imall.iportal.core.shop.repository.ConsultServiceRepositoryCustom;
import com.imall.iportal.core.shop.vo.ConsultServiceSearchParam;
import org.apache.commons.lang3.StringUtils;
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
public class ConsultServiceRepositoryImpl implements ConsultServiceRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<ConsultService> query(Pageable pageable, ConsultServiceSearchParam searchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_consult_service a where 1=1 ");
        sql.append(" and a.SHOP_ID = :shopId");
        paramMap.put(ConsultService.SHOP_ID, searchParam.getShopId());

        if(searchParam.getFromDate() != null) {
            sql.append(" and a.CONSULT_TIME >= :startDate");
            paramMap.put("startDate", searchParam.getFromDate());
        }

        if(searchParam.getToDate() != null) {
            sql.append(" and a.CONSULT_TIME <= :endDate");
            paramMap.put("endDate", searchParam.getToDate());

        }

        //接口按需要进行改进，若大规模使用，需调整为ES搜索
        if (StringUtils.isNotBlank(searchParam.getSearchFields())) {
            sql.append(" and a.ID in " +
                    "(select t.CONSULT_SERVICE_ID from t_shp_consult_goods_inf t where t.GOODS_ID in " +
                    "(select g.id from t_shp_goods g where g.SHOP_ID = :shopId and ( g.GOODS_CODE like :searchFields or g.GOODS_NM like :searchFields1 or g.PINYIN like :searchFields1 or g.COMMON_NM like :searchFields1))) ");
            paramMap.put("searchFields", "%" + searchParam.getSearchFields() + "%");
            paramMap.put("searchFields1", "%" + searchParam.getSearchFields() + "%");
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, ConsultService.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<ConsultService>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());

    }

}
