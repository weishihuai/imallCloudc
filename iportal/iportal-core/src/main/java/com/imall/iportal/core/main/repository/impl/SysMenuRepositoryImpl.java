package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysMenu;
import com.imall.iportal.core.main.repository.SysMenuRepositoryCustom;
import com.imall.iportal.core.main.vo.SysMenuVo;
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
 * Created by Donie on 2015/12/29.
 */
public class SysMenuRepositoryImpl implements SysMenuRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysMenuVo> query(Pageable pageable, Long parentId, String menuName) {
        StringBuilder sql = new StringBuilder();
        //select m.*,r.RESOURCE_NAME from t_pt_sys_menu  m INNER JOIN (select RESOURCE_NAME,id from t_pt_sys_resource where id=(select RESOURCE_ID from  t_pt_sys_menu where id=2))r ON m.RESOURCE_ID = r.id;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(menuName)) {
            sql.append(" and m.MENU_NAME LIKE :menuName");
            paramMap.put(SysMenu.MENU_NAME, "%" + menuName + "%");
        }
        sql.append(" and m.PARENT_ID=:parentId");
        paramMap.put(SysMenu.PARENT_ID, parentId);

        Query query = entityManager.createNativeQuery("SELECT m.*,r.RESOURCE_NAME FROM T_PT_SYS_MENU m " +
                "INNER JOIN t_pt_sys_resource r ON m.RESOURCE_ID = r.id WHERE 1=1"+ sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysMenuVo> list = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            list.add(CommonUtil.copyFromDbMap(objectMap, new SysMenuVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_MENU m WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();


        return new PageImpl<SysMenuVo>(list, pageable, total);
    }
}
