
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrder;
import com.imall.iportal.core.shop.repository.ReturnedPurchaseOrderRepositoryCustom;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderSearchParams;
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
public class ReturnedPurchaseOrderRepositoryImpl implements ReturnedPurchaseOrderRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<ReturnedPurchaseOrder> query(Pageable pageable, ReturnedPurchaseOrderSearchParams params) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put(ReturnedPurchaseOrder.SHOP_ID, params.getShopId());
        builder.append(" from t_shp_returned_purchase_order o where o.SHOP_ID=:shopId");
        if (params.getSupplierId() != null) {
            builder.append(" and o.SUPPLIER_ID=:supplierId");
            map.put(ReturnedPurchaseOrder.SUPPLIER_ID, params.getSupplierId());
        }
        if (StringUtils.isNotBlank(params.getIsPayed())) {
            builder.append(" and o.IS_PAYED=:isPayed");
            map.put(ReturnedPurchaseOrder.IS_PAYED, params.getIsPayed());
        }
        if (StringUtils.isNotBlank(params.getReturnedPurchaseOrderNum())) {
            builder.append(" and o.RETURNED_PURCHASE_ORDER_NUM like :returnedPurchaseOrderNum");
            map.put(ReturnedPurchaseOrder.RETURNED_PURCHASE_ORDER_NUM, "%" + params.getReturnedPurchaseOrderNum() + "%");
        }
        if (StringUtils.isNotBlank(params.getReturnedPurchaseType())){
            builder.append(" and o.RETURNED_PURCHASE_TYPE=:returnedPurchaseType");
            map.put(ReturnedPurchaseOrder.RETURNED_PURCHASE_TYPE, params.getReturnedPurchaseType());
        }
        builder.append(" order by o.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger) totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select o.*" + sql, ReturnedPurchaseOrder.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<ReturnedPurchaseOrder>(resultQ.getResultList(), pageable, total);
    }
}
