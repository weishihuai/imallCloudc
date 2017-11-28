
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.PurchaseReceiveRecord;
import com.imall.iportal.core.shop.repository.PurchaseReceiveRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.PurchaseReceiveSearchParams;
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
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class PurchaseReceiveRecordRepositoryImpl implements PurchaseReceiveRecordRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<PurchaseReceiveRecord> query(Pageable pageable, PurchaseReceiveSearchParams searchParam) {
        Map<String, Object> map = new HashMap<>();
        map.put(PurchaseReceiveRecord.SHOP_ID, searchParam.getShopId());
        StringBuilder builder = new StringBuilder();
        builder.append(" from t_shp_purchase_receive_record r where r.SHOP_ID=:shopId");
        if (searchParam.getSupplierId() != null) {
            builder.append(" and r.SUPPLIER_ID=:supplierId");
            map.put(PurchaseReceiveRecord.SUPPLIER_ID, searchParam.getSupplierId());
        }
        if (StringUtils.isNotBlank(searchParam.getReceiveOrderNum())) {
            builder.append(" and r.RECEIVE_ORDER_NUM like :receiveOrderNum");
            map.put(PurchaseReceiveRecord.RECEIVE_ORDER_NUM, "%" + searchParam.getReceiveOrderNum() + "%");
        }
        builder.append(" order by r.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger) totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select r.*" + sql, PurchaseReceiveRecord.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<PurchaseReceiveRecord>(resultQ.getResultList(), pageable, total);
    }
}
