package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.entity.SysResource;
import com.imall.iportal.core.main.repository.SysResourceRepositoryCustom;
import com.imall.iportal.core.main.vo.SysResourceVo;
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
public class SysResourceRepositoryImpl implements SysResourceRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysResourceVo> query(Pageable pageable, Long parentId, String resourceName, String permissionCode, String routerKey, String isAvailable) {
        //select r.*,a.APP_CNAME from t_pt_sys_resource r INNER JOIN (select APP_CNAME,id from t_pt_sys_app where id in(select APP_ID from  t_pt_sys_resource where id=2))a ON r.APP_ID = a.id;
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (parentId!=null) {
            sql.append(" and PARENT_ID = :parentId ");
            paramMap.put(SysResource.PARENT_ID, parentId);
        }
        if (StringUtils.isNotBlank(isAvailable)) {
            sql.append(" and r.IS_AVAILABLE = :isAvailable ");
            paramMap.put(SysResource.IS_AVAILABLE, BoolCodeEnum.fromCode(isAvailable).toCode());
        }
        if (StringUtils.isNotBlank(resourceName)) {
            sql.append(" and RESOURCE_NAME LIKE :resourceName");
            paramMap.put(SysResource.RESOURCE_NAME, "%" + resourceName + "%");
        }
        if (StringUtils.isNotBlank(permissionCode)) {
            sql.append(" and PERMISSION_CODE LIKE :permissionCode");
            paramMap.put(SysResource.PERMISSION_CODE, "%" + permissionCode + "%");
        }
        if (StringUtils.isNotBlank(routerKey)) {
            sql.append(" and ROUTER_KEY LIKE :routerKey");
            paramMap.put(SysResource.ROUTER_KEY, "%" + routerKey + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT r.*, a.APP_CNAME FROM t_pt_sys_resource r " +
                "INNER JOIN t_pt_sys_app a ON r.APP_ID = a.id WHERE 1=1"+ sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysResourceVo> list = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            list.add(CommonUtil.copyFromDbMap(objectMap, new SysResourceVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM t_pt_sys_resource r WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysResourceVo>(list, pageable, total);
    }
}
