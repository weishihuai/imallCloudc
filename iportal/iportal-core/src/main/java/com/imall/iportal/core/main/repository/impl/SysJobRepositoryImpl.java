package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.repository.SysJobRepositoryCustom;
import com.imall.iportal.core.main.vo.SysJobVo;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donie on 2015/12/28.
 */
public class SysJobRepositoryImpl implements SysJobRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysJobVo> query(Pageable pageable,Long orgId) {

        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (orgId!=null) {
            sql.append(" and ORG_ID=:orgId  ");
            paramMap.put(SysJob.ORG_ID,orgId);
        }
        sql.append(" and IS_DEFAULT_ADMIN='N' ");
        sql.append(" order by ORDERBY desc ");

        Query query = entityManager.createNativeQuery("SELECT * FROM T_PT_SYS_JOB a where 1=1" + sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysJobVo> list = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            list.add(CommonUtil.copyFromDbMap(objectMap, new SysJobVo()));
        }


        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_JOB a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysJobVo>(list, pageable, total);

    }
}
