
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.PurchaseOrder;
import com.imall.iportal.core.shop.repository.PurchaseOrderRepositoryCustom;
import com.imall.iportal.core.shop.vo.PurchaseOrderSearchParams;
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
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<PurchaseOrder> query(Pageable pageable, PurchaseOrderSearchParams searchParam) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put(PurchaseOrder.SHOP_ID, searchParam.getShopId());
        builder.append(" from t_shp_purchase_order o where o.SHOP_ID=:shopId");
        if (searchParam.getSupplierId() != null) {
            builder.append(" and o.SUPPLIER_ID=:supplierId");
            map.put(PurchaseOrder.SUPPLIER_ID, searchParam.getSupplierId());
        }
        if (StringUtils.isNotBlank(searchParam.getPurchaseOrderState())) {
            builder.append(" and o.PURCHASE_ORDER_STATE=:purchaseOrderState");
            map.put(PurchaseOrder.PURCHASE_ORDER_STATE, searchParam.getPurchaseOrderState());
        }
        if (StringUtils.isNotBlank(searchParam.getPurchaseOrderType())) {
            builder.append(" and o.PURCHASE_ORDER_TYPE=:purchaseOrderType");
            map.put(PurchaseOrder.PURCHASE_ORDER_TYPE, searchParam.getPurchaseOrderType());
        }
        if (StringUtils.isNotBlank(searchParam.getPurchaseOrderNum())) {
            builder.append(" and o.PURCHASE_ORDER_NUM like :purchaseOrderNum");
            map.put(PurchaseOrder.PURCHASE_ORDER_NUM, "%" + searchParam.getPurchaseOrderNum() + "%");
        }
        builder.append(" order by o.ID desc");
        String sql = builder.toString();
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;

        Query resultTotal = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(resultTotal, map);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select o.*" + sql, PurchaseOrder.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<PurchaseOrder>(resultQ.getResultList(), pageable, total);
    }

}
