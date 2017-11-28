
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.MeasuringDeviceAccounts;
import com.imall.iportal.core.shop.repository.MeasuringDeviceAccountsRepositoryCustom;
import com.imall.iportal.core.shop.vo.MeasuringDeviceAccountsSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class MeasuringDeviceAccountsRepositoryImpl implements MeasuringDeviceAccountsRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<MeasuringDeviceAccounts> query(Pageable pageable, MeasuringDeviceAccountsSearchParam measuringDeviceAccountsSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_measuring_device_accounts a where 1=1 ");

        if (measuringDeviceAccountsSearchParam.getShopId() != null) {
            sql.append(" and a.SHOP_ID =:shopId");
            paramMap.put(MeasuringDeviceAccounts.SHOP_ID, measuringDeviceAccountsSearchParam.getShopId());
        }

        //计量器具编号
        if (StringUtils.isNotBlank(measuringDeviceAccountsSearchParam.getMeasuringDeviceNum())) {
            sql.append(" and a.MEASURING_DEVICE_NUM like:measuringDeviceNum");
            paramMap.put(MeasuringDeviceAccounts.MEASURING_DEVICE_NUM,"%" + measuringDeviceAccountsSearchParam.getMeasuringDeviceNum() + "%");
        }

        //出厂编号
        if (StringUtils.isNotBlank(measuringDeviceAccountsSearchParam.getManufacturingNum())) {
            sql.append(" and a.MANUFACTURING_NUM like:manufacturingNum");
            paramMap.put(MeasuringDeviceAccounts.MANUFACTURING_NUM,"%" +  measuringDeviceAccountsSearchParam.getManufacturingNum() + "%");
        }

        //使用状态
        if (StringUtils.isNotBlank(measuringDeviceAccountsSearchParam.getUseStateCode())) {
            sql.append(" and a.USE_STATE_CODE =:useStateCode");
            paramMap.put(MeasuringDeviceAccounts.USE_STATE_CODE, measuringDeviceAccountsSearchParam.getUseStateCode());
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, MeasuringDeviceAccounts.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<MeasuringDeviceAccounts>(resultQ.getResultList(), pageable, total);
    }
}
