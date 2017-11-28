package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.repository.SysUserOrgJobRepositoryCustom;
import com.imall.iportal.core.main.vo.SysUserOrgJobVo;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygw on 2015/12/29.
 */
public class SysUserOrgJobRepositoryImpl implements SysUserOrgJobRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public List<SysUserOrgJobVo> findVoByUserId(Long userId) {
       String sql = "SELECT uoj.`id`,uoj.`ISMAIN`, uoj.`USER_ID`,uoj.`ORG_ID`,uoj.`JOB_ID`, u.`USER_NAME`,u.`REAL_NAME`,o.`ORG_CODE`, o.`ORG_NAME`,j.`JOB_CODE`, j.`JOB_NAME`  FROM t_pt_sys_user_org_job uoj,t_pt_sys_user u, t_pt_sys_org o,t_pt_sys_job j WHERE uoj.`USER_ID`=u.`id` AND uoj.`ORG_ID`=o.`id` AND uoj.`JOB_ID`=j.`id` AND uoj.`USER_ID`= :userId";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(SysUserOrgJob.USER_ID, userId);
        Query query = entityManager.createNativeQuery(sql);
        CommonUtil.formatQueryParameter(query, paramMap);

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysUserOrgJobVo> list = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            list.add(CommonUtil.copyFromDbMap(objectMap, new SysUserOrgJobVo()));
        }
        return list;
    }
}
