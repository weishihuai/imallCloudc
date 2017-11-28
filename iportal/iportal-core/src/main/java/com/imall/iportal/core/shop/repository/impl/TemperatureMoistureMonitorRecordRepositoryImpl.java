
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.TemperatureMoistureMonitorRecord;
import com.imall.iportal.core.shop.repository.TemperatureMoistureMonitorRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordPageVo;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordSearchParam;
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
public class TemperatureMoistureMonitorRecordRepositoryImpl implements TemperatureMoistureMonitorRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<TemperatureMoistureMonitorRecordPageVo> query(Pageable pageable, TemperatureMoistureMonitorRecordSearchParam temperatureMoistureMonitorRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        sql.append(" AND R.SHOP_ID = :shopId ");
        paramMap.put(TemperatureMoistureMonitorRecord.SHOP_ID, temperatureMoistureMonitorRecordSearchParam.getShopId());

        if(temperatureMoistureMonitorRecordSearchParam.getStorageSpaceId()!=null){
            sql.append(" AND R.STORAGE_SPACE_ID = :storageSpaceId ");
            paramMap.put(TemperatureMoistureMonitorRecord.STORAGE_SPACE_ID, temperatureMoistureMonitorRecordSearchParam.getStorageSpaceId());
        }

        if(StringUtils.isNotBlank(temperatureMoistureMonitorRecordSearchParam.getFromMonitorDateString())){
            sql.append(" AND R.MONITOR_DATE >= :fromMonitorDate ");
            paramMap.put("fromMonitorDate", temperatureMoistureMonitorRecordSearchParam.getFromMonitorDate());
        }

        if(StringUtils.isNotBlank(temperatureMoistureMonitorRecordSearchParam.getToMonitorDateString())){
            sql.append(" AND R.MONITOR_DATE <= :toMonitorDate ");
            paramMap.put("toMonitorDate", temperatureMoistureMonitorRecordSearchParam.getToMonitorDate());
        }

        Query query = entityManager.createNativeQuery("SELECT R.* FROM T_SHP_TEMPERATURE_MOISTURE_MONITOR_RECORD R WHERE 1=1 " + sql + " ORDER BY R.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        List<TemperatureMoistureMonitorRecordPageVo> temperatureMoistureMonitorRecordPageVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            temperatureMoistureMonitorRecordPageVoList.add(CommonUtil.copyFromDbMap(objectMap, new TemperatureMoistureMonitorRecordPageVo()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_TEMPERATURE_MOISTURE_MONITOR_RECORD R WHERE 1=1 " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<TemperatureMoistureMonitorRecordPageVo>(temperatureMoistureMonitorRecordPageVoList, pageable, total);
    }
}
