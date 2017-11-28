
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.PurchaseReceiveRecord;
import com.imall.iportal.core.shop.entity.PurchaseReceiveRecordItem;
import com.imall.iportal.core.shop.repository.PurchaseReceiveRecordItemRepositoryCustom;
import com.imall.iportal.core.shop.vo.PurchaseReceiveRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseReceiveSearchParams;
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
public class PurchaseReceiveRecordItemRepositoryImpl implements PurchaseReceiveRecordItemRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<PurchaseReceiveRecordItemPageVo> query(Pageable pageable, PurchaseReceiveSearchParams searchParams) {
        Map<String, Object> map = new HashMap<>();
        map.put(PurchaseReceiveRecordItem.SHOP_ID, searchParams.getShopId());
        StringBuilder builder = new StringBuilder();
        builder.append(" from t_shp_purchase_receive_record_item i, t_shp_purchase_receive_record r where i.PURCHASE_RECEIVE_RECORD_ID=r.ID")
                .append(" and i.SHOP_ID=:shopId");
        if (searchParams.getSupplierId() != null){
            builder.append(" and i.SUPPLIER_ID:=supplierId");
            map.put(PurchaseReceiveRecordItem.SUPPLIER_ID, searchParams.getSupplierId());
        }
        if (StringUtils.isNotBlank(searchParams.getReceiveOrderNum())){
            builder.append(" and r.RECEIVE_ORDER_NUM like :receiveOrderNum");
            map.put(PurchaseReceiveRecord.RECEIVE_ORDER_NUM, "%" + searchParams.getReceiveOrderNum() + "%");
        }
        builder.append(" order by i.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger)totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select i.*, r.RECEIVE_ORDER_NUM, r.RECEIVER, r.RECEIVE_TIME" + sql)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);

        SQLQuery sqlQuery = resultQ.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> mapList = sqlQuery.list();
        List<PurchaseReceiveRecordItemPageVo> voList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList){
            voList.add(CommonUtil.copyFromDbMap(objectMap, new PurchaseReceiveRecordItemPageVo()));
        }
        return new PageImpl<PurchaseReceiveRecordItemPageVo>(voList, pageable, total);
    }
}
