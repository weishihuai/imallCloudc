package com.imall.iportal.core.example.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.example.entity.SysDoc;
import com.imall.iportal.core.example.repository.SysDocRepositoryCustom;
import com.imall.iportal.core.example.vo.DocParamVo;
import com.imall.iportal.core.example.vo.SysDocVo;
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
public class SysDocRepositoryImpl implements SysDocRepositoryCustom {

    @Resource
    EntityManager entityManager;

    @Override
    public Page<SysDocVo> query(Pageable pageable, DocParamVo paramsVo) {
        BoolCodeEnum isAvailableEnum = StringUtils.isBlank(paramsVo.getIsAvailable()) ? null : BoolCodeEnum.fromCode(paramsVo.getIsAvailable());
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (isAvailableEnum!=null) {
            sql.append(" and a.IS_AVAILABLE = :isAvailable ");
            paramMap.put(SysDoc.IS_AVAILABLE, isAvailableEnum.toCode());
        }
        if (StringUtils.isNotBlank(paramsVo.getTitle())) {
            sql.append(" and a.TITLE LIKE :title");
            paramMap.put(SysDoc.TITLE, "%" + paramsVo.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(paramsVo.getSummary())) {
            sql.append(" and a.SUMMARY LIKE :summary");
            paramMap.put(SysDoc.SUMMARY, "%" + paramsVo.getSummary() + "%");
        }
        if (StringUtils.isNotBlank(paramsVo.getContStr())) {
            sql.append(" and a.CONT LIKE :cont");
            paramMap.put(SysDoc.CONT, "%" + paramsVo.getContStr() + "%");
        }
        Query query = entityManager.createNativeQuery("SELECT * FROM T_PT_SYS_DOC a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<SysDocVo> sysUserVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            sysUserVoList.add(CommonUtil.copyFromDbMap(objectMap,  new SysDocVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM T_PT_SYS_DOC u WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<SysDocVo>(sysUserVoList, pageable, total);
    }

    @Override
    public Integer queryDocToQueue() {
        Query query = entityManager.createNativeQuery("insert into t_pt_es_index_queue(object_id,index_type_code,execute_state,version)  select id,'sys_doc','UN_PROCESSED',0 FROM t_pt_sys_doc");
        return query.executeUpdate();
    }
}
