package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysResourceUrl;
import com.imall.iportal.core.main.repository.SysResourceUrlRepositoryCustom;
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

;

/**
 * Created by ygw on 2015/12/31.
 */
public class SysResourceUrlRepositoryImpl implements SysResourceUrlRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysResourceUrl> query(Pageable pageable, Long resourceId, String url) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (resourceId!=null) {
            sql.append(" and a.RESOURCE_ID = :resourceId ");
            paramMap.put(SysResourceUrl.RESOURCE_ID, resourceId);
        }
        if (StringUtils.isNotBlank(url)) {
            sql.append(" and a.URL LIKE :url");
            paramMap.put(SysResourceUrl.URL, "%" + url + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT * FROM T_PT_SYS_RESOURCE_URL a where 1=1" + sql, SysResourceUrl.class);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<SysResourceUrl> resultList = query.getResultList();

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_RESOURCE_URL a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();
        return new PageImpl<SysResourceUrl>(resultList, pageable, total);
    }
}
