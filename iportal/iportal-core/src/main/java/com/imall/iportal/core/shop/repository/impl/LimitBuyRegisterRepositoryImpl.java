
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.LimitBuyRegister;
import com.imall.iportal.core.shop.repository.LimitBuyRegisterRepositoryCustom;
import com.imall.iportal.core.shop.vo.LimitBuyRegisterSearchParam;
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
public class LimitBuyRegisterRepositoryImpl implements LimitBuyRegisterRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<LimitBuyRegister> query(Pageable pageable, LimitBuyRegisterSearchParam limitBuyRegisterSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_limit_buy_register a where 1=1 ");

        sql.append(" and a.SHOP_ID = :shopId");
        paramMap.put(LimitBuyRegister.SHOP_ID, limitBuyRegisterSearchParam.getShopId());

        //患者名称
        if (StringUtils.isNotBlank(limitBuyRegisterSearchParam.getPatientNm())) {
            sql.append(" and a.PATIENT_NM like :patientNm");
            paramMap.put(LimitBuyRegister.PATIENT_NM, "%" + limitBuyRegisterSearchParam.getPatientNm() + "%");
        }
        //订单编号
        if (StringUtils.isNotBlank(limitBuyRegisterSearchParam.getSellOrderCode())) {
            sql.append(" and a.SELL_ORDER_CODE = :sellOrderCode");
            paramMap.put(LimitBuyRegister.SELL_ORDER_CODE, limitBuyRegisterSearchParam.getSellOrderCode());
        }
        //登记日期范围
        if (StringUtils.isNotBlank(limitBuyRegisterSearchParam.getRegisterStartDateString())) {
            sql.append(" and REGISTER_DATE >= :fromDate ");
            paramMap.put("fromDate", limitBuyRegisterSearchParam.getRegisterStartDate());
        }

        if (StringUtils.isNotBlank(limitBuyRegisterSearchParam.getRegisterEndDateString())) {
            sql.append(" and REGISTER_DATE <= :toDate ");
            paramMap.put("toDate", limitBuyRegisterSearchParam.getRegisterEndDate());
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
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr + " ORDER BY A.CREATE_DATE DESC", LimitBuyRegister.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);

        return new PageImpl<LimitBuyRegister>(resultQ.getResultList(), pageable, total);
    }
}
