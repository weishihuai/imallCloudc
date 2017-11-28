
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.PrescriptionRegister;
import com.imall.iportal.core.shop.repository.PrescriptionRegisterRepositoryCustom;
import com.imall.iportal.core.shop.vo.PrescriptionRegisterPageVo;
import com.imall.iportal.core.shop.vo.PrescriptionRegisterSearchParam;
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
public class PrescriptionRegisterRepositoryImpl implements PrescriptionRegisterRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<PrescriptionRegisterPageVo> query(Pageable pageable, PrescriptionRegisterSearchParam prescriptionRegisterSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" P.SHOP_ID = :shopId ");
        paramMap.put(PrescriptionRegister.SHOP_ID, prescriptionRegisterSearchParam.getShopId());

        if(StringUtils.isNotBlank(prescriptionRegisterSearchParam.getPrescriptionRegisterState())){
            sql.append(" AND P.PRESCRIPTION_REGISTER_STATE = :prescriptionRegisterState ");
            paramMap.put(PrescriptionRegister.PRESCRIPTION_REGISTER_STATE, prescriptionRegisterSearchParam.getPrescriptionRegisterState());
        }

        if(StringUtils.isNotBlank(prescriptionRegisterSearchParam.getPrescriptionSellOrderCode())){
            sql.append(" AND P.PRESCRIPTION_SELL_ORDER_CODE = :prescriptionSellOrderCode ");
            paramMap.put(PrescriptionRegister.PRESCRIPTION_SELL_ORDER_CODE, prescriptionRegisterSearchParam.getPrescriptionSellOrderCode());
        }

        if(StringUtils.isNotBlank(prescriptionRegisterSearchParam.getPatientNm())){
            sql.append(" AND P.PATIENT_NM LIKE :patientNm ");
            paramMap.put(PrescriptionRegister.PATIENT_NM, "%" + prescriptionRegisterSearchParam.getPatientNm() + "%");
        }

        if(StringUtils.isNotBlank(prescriptionRegisterSearchParam.getStartPrescriptionDateString())){
            sql.append(" AND P.PRESCRIPTION_DATE >= :startPrescriptionDate ");
            paramMap.put("startPrescriptionDate", prescriptionRegisterSearchParam.getStartPrescriptionDate());
        }

        if(StringUtils.isNotBlank(prescriptionRegisterSearchParam.getEndPrescriptionDateString())){
            sql.append(" AND P.PRESCRIPTION_DATE <= :endPrescriptionDate ");
            paramMap.put("endPrescriptionDate", prescriptionRegisterSearchParam.getEndPrescriptionDate());
        }

        Query query = entityManager.createNativeQuery("SELECT P.* FROM T_SHP_PRESCRIPTION_REGISTER P WHERE " + sql + " ORDER BY P.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<PrescriptionRegisterPageVo> prescriptionRegisterPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            prescriptionRegisterPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new PrescriptionRegisterPageVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_PRESCRIPTION_REGISTER P WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<PrescriptionRegisterPageVo>(prescriptionRegisterPageVoList, pageable, total);
    }
}
