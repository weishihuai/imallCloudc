package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysOrg;
import com.imall.iportal.core.main.repository.SysOrgRepositoryCustom;
import com.imall.iportal.core.main.vo.SysOrgVo;
import org.apache.commons.lang3.StringUtils;
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
 * Created by Donie on 2015/12/30.
 */
public class SysOrgRepositoryImpl implements SysOrgRepositoryCustom {


    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysOrgVo> query(Pageable pageable, Long parentId, String orgName, String orgCode, String phone) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(orgName)) {
            sql.append(" and a.ORG_NAME LIKE :orgName");
            paramMap.put(SysOrg.ORG_NAME, "%" + orgName + "%");
        }
        if (StringUtils.isNotBlank(orgCode)) {
            sql.append(" and a.ORG_CODE LIKE :orgCode");
            paramMap.put(SysOrg.ORG_CODE, "%" + orgCode + "%");
        }
        if (StringUtils.isNotBlank(phone)) {
            sql.append(" and a.PHONE LIKE :phone");
            paramMap.put(SysOrg.PHONE, "%" + phone + "%");
        }
        sql.append(" and a.PARENT_ID=:parentId");
        paramMap.put(SysOrg.PARENT_ID, parentId);

        Query query = entityManager.createNativeQuery("SELECT * FROM T_PT_SYS_ORG a where 1=1" + sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysOrgVo> list = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            list.add(CommonUtil.copyFromDbMap(objectMap, new SysOrgVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_ORG a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysOrgVo>(list, pageable, total);
    }
}
