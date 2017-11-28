
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.BadReactionRep;
import com.imall.iportal.core.shop.repository.BadReactionRepRepositoryCustom;
import com.imall.iportal.core.shop.vo.BadReactionRegSearchParam;
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
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class BadReactionRepRepositoryImpl implements BadReactionRepRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<BadReactionRep> query(Pageable pageable, BadReactionRegSearchParam badReactionRegSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_bad_reaction_rep  where 1=1 ");
        //门店ID
        if (badReactionRegSearchParam.getShopId() != null) {
            sql.append("and SHOP_ID =:shopId ");
            paramMap.put(BadReactionRep.SHOP_ID, badReactionRegSearchParam.getShopId());
        }

        //报告类型
        if (StringUtils.isNotEmpty(badReactionRegSearchParam.getRepType())) {
            sql.append("and REP_TYPE =:repType ");
            paramMap.put(BadReactionRep.REP_TYPE, badReactionRegSearchParam.getRepType());
        }

        //患者姓名
        if (StringUtils.isNotEmpty(badReactionRegSearchParam.getPatientName())) {
            sql.append("and PATIENT_NAME like:patientName ");
            paramMap.put(BadReactionRep.PATIENT_NAME,"%" +  badReactionRegSearchParam.getPatientName() + "%");
        }

        //报告时间
        if (StringUtils.isNotBlank(badReactionRegSearchParam.getRepStartDateString())) {
            sql.append(" and REP_DATE >= :fromDate");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(badReactionRegSearchParam.getRepStartDateString()));
        }

        if (StringUtils.isNotBlank(badReactionRegSearchParam.getRepEndDateString())) {
            sql.append(" and REP_DATE <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(badReactionRegSearchParam.getRepEndDateString()));
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, BadReactionRep.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<BadReactionRep>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
