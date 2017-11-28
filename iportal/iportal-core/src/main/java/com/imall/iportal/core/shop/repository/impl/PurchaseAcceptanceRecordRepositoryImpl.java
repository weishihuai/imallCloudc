
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecord;
import com.imall.iportal.core.shop.repository.PurchaseAcceptanceRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordSearchParams;
import org.apache.commons.lang.StringUtils;
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
public class PurchaseAcceptanceRecordRepositoryImpl implements PurchaseAcceptanceRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<PurchaseAcceptanceRecord> query(Pageable pageable, PurchaseAcceptanceRecordSearchParams searchParams) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put(PurchaseAcceptanceRecord.SHOP_ID, searchParams.getShopId());
        builder.append(" from t_shp_purchase_acceptance_record r where r.SHOP_ID=:shopId");
        if (searchParams.getSupplierId() != null){
            builder.append(" and r.SUPPLIER_ID=:supplierId");
            map.put(PurchaseAcceptanceRecord.SUPPLIER_ID, searchParams.getSupplierId());
        }
        if (StringUtils.isNotBlank(searchParams.getAcceptanceOrderNum())){
            builder.append(" and r.ACCEPTANCE_ORDER_NUM like :acceptanceOrderNum");
            map.put(PurchaseAcceptanceRecord.ACCEPTANCE_ORDER_NUM, "%" + searchParams.getAcceptanceOrderNum() + "%");
        }
        builder.append(" order by r.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger)totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select r.*" + sql, PurchaseAcceptanceRecord.class)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<PurchaseAcceptanceRecord>(resultQ.getResultList(), pageable, total);
    }
}
