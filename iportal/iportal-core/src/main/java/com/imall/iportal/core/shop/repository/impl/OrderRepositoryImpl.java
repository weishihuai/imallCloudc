
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.iportal.core.shop.entity.Order;
import com.imall.iportal.core.shop.repository.OrderRepositoryCustom;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Integer queryOrderToQueue() {
        Query query = entityManager.createNativeQuery("insert into t_pt_es_index_queue(object_id,index_type_code,execute_state,version)  select id,'order','UN_PROCESSED',0 FROM t_shp_order");
        return query.executeUpdate();
    }

    @Override
    public Integer countOrderQuantity(Long id, String orderSourceCode, Date minCreateDate) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if(OrderSourceCodeEnum.SALES_POS.toCode().equals(orderSourceCode)){
            sql.append(" O.SHOP_ID = :shopId ");
            paramMap.put(Order.SHOP_ID, id);
        }else{
            sql.append(" O.WE_SHOP_ID = :weShopId ");
            paramMap.put(Order.WE_SHOP_ID, id);
        }

        sql.append(" AND O.ORDER_SOURCE_CODE = :orderSourceCode ");
        paramMap.put(Order.ORDER_SOURCE_CODE, orderSourceCode);
        sql.append(" AND O.CREATE_DATE >= :createDate ");
        paramMap.put(Order.CREATE_DATE, minCreateDate);
        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_ORDER O WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);

        return ((BigInteger)totalQuery.getSingleResult()).intValue();
    }

    @Override
    public Double countTotalAmount(Long id, String orderSourceCode, Date minCreateDate) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if(OrderSourceCodeEnum.SALES_POS.toCode().equals(orderSourceCode)){
            sql.append(" O.SHOP_ID = :shopId ");
            paramMap.put(Order.SHOP_ID, id);
        }else{
            sql.append(" O.WE_SHOP_ID = :weShopId ");
            paramMap.put(Order.WE_SHOP_ID, id);
        }

        sql.append(" AND O.ORDER_SOURCE_CODE = :orderSourceCode ");
        paramMap.put(Order.ORDER_SOURCE_CODE, orderSourceCode);
        sql.append(" AND O.CREATE_DATE >= :createDate ");
        paramMap.put(Order.CREATE_DATE, minCreateDate);
        Query totalQuery = entityManager.createNativeQuery("SELECT SUM(O.GOODS_TOTAL_AMOUNT) AS TOTAL_AMOUNT FROM T_SHP_ORDER O WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);

        SQLQuery sqlQuery = totalQuery.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();

        return (Double) mapList.get(0).get("TOTAL_AMOUNT");
    }

    @Override
    public Integer countOrderStateQuantity(Long id, String orderSourceCode, String orderStateCode) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if(OrderSourceCodeEnum.SALES_POS.toCode().equals(orderSourceCode)){
            sql.append(" O.SHOP_ID = :shopId ");
            paramMap.put(Order.SHOP_ID, id);
        }else{
            sql.append(" O.WE_SHOP_ID = :weShopId ");
            paramMap.put(Order.WE_SHOP_ID, id);
        }

        sql.append(" AND O.ORDER_SOURCE_CODE = :orderSourceCode ");
        paramMap.put(Order.ORDER_SOURCE_CODE, orderSourceCode);
        sql.append(" AND O.ORDER_STATE_CODE = :orderStateCode ");
        paramMap.put(Order.ORDER_STATE_CODE, orderStateCode);

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_ORDER O WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);

        return ((BigInteger)totalQuery.getSingleResult()).intValue();
    }

    @Override
    public Page<Order> query(Pageable pageable, Long wechatUserId) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sql.append(" O.WECHAT_USER_ID = :wechatUserId ");
        paramMap.put(Order.WECHAT_USER_ID, wechatUserId);

        Query query = entityManager.createNativeQuery("SELECT O.* FROM T_SHP_ORDER O WHERE " + sql + " ORDER BY O.CREATE_DATE DESC");
        CommonUtil.formatQueryParameter(query, paramMap);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = sqlQuery.list();
        List<Order> orderList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            orderList.add(CommonUtil.copyFromDbMap(objectMap, new Order()));
        }

        Query totalQuery = entityManager.createNativeQuery("SELECT COUNT(1) FROM T_SHP_ORDER O WHERE " + sql);
        CommonUtil.formatQueryParameter(totalQuery, paramMap);
        int total = ((BigInteger)totalQuery.getSingleResult()).intValue();

        return new PageImpl<Order>(orderList, pageable, total);
    }
}
