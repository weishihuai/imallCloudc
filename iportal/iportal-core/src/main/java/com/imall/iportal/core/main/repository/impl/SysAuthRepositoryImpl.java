package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysAuth;
import com.imall.iportal.core.main.entity.SysRole;
import com.imall.iportal.core.main.repository.SysAuthRepositoryCustom;
import com.imall.iportal.core.main.vo.SysRoleVo;
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
 * Created by ygw on 2015/12/31.
 */
public class SysAuthRepositoryImpl implements SysAuthRepositoryCustom {


    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysRoleVo> query(Pageable pageable, Long jobId, String roleName, String roleDescription)  {
        StringBuilder sql = new StringBuilder();
        /*sql.append(" from t_pt_sys_auth a, t_pt_sys_role r where 1=1 and a.ROLE_ID=r.id");*/
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sql.append(" and a.JOB_ID = :jobId");
        paramMap.put(SysAuth.JOB_ID, jobId);
        if (StringUtils.isNotBlank(roleName)) {
            sql.append(" and r.ROLE_NAME LIKE :roleName");
            paramMap.put(SysRole.ROLE_NAME, "%" + roleName + "%");
        }
        if (StringUtils.isNotBlank(roleDescription)) {
            sql.append(" and r.DESCRIPTION LIKE :roleDescription ");
            paramMap.put(SysRole.DESCRIPTION, "%" + roleDescription + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT r.* FROM t_pt_sys_auth a, t_pt_sys_role r where 1=1 and a.ROLE_ID=r.id" + sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysRoleVo> resultList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            resultList.add(CommonUtil.copyFromDbMap(objectMap, new SysRoleVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*)  FROM t_pt_sys_auth a, t_pt_sys_role r where 1=1 and a.ROLE_ID=r.id" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysRoleVo>(resultList, pageable, total);
    }


}
