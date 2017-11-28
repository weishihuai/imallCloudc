
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.DrugCheckRecord;
import com.imall.iportal.core.shop.repository.DrugCheckRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugCheckRecordPageVo;
import com.imall.iportal.core.shop.vo.DrugCheckRecordSearchParam;
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
public class DrugCheckRecordRepositoryImpl implements DrugCheckRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugCheckRecordPageVo> query(Pageable pageable, DrugCheckRecordSearchParam drugCheckRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" D.SHOP_ID = :shopId ");
        paramMap.put(DrugCheckRecord.SHOP_ID, drugCheckRecordSearchParam.getShopId());

        if(StringUtils.isNotBlank(drugCheckRecordSearchParam.getKeyword())){
            sql.append(" AND (D.GOODS_CODE LIKE :goodsCode OR D.GOODS_NM LIKE :goodsNm OR D.COMMON_NM LIKE :commonNm OR D.GOODS_NM_FIRST_SPELL LIKE :goodsNmFirstSpell) ");
            paramMap.put(DrugCheckRecord.GOODS_CODE, "%" + drugCheckRecordSearchParam.getKeyword() + "%");
            paramMap.put(DrugCheckRecord.GOODS_NM, "%" + drugCheckRecordSearchParam.getKeyword() + "%");
            paramMap.put(DrugCheckRecord.COMMON_NM, "%" + drugCheckRecordSearchParam.getKeyword() + "%");
            paramMap.put(DrugCheckRecord.GOODS_NM_FIRST_SPELL, "%" + drugCheckRecordSearchParam.getKeyword() + "%");
        }

        if(StringUtils.isNotBlank(drugCheckRecordSearchParam.getCheckNum())){
            sql.append(" AND D.CHECK_NUM = :checkNum ");
            paramMap.put(DrugCheckRecord.CHECK_NUM, drugCheckRecordSearchParam.getCheckNum());
        }

        if(StringUtils.isNotBlank(drugCheckRecordSearchParam.getStartCheckTimeString())){
            sql.append(" AND D.CHECK_TIME >= :startCheckTime ");
            paramMap.put("startCheckTime", drugCheckRecordSearchParam.getStartCheckTime());
        }

        if(StringUtils.isNotBlank(drugCheckRecordSearchParam.getEndCheckTimeString())){
            sql.append(" AND D.CHECK_TIME <= :endCheckTime ");
            paramMap.put("endCheckTime", drugCheckRecordSearchParam.getEndCheckTime());
        }

        Query query = entityManager.createNativeQuery("SELECT D.* FROM T_SHP_DRUG_CHECK_RECORD D WHERE " + sql + " ORDER BY D.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<DrugCheckRecordPageVo> drugCheckRecordPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            drugCheckRecordPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new DrugCheckRecordPageVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_DRUG_CHECK_RECORD D WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<DrugCheckRecordPageVo>(drugCheckRecordPageVoList, pageable, total);
    }
}
