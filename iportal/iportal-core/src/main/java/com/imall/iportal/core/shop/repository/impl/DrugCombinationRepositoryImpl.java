
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCombination;
import com.imall.iportal.core.shop.repository.DrugCombinationRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugCombinationPageVo;
import com.imall.iportal.core.shop.vo.DrugCombinationSearchParam;
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
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class DrugCombinationRepositoryImpl implements DrugCombinationRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugCombinationPageVo> query(Pageable pageable, DrugCombinationSearchParam drugCombinationSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" (D.ORG_ID = :orgId OR D.ORG_ID = :adminDefaultOrgId) ");
        paramMap.put(DrugCombination.ORG_ID, drugCombinationSearchParam.getOrgId());
        paramMap.put("adminDefaultOrgId", Global.ADMIN_DEFAULT_ORG_ID);

        if(drugCombinationSearchParam.getDrugCombinationCategoryId()!=null){
            sql.append(" AND D.DRUG_COMBINATION_CATEGORY_ID = :drugCombinationCategoryId ");
            paramMap.put(DrugCombination.DRUG_COMBINATION_CATEGORY_ID, drugCombinationSearchParam.getDrugCombinationCategoryId());
        }

        if(drugCombinationSearchParam.getDisease()!=null){
            sql.append(" AND D.DISEASE LIKE :disease ");
            paramMap.put(DrugCombination.DISEASE, "%" + drugCombinationSearchParam.getDisease() + "%");
        }

        if(drugCombinationSearchParam.getSymptom()!=null){
            sql.append(" AND D.SYMPTOM LIKE :symptom ");
            paramMap.put(DrugCombination.SYMPTOM, "%" + drugCombinationSearchParam.getSymptom() + "%");
        }

        if(drugCombinationSearchParam.getCommonSense()!=null){
            sql.append(" AND D.COMMON_SENSE LIKE :commonSense ");
            paramMap.put(DrugCombination.COMMON_SENSE, "%" + drugCombinationSearchParam.getCommonSense() + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT D.* FROM T_SHP_DRUG_COMBINATION D WHERE " + sql + " ORDER BY D.ORG_ID DESC, D.ID DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<DrugCombinationPageVo> drugCombinationPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            DrugCombinationPageVo pageVo = new DrugCombinationPageVo();
            CommonUtil.copyFromDbMap(objectMap, pageVo);
            pageVo.setCurrentOrgId(drugCombinationSearchParam.getOrgId());
            drugCombinationPageVoList.add(pageVo);
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_DRUG_COMBINATION D WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<DrugCombinationPageVo>(drugCombinationPageVoList, pageable, total);
    }
}
