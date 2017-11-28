package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.core.main.repository.SysAppRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donie on 2015/12/16.
 */
public class SysAppRepositoryImpl implements SysAppRepositoryCustom {

    @Resource
    EntityManager entityManager;

    public Page<SysApp> query(Pageable pageable, String appName, String appCname, String hostname, String isAvailable)  {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(appName)) {
            sql.append(" and a.APP_NAME LIKE :appName");
            paramMap.put(SysApp.APP_NAME, "%" + appName + "%");
        }
        if (StringUtils.isNotBlank(appCname)) {
            sql.append(" and a.APP_CNAME LIKE :appCname ");
            paramMap.put(SysApp.APP_CNAME, "%" + appCname + "%");
        }
        if (StringUtils.isNotBlank(hostname)) {
            sql.append(" and HOSTNAME LIKE :hostname");
            paramMap.put(SysApp.HOSTNAME, "%" + hostname + "%");
        }
        if (StringUtils.isNotBlank(isAvailable)) {
            sql.append(" and IS_AVAILABLE=:isAvailable");
            paramMap.put(SysApp.IS_AVAILABLE, isAvailable);
        }

        Query query = entityManager.createNativeQuery("SELECT * FROM T_PT_SYS_APP a where 1=1" + sql, SysApp.class);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<SysApp> resultList = query.getResultList();

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_APP a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysApp>(resultList, pageable, total);
    }


}
