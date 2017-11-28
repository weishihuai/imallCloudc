
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import com.imall.iportal.core.shop.entity.UseRecord;
import com.imall.iportal.core.shop.repository.UseRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.UseRecordPageVo;
import com.imall.iportal.core.shop.vo.UseRecordSearchParam;
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
public class UseRecordRepositoryImpl implements UseRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<UseRecordPageVo> query(Pageable pageable, UseRecordSearchParam useRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" U.SHOP_ID = :shopId ");
        paramMap.put(UseRecord.SHOP_ID, useRecordSearchParam.getShopId());

        if(StringUtils.isNotBlank(useRecordSearchParam.getOperationMan())){
            sql.append(" AND U.OPERATION_MAN LIKE :operationMan ");
            paramMap.put(UseRecord.OPERATION_MAN, "%" + useRecordSearchParam.getOperationMan() + "%");
        }

        if(StringUtils.isNotBlank(useRecordSearchParam.getDeviceTypeCode())){
            sql.append(" AND F.DEVICE_TYPE_CODE = :deviceTypeCode ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_TYPE_CODE, useRecordSearchParam.getDeviceTypeCode());
        }

        if(StringUtils.isNotBlank(useRecordSearchParam.getDeviceNum())){
            sql.append(" AND F.DEVICE_NUM = :deviceNum ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_NUM, useRecordSearchParam.getDeviceNum());
        }

        if(StringUtils.isNotBlank(useRecordSearchParam.getDeviceNm())){
            sql.append(" AND F.DEVICE_NM LIKE :deviceNm ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_NM, "%" + useRecordSearchParam.getDeviceNm() + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT U.*, F.DEVICE_NUM, F.DEVICE_NM, F.DEVICE_TYPE_CODE, F.MODEL, F.PURCHASE_PLACE " +
                "FROM T_SHP_USE_RECORD U LEFT JOIN T_SHP_FACILITY_AND_DEVICE_ACCOUNTS F ON U.FACILITY_AND_DEVICE_ACCOUNTS_ID = F.ID WHERE " + sql
                + " ORDER BY U.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<UseRecordPageVo> useRecordPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            useRecordPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new UseRecordPageVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_USE_RECORD U LEFT JOIN T_SHP_FACILITY_AND_DEVICE_ACCOUNTS F " +
                "ON U.FACILITY_AND_DEVICE_ACCOUNTS_ID = F.ID  WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<UseRecordPageVo>(useRecordPageVoList, pageable, total);
    }
}
