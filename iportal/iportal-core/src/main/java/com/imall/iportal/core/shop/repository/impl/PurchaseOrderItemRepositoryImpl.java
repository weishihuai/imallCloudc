
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.PurchaseOrder;
import com.imall.iportal.core.shop.entity.PurchaseOrderItem;
import com.imall.iportal.core.shop.repository.PurchaseOrderItemRepositoryCustom;
import com.imall.iportal.core.shop.vo.PurchaseOrderItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderSearchParams;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.annotation.Resource;
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
public class PurchaseOrderItemRepositoryImpl implements PurchaseOrderItemRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<PurchaseOrderItemPageVo> query(Pageable pageable, PurchaseOrderSearchParams searchParams) {
        Map<String, Object> map = new HashMap<>();
        map.put(PurchaseOrderItem.SHOP_ID, searchParams.getShopId());
        StringBuilder builder = new StringBuilder();
        builder.append(" from t_shp_purchase_order_item i, t_shp_purchase_order o where i.PURCHASE_ORDER_ID=o.ID and i.SHOP_ID=:shopId");
        if (searchParams.getSupplierId() != null){
            builder.append(" and i.SUPPLIER_ID=:supplierId");
            map.put(PurchaseOrderItem.SUPPLIER_ID, searchParams.getSupplierId());
        }
        if (StringUtils.isNotBlank(searchParams.getPurchaseOrderState())){
            builder.append(" and o.PURCHASE_ORDER_STATE=:purchaseOrderState");
            map.put(PurchaseOrder.PURCHASE_ORDER_STATE, searchParams.getPurchaseOrderState());
        }
        if (StringUtils.isNotBlank(searchParams.getPurchaseOrderType())){
            builder.append(" and o.PURCHASE_ORDER_TYPE=:purchaseOrderType");
            map.put(PurchaseOrder.PURCHASE_ORDER_TYPE, searchParams.getPurchaseOrderType());
        }
        if (StringUtils.isNotBlank(searchParams.getPurchaseOrderNum())){
            builder.append(" and o.PURCHASE_ORDER_NUM like :purchaseOrderNum");
            map.put(PurchaseOrder.PURCHASE_ORDER_NUM, "%" + searchParams.getPurchaseOrderNum() + "%");
        }
        builder.append(" order by i.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger)totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select i.*, o.PURCHASE_ORDER_NUM, o.PURCHASE_ORDER_STATE, o.PURCHASE_MAN" + sql)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        SQLQuery sqlQuery = resultQ.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();
        List<PurchaseOrderItemPageVo> voList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList){
            voList.add(CommonUtil.copyFromDbMap(objectMap, new PurchaseOrderItemPageVo()));
        }
        return new PageImpl<PurchaseOrderItemPageVo>(voList, pageable, total);
    }
}
