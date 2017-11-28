
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.Order;
import com.imall.iportal.core.shop.entity.OrderItem;
import com.imall.iportal.core.shop.repository.OrderItemRepositoryCustom;
import com.imall.iportal.core.shop.vo.SellSummarySearchParams;
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
public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<OrderItem> querySellDetailPageVo(Pageable pageable, SellSummarySearchParams searchParams) {
        Map<String, Object> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append(" from t_shp_order_item i, t_shp_order o where i.ORDER_ID=o.ID and o.SHOP_ID=:shopId");
        map.put(Order.SHOP_ID, searchParams.getShopId());
        if (StringUtils.isNotBlank(searchParams.getOrderNum())){
            builder.append(" and o.ORDER_NUM like :orderNum");
            map.put(Order.ORDER_NUM, "%" + searchParams.getOrderNum() + "%");
        }
        if (StringUtils.isNotBlank(searchParams.getOrderStateCode())){
            builder.append(" and o.ORDER_STATE_CODE=:orderNum");
            map.put(Order.ORDER_STATE_CODE, searchParams.getOrderStateCode());
        }
        if (StringUtils.isNotBlank(searchParams.getOrderSourceCode())){
            builder.append(" and o.ORDER_SOURCE_CODE=:orderSourceCode");
            map.put(Order.ORDER_SOURCE_CODE, searchParams.getOrderSourceCode());
        }
        if (StringUtils.isNotBlank(searchParams.getKeyword())){
            builder.append(" and (i.GOODS_NM like :keyword or i.GOODS_CODING like :keyword or i.COMMON_NM like :keyword or i.GOODS_PINYIN like :keyword)");
            map.put("keyword", "%" + searchParams.getKeyword() + "%");
        }
        builder.append(" order by i.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger)totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select i.*" + sql, OrderItem.class)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<OrderItem>(resultQ.getResultList(), pageable, total);
    }
}
