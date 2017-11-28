
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import com.imall.iportal.core.shop.entity.MaintainingRecord;
import com.imall.iportal.core.shop.repository.MaintainingRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.MaintainingRecordPageVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSearchParam;
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
public class MaintainingRecordRepositoryImpl implements MaintainingRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<MaintainingRecordPageVo> query(Pageable pageable, MaintainingRecordSearchParam maintainingRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" M.SHOP_ID = :shopId ");
        paramMap.put(MaintainingRecord.SHOP_ID, maintainingRecordSearchParam.getShopId());

        if(StringUtils.isNotBlank(maintainingRecordSearchParam.getDeviceTypeCode())){
            sql.append(" AND F.DEVICE_TYPE_CODE = :deviceTypeCode ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_TYPE_CODE, maintainingRecordSearchParam.getDeviceTypeCode());
        }

        if(StringUtils.isNotBlank(maintainingRecordSearchParam.getDeviceNum())){
            sql.append(" AND F.DEVICE_NUM = :deviceNum ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_NUM, maintainingRecordSearchParam.getDeviceNum());
        }

        if(StringUtils.isNotBlank(maintainingRecordSearchParam.getDeviceNm())){
            sql.append(" AND F.DEVICE_NM LIKE :deviceNm ");
            paramMap.put(FacilityAndDeviceAccounts.DEVICE_NM, "%" + maintainingRecordSearchParam.getDeviceNm() + "%");
        }

        if(StringUtils.isNotBlank(maintainingRecordSearchParam.getMaintainResponseMan())){
            sql.append(" AND M.MAINTAIN_RESPONSE_MAN LIKE :maintainResponseMan ");
            paramMap.put(MaintainingRecord.MAINTAIN_RESPONSE_MAN, "%" + maintainingRecordSearchParam.getMaintainResponseMan() + "%");
        }

        Query query = entityManager.createNativeQuery("SELECT M.*, F.DEVICE_NUM, F.DEVICE_NM, F.DEVICE_TYPE_CODE, F.MODEL, F.PURCHASE_PLACE " +
                "FROM T_SHP_MAINTAINING_RECORD M LEFT JOIN T_SHP_FACILITY_AND_DEVICE_ACCOUNTS F ON M.FACILITY_AND_DEVICE_ACCOUNTS_ID = F.ID WHERE " + sql
                + " ORDER BY M.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<MaintainingRecordPageVo> maintainingRecordPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            maintainingRecordPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new MaintainingRecordPageVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_MAINTAINING_RECORD M LEFT JOIN T_SHP_FACILITY_AND_DEVICE_ACCOUNTS F ON M.FACILITY_AND_DEVICE_ACCOUNTS_ID = F.ID WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<MaintainingRecordPageVo>(maintainingRecordPageVoList, pageable, total);
    }
}
