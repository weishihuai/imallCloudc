package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysDict;
import com.imall.iportal.core.main.repository.SysDictRepositoryCustom;
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
 * Created by Donie on 2015/12/28.
 */
public class SysDictRepositoryImpl implements SysDictRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysDict> query(Pageable pageable, String dictNm, String dictType, String isAvailable) {
        StringBuilder sql = new StringBuilder();
//        sql.append(" from t_pt_sys_dict a where 1=1 ");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(dictNm)) {
            sql.append(" and a.DICT_NM LIKE :dictNm");
            paramMap.put(SysDict.DICT_NM, "%" + dictNm + "%");
        }
        if (StringUtils.isNotBlank(dictType)) {
            sql.append(" and DICT_TYPE=:dictType");
            paramMap.put(SysDict.DICT_TYPE, dictType);
        }
        if (StringUtils.isNotBlank(isAvailable)) {
            sql.append(" and IS_AVAILABLE=:isAvailable");
            paramMap.put(SysDict.IS_AVAILABLE, isAvailable);
        }

        Query query = entityManager.createNativeQuery("SELECT * FROM T_PT_SYS_DICT a where 1=1" + sql, SysDict.class);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<SysDict> list = query.getResultList();

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_DICT a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysDict>(list, pageable, total);
    }
}
