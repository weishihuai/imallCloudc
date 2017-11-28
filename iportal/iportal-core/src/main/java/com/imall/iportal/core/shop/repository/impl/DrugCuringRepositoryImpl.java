
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCuring;
import com.imall.iportal.core.shop.repository.DrugCuringRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugCuringPageVo;
import com.imall.iportal.core.shop.vo.DrugCuringSearchParam;
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
public class DrugCuringRepositoryImpl implements DrugCuringRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugCuringPageVo> query(Pageable pageable, DrugCuringSearchParam drugCuringSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" AND D.SHOP_ID = :shopId ");
        paramMap.put(DrugCuring.SHOP_ID, drugCuringSearchParam.getShopId());

        if(StringUtils.isNotBlank(drugCuringSearchParam.getCuringDocumentNum())){
            sql.append(" AND D.CURING_DOCUMENT_NUM LIKE :curingDocumentNum ");
            paramMap.put(DrugCuring.CURING_DOCUMENT_NUM, "%" + drugCuringSearchParam.getCuringDocumentNum() + "%");
        }

        if(StringUtils.isNotBlank(drugCuringSearchParam.getCuringTypeCode())){
            sql.append(" AND D.CURING_TYPE_CODE = :curingTypeCode ");
            paramMap.put(DrugCuring.CURING_TYPE_CODE, drugCuringSearchParam.getCuringTypeCode());
        }

        if(StringUtils.isNotBlank(drugCuringSearchParam.getFromPlanCuringTimeString())){
            sql.append(" AND D.PLAN_CURING_TIME >= :fromPlanCuringTime ");
            paramMap.put("fromPlanCuringTime", drugCuringSearchParam.getFromPlanCuringTime());
        }

        if(StringUtils.isNotBlank(drugCuringSearchParam.getToPlanCuringTimeString())){
            sql.append(" AND D.PLAN_CURING_TIME <= :toPlanCuringTime ");
            paramMap.put("toPlanCuringTime", drugCuringSearchParam.getToPlanCuringTime());
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;

        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_DRUG_CURING D WHERE 1=1 " + sql);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query query = entityManager.createNativeQuery("SELECT D.* FROM T_SHP_DRUG_CURING D WHERE 1=1 " + sql + " ORDER BY D.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(firstIdx);
        query.setMaxResults(pageSize);

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<DrugCuringPageVo> drugCuringPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            drugCuringPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new DrugCuringPageVo()));
        }
        return new PageImpl<DrugCuringPageVo>(drugCuringPageVoList, pageable, total);
    }
}
