
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrder;
import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrderItem;
import com.imall.iportal.core.shop.repository.ReturnedPurchaseOrderItemRepositoryCustom;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderItemPageVo;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderSearchParams;
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
public class ReturnedPurchaseOrderItemRepositoryImpl implements ReturnedPurchaseOrderItemRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<ReturnedPurchaseOrderItemPageVo> query(Pageable pageable, ReturnedPurchaseOrderSearchParams searchParams) {
        Map<String, Object> map = new HashMap<>();
        map.put(ReturnedPurchaseOrderItem.SHOP_ID, searchParams.getShopId());
        StringBuilder builder = new StringBuilder();
        builder.append(" from t_shp_returned_purchase_order_item i, t_shp_returned_purchase_order o where i.RETURNED_PURCHASE_ORDER_ID=o.ID and i.SHOP_ID=:shopId");
        if (searchParams.getSupplierId() != null){
            builder.append(" and i.SUPPLIER_ID=:supplierId");
            map.put(ReturnedPurchaseOrderItem.SUPPLIER_ID, searchParams.getSupplierId());
        }
        if (StringUtils.isNotBlank(searchParams.getReturnedPurchaseType())){
            builder.append(" and o.RETURNED_PURCHASE_TYPE=:returnedPurchaseType");
            map.put(ReturnedPurchaseOrder.RETURNED_PURCHASE_TYPE, searchParams.getReturnedPurchaseType());
        }
        if (StringUtils.isNotBlank(searchParams.getIsPayed())){
            builder.append(" and o.IS_PAYED=:isPayed");
            map.put(ReturnedPurchaseOrder.IS_PAYED, searchParams.getIsPayed());
        }
        if (StringUtils.isNotBlank(searchParams.getReturnedPurchaseOrderNum())){
            builder.append(" and o.RETURNED_PURCHASE_ORDER_NUM=:returnedPurchaseOrderNum");
            map.put(ReturnedPurchaseOrder.RETURNED_PURCHASE_ORDER_NUM, searchParams.getReturnedPurchaseOrderNum());
        }
        builder.append(" order by i.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger)totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select i.*, o.RETURNED_PURCHASE_ORDER_NUM, o.OUT_STORAGE_MAN, o.RETURNED_PURCHASE_REASON" + sql)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        SQLQuery sqlQuery = resultQ.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<ReturnedPurchaseOrderItemPageVo> voList = new ArrayList<>();
        List<Map<String, Object>> mapList = sqlQuery.list();
        for (Map<String, Object> objectMap : mapList){
            voList.add(CommonUtil.copyFromDbMap(objectMap, new ReturnedPurchaseOrderItemPageVo()));
        }
        return new PageImpl<ReturnedPurchaseOrderItemPageVo>(voList, pageable, total);
    }
}
