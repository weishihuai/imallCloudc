
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.SellReturnedPurchaseOrder;
import com.imall.iportal.core.shop.repository.SellReturnedPurchaseOrderRepositoryCustom;
import com.imall.iportal.core.shop.vo.SellReturnedPurchaseOrderSearchParam;
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
 * 销售 退货 订单(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SellReturnedPurchaseOrderRepositoryImpl implements SellReturnedPurchaseOrderRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SellReturnedPurchaseOrder> query(Pageable pageable, SellReturnedPurchaseOrderSearchParam searchParam) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        builder.append(" from t_shp_sell_returned_purchase_order where SHOP_ID = :shopId");
        map.put(SellReturnedPurchaseOrder.SHOP_ID, searchParam.getShopId());

        if (searchParam.getCashierId() != null) {
            builder.append(" and CASHIER_ID = :cashierId");
            map.put(SellReturnedPurchaseOrder.CASHIER_ID, searchParam.getCashierId());
        }
        if (StringUtils.isNotBlank(searchParam.getOrderNum())) {
            builder.append(" and SELL_RETURNED_PURCHASE_ORDER_NUM like :sellReturnedPurchaseOrderNum");
            map.put(SellReturnedPurchaseOrder.SELL_RETURNED_PURCHASE_ORDER_NUM, "%" + searchParam.getOrderNum() + "%");
        }
        if (searchParam.getFromDate() != null){
            builder.append(" and CREATE_DATE >= :startDate");
            map.put("startDate", searchParam.getFromDate());
        }
        if (searchParam.getToDate() != null){
            builder.append(" and CREATE_DATE <= :endDate");
            map.put("endDate", searchParam.getToDate());
        }
        builder.append(" order by ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger) totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select *" + sql, SellReturnedPurchaseOrder.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<SellReturnedPurchaseOrder>(resultQ.getResultList(), pageable, total);

    }

    @Override
    public Integer queryOrderToQueue() {
        Query query = entityManager.createNativeQuery("insert into t_pt_es_index_queue(object_id,index_type_code,execute_state,version)  select id,'srporder','UN_PROCESSED',0 FROM t_shp_sell_returned_purchase_order");
        return query.executeUpdate();
    }

}
