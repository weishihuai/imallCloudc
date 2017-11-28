
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCombinationCategory;
import com.imall.iportal.core.shop.repository.DrugCombinationCategoryRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugCombinationCategoryPageVo;
import com.imall.iportal.core.shop.vo.DrugCombinationCategorySearchParam;
import org.apache.commons.lang.StringUtils;
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
public class DrugCombinationCategoryRepositoryImpl implements DrugCombinationCategoryRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugCombinationCategoryPageVo> query(Pageable pageable, DrugCombinationCategorySearchParam drugCombinationCategorySearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" (D.ORG_ID = :orgId OR D.ORG_ID = :adminDefaultOrgId) ");
        paramMap.put(DrugCombinationCategory.ORG_ID, drugCombinationCategorySearchParam.getOrgId());
        paramMap.put("adminDefaultOrgId", Global.ADMIN_DEFAULT_ORG_ID);

        if(StringUtils.isNotBlank(drugCombinationCategorySearchParam.getCategoryNm())){
            sql.append(" AND D.CATEGORY_NM LIKE :categoryNm ");
            paramMap.put(DrugCombinationCategory.CATEGORY_NM, "%" + drugCombinationCategorySearchParam.getCategoryNm() + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT D.* FROM T_SHP_DRUG_COMBINATION_CATEGORY D WHERE " + sql + " ORDER BY D.ORG_ID DESC, D.ID DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<DrugCombinationCategoryPageVo> drugCombinationCategoryPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            DrugCombinationCategoryPageVo pageVo = new DrugCombinationCategoryPageVo();
            CommonUtil.copyFromDbMap(objectMap, pageVo);
            pageVo.setCurrentOrgId(drugCombinationCategorySearchParam.getOrgId());
            drugCombinationCategoryPageVoList.add(pageVo);
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_DRUG_COMBINATION_CATEGORY D WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<DrugCombinationCategoryPageVo>(drugCombinationCategoryPageVoList, pageable, total);
    }
}
