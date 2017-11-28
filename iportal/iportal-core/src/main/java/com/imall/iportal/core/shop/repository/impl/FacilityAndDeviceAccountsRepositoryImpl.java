
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import com.imall.iportal.core.shop.repository.FacilityAndDeviceAccountsRepositoryCustom;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsPageVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSearchParam;
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
public class FacilityAndDeviceAccountsRepositoryImpl implements FacilityAndDeviceAccountsRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<FacilityAndDeviceAccountsPageVo> query(Pageable pageable, FacilityAndDeviceAccountsSearchParam searchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" F.SHOP_ID = :shopId ");
        paramMap.put(FacilityAndDeviceAccounts.SHOP_ID, searchParam.getShopId());

        if(StringUtils.isNotBlank(searchParam.getDeviceTypeCode())){
            sql.append(" AND F.DEVICE_TYPE_CODE = :deviceTypeCode ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_TYPE_CODE, searchParam.getDeviceTypeCode());
        }

        if(StringUtils.isNotBlank(searchParam.getDeviceNum())){
            sql.append(" AND F.DEVICE_NUM = :deviceNum ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_NUM, searchParam.getDeviceNum());
        }

        if(StringUtils.isNotBlank(searchParam.getDeviceNm())){
            sql.append(" AND F.DEVICE_NM LIKE :deviceNm ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_NM, "%" + searchParam.getDeviceNm() + "%");
        }

        if(StringUtils.isNotBlank(searchParam.getResponseMan())){
            sql.append(" AND F.RESPONSE_MAN LIKE :responseMan ");
            paramMap.put(FacilityAndDeviceAccounts.RESPONSE_MAN, "%" + searchParam.getResponseMan() + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT F.* FROM T_SHP_FACILITY_AND_DEVICE_ACCOUNTS F WHERE " + sql + " ORDER BY F.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<FacilityAndDeviceAccountsPageVo> facilityAndDeviceAccountsPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            facilityAndDeviceAccountsPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new FacilityAndDeviceAccountsPageVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_FACILITY_AND_DEVICE_ACCOUNTS F WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<FacilityAndDeviceAccountsPageVo>(facilityAndDeviceAccountsPageVoList, pageable, total);
    }
}
