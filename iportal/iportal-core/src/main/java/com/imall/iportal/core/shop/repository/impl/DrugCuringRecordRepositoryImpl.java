
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCuringRecord;
import com.imall.iportal.core.shop.repository.DrugCuringRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugCuringRecordPageVo;
import com.imall.iportal.core.shop.vo.DrugCuringRecordSearchParam;
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
public class DrugCuringRecordRepositoryImpl implements DrugCuringRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugCuringRecordPageVo> query(Pageable pageable, DrugCuringRecordSearchParam drugCuringRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" D.SHOP_ID = :shopId ");
        paramMap.put(DrugCuringRecord.SHOP_ID, drugCuringRecordSearchParam.getShopId());

        if(StringUtils.isNotBlank(drugCuringRecordSearchParam.getKeyword())){
            sql.append(" AND (D.GOODS_CODE LIKE :goodsCode OR D.GOODS_NM LIKE :goodsNm OR D.COMMON_NM LIKE :commonNm OR D.GOODS_NM_FIRST_SPELL LIKE :goodsNmFirstSpell) ");
            paramMap.put(DrugCuringRecord.GOODS_CODE, "%" + drugCuringRecordSearchParam.getKeyword() + "%");
            paramMap.put(DrugCuringRecord.GOODS_NM, "%" + drugCuringRecordSearchParam.getKeyword() + "%");
            paramMap.put(DrugCuringRecord.COMMON_NM, "%" + drugCuringRecordSearchParam.getKeyword() + "%");
            paramMap.put(DrugCuringRecord.GOODS_NM_FIRST_SPELL, "%" + drugCuringRecordSearchParam.getKeyword() + "%");
        }

        if(StringUtils.isNotBlank(drugCuringRecordSearchParam.getCuringPlanNum())){
            sql.append(" AND D.CURING_PLAN_NUM = :curingPlanNum ");
            paramMap.put(DrugCuringRecord.CURING_PLAN_NUM, drugCuringRecordSearchParam.getCuringPlanNum());
        }

        if(StringUtils.isNotBlank(drugCuringRecordSearchParam.getStartCuringDateString())){
            sql.append(" AND D.CURING_DATE >= :startCuringDate ");
            paramMap.put("startCuringDate", drugCuringRecordSearchParam.getStartCuringDate());
        }

        if(StringUtils.isNotBlank(drugCuringRecordSearchParam.getEndCuringDateString())){
            sql.append(" AND D.CURING_DATE <= :endCuringDate ");
            paramMap.put("endCuringDate", drugCuringRecordSearchParam.getEndCuringDate());
        }

        Query query = entityManager.createNativeQuery("SELECT D.* FROM T_SHP_DRUG_CURING_RECORD D WHERE " + sql + " ORDER BY D.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<DrugCuringRecordPageVo> drugCuringRecordPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            drugCuringRecordPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new DrugCuringRecordPageVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_DRUG_CURING_RECORD D WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<DrugCuringRecordPageVo>(drugCuringRecordPageVoList, pageable, total);
    }
}
