
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCheck;
import com.imall.iportal.core.shop.repository.DrugCheckRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugCheckPageVo;
import com.imall.iportal.core.shop.vo.DrugCheckSearchParam;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class DrugCheckRepositoryImpl implements DrugCheckRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugCheckPageVo> query(Pageable pageable, DrugCheckSearchParam drugCheckSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" AND D.SHOP_ID = :shopId ");
        paramMap.put(DrugCheck.SHOP_ID, drugCheckSearchParam.getShopId());

        if(StringUtils.isNotBlank(drugCheckSearchParam.getCheckDocumentNum())){
            sql.append(" AND D.CHECK_DOCUMENT_NUM LIKE :checkDocumentNum ");
            paramMap.put(DrugCheck.CHECK_DOCUMENT_NUM, "%" + drugCheckSearchParam.getCheckDocumentNum() + "%");
        }

        if(StringUtils.isNotBlank(drugCheckSearchParam.getCheckTypeCode())){
            sql.append(" AND D.CHECK_TYPE_CODE = :checkTypeCode ");
            paramMap.put(DrugCheck.CHECK_TYPE_CODE, drugCheckSearchParam.getCheckTypeCode());
        }

        if(StringUtils.isNotBlank(drugCheckSearchParam.getFromPlanCheckTimeString())){
            sql.append(" AND D.PLAN_CHECK_TIME >= :fromPlanCheckTime ");
            paramMap.put("fromPlanCheckTime", drugCheckSearchParam.getFromPlanCheckTime());
        }

        if(StringUtils.isNotBlank(drugCheckSearchParam.getToPlanCheckTimeString())){
            sql.append(" AND D.PLAN_CHECK_TIME <= :toPlanCheckTime ");
            paramMap.put("toPlanCheckTime", drugCheckSearchParam.getToPlanCheckTime());
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_DRUG_CHECK D WHERE 1=1 " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query query = entityManager.createNativeQuery("SELECT D.* FROM T_SHP_DRUG_CHECK D WHERE 1=1 " + sql + " ORDER BY D.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(firstIdx);
        query.setMaxResults(pageSize);
        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<DrugCheckPageVo> drugCheckPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            drugCheckPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new DrugCheckPageVo()));
        }

        return new PageImpl<DrugCheckPageVo>(drugCheckPageVoList, pageable, total);
    }
}
