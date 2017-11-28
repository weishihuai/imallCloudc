
package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysLogData;
import com.imall.iportal.core.main.repository.SysLogDataRepositoryCustom;
import com.imall.iportal.core.main.vo.SysLogDataVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;

/**
 * 系统 日志 数据(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysLogDataRepositoryImpl implements SysLogDataRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    public Page<SysLogDataVo> query(Pageable pageable, String tableKey, Long objectId, String searchText)  {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(tableKey)) {
            sql.append(" and a.TABLE_KEY=:tableKey");
            paramMap.put(SysLogData.TABLE_KEY, tableKey);
        }

        if (objectId!=null) {
            sql.append(" and a.OBJECT_ID=:objectId");
            paramMap.put(SysLogData.OBJECT_ID, objectId);
        }

        if (StringUtils.isNotBlank(searchText)) {
            sql.append(" and (a.LOG_INNER_CODE LIKE :logInnerCode OR a.CREATE_BY LIKE :createBy OR a.LAST_MODIFIED_BY LIKE :lastModifiedBy)");
            paramMap.put(SysLogData.LOG_INNER_CODE, "%" + searchText + "%");
            paramMap.put(SysLogData.CREATE_BY, "%" + searchText + "%");
            paramMap.put(SysLogData.LAST_MODIFIED_BY, "%" + searchText + "%");
        }

        sql.append(" group by a.LOG_INNER_CODE order by a.LOG_INNER_CODE desc");

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(a.ID) FROM T_PT_SYS_LOG_DATA a WHERE 1=1" + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        Integer total;
        try {
            total = ((BigInteger)totalQuery.getSingleResult()).intValue();
        }catch (NoResultException exception){
            total = 0;
        }

        List<SysLogDataVo> resultList = new ArrayList<>();

        if(total > 0){
            Query query = entityManager.createNativeQuery("SELECT a.* FROM T_PT_SYS_LOG_DATA a where 1=1" + sql);
            CommonUtil.formatQueryParameter(query, paramMap);
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());

            SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
            nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> mapList = nativeQuery.list();

            for (Map<String, Object> objectMap : mapList) {
                resultList.add(CommonUtil.copyFromDbMap(objectMap, new SysLogDataVo()));
            }
        }

        return new PageImpl<SysLogDataVo>(resultList, pageable, total);
    }

}
