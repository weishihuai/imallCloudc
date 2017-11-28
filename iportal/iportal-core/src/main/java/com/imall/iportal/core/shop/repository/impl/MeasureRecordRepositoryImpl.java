
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.MeasureRecord;
import com.imall.iportal.core.shop.repository.MeasureRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.MeasureRecordSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class MeasureRecordRepositoryImpl implements MeasureRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<MeasureRecord> query(Pageable pageable, MeasureRecordSearchParam measureRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_measure_record b where 1=1 ");

        if (measureRecordSearchParam.getShopId() != null) {
            sql.append(" and b.SHOP_ID =:shopId");
            paramMap.put(MeasureRecord.SHOP_ID, measureRecordSearchParam.getShopId());
        }

        //计量器具编号
        if (StringUtils.isNotBlank(measureRecordSearchParam.getMeasuringDeviceNum())) {
            sql.append(" and b.MEASURING_DEVICE_ACCOUNTS_ID in (SELECT id FROM t_shp_measuring_device_accounts c WHERE c.MEASURING_DEVICE_NUM like:measuringDeviceNum )");
            paramMap.put("measuringDeviceNum","%" + measureRecordSearchParam.getMeasuringDeviceNum() + "%");
        }

        //出厂编号
        if (StringUtils.isNotBlank(measureRecordSearchParam.getManufacturingNum())) {
            sql.append(" and b.MEASURING_DEVICE_ACCOUNTS_ID in (SELECT id FROM t_shp_measuring_device_accounts c WHERE c.MANUFACTURING_NUM like:manufacturingNum )");
            paramMap.put("manufacturingNum","%" + measureRecordSearchParam.getManufacturingNum() + "%");
        }

        //检测时间
        if (StringUtils.isNotBlank(measureRecordSearchParam.getMeasureDateStartString())) {
            sql.append(" and b.MEASURE_DATE >= :fromDate");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(measureRecordSearchParam.getMeasureDateStartString()));
        }

        if (StringUtils.isNotBlank(measureRecordSearchParam.getMeasureDateEndString())){
            sql.append(" and b.MEASURE_DATE <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(measureRecordSearchParam.getMeasureDateEndString()));
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select b.*" + sqlStr, MeasureRecord.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<MeasureRecord>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
