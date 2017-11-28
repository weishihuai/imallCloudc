package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SmsQueue;
import com.imall.iportal.core.main.repository.SmsQueueRepositoryCustom;
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
public class SmsQueueRepositoryImpl implements SmsQueueRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SmsQueue> query(Pageable pageable, String sendStatCode, Long sysOrgId) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put(SmsQueue.SEND_STAT_CODE, sendStatCode);
        sql.append(" from t_pt_sms_queue q where q.SEND_STAT_CODE =:sendStatCode");
        if (sysOrgId != null) {
            sql.append(" and q.SYS_ORG_ID =:sysOrgId");
            params.put(SmsQueue.SYS_ORG_ID, sysOrgId);
        }
        sql.append(" order by q.POSITION asc");
        String sqlString = sql.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sqlString);
        CommonUtil.formatQueryParameter(totalQ, params);
        Long total = ((BigInteger) totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select q.*" + sqlString, SmsQueue.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, params);
        return new PageImpl<SmsQueue>(resultQ.getResultList(), pageable, total);
    }
}
