package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysDeveloperAuth;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.repository.SysDeveloperAuthRepositoryCustom;
import com.imall.iportal.core.main.vo.SysDeveloperAuthVo;
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
 * Created by ygw on 2017/12/12
 */
public class SysDeveloperAuthRepositoryImpl implements SysDeveloperAuthRepositoryCustom {

    @Resource
    EntityManager entityManager;

    @Override
    public Page<SysDeveloperAuthVo> query(Pageable pageable, String userName, String isAvailable) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(isAvailable)) {
            sql.append(" and a.IS_AVAILABLE=:isAvailable");
            paramMap.put(SysDeveloperAuth.IS_AVAILABLE, isAvailable);
        }

        if (StringUtils.isNotBlank(userName)) {
            sql.append(" and b.user_name like :userName");
            paramMap.put(SysUser.USER_NAME, "%" +userName+ "%");
        }

        sql.append(" and a.user_id = b.id");
        sql.append(" and a.app_id = c.id");

        Query query = entityManager.createNativeQuery("SELECT a.ID, a.USER_ID, a.APP_ID, b.USER_NAME, c.APP_NAME, a.HOSTNAME, a.IS_AVAILABLE FROM T_PT_SYS_DEVELOPER_AUTH a , T_PT_SYS_USER b, t_pt_sys_app c where 1=1" + sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysDeveloperAuthVo> sysDeveloperAuthVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            sysDeveloperAuthVoList.add(CommonUtil.copyFromDbMap(objectMap,  new SysDeveloperAuthVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_DEVELOPER_AUTH a , T_PT_SYS_USER b, t_pt_sys_app c  WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysDeveloperAuthVo>(sysDeveloperAuthVoList, pageable, total);
    }

    @Override
    public SysDeveloperAuthVo findByDeveloperAuthId(Long id) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (id != null) {
            sql.append(" and a.id = :id");
            paramMap.put(SysDeveloperAuth.ID, id);
        }
        sql.append(" and a.user_id = b.id");
        Query query = entityManager.createNativeQuery("SELECT a.ID,a.USER_ID,a.HOSTNAME, a.app_id, a.IS_AVAILABLE, b.USER_NAME FROM T_PT_SYS_DEVELOPER_AUTH a , T_PT_SYS_USER b  where 1=1" + sql);
        CommonUtil.formatQueryParameter(query, paramMap);

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysDeveloperAuthVo> developerAuthVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            developerAuthVoList.add(CommonUtil.copyFromDbMap(objectMap, new SysDeveloperAuthVo()));
        }

        return developerAuthVoList.isEmpty() ? null : developerAuthVoList.get(0);
    }
}
