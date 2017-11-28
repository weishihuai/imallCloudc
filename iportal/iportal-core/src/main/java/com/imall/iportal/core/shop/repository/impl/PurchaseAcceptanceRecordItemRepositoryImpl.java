
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecord;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;
import com.imall.iportal.core.shop.repository.PurchaseAcceptanceRecordItemRepositoryCustom;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemSearchParams;
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
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class PurchaseAcceptanceRecordItemRepositoryImpl implements PurchaseAcceptanceRecordItemRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<PurchaseAcceptanceRecordItem> queryReturnableItem(Pageable pageable, PurchaseAcceptanceRecordItemSearchParams searchParams) {
        Map<String, Object> map = new HashMap<>();
        map.put(PurchaseAcceptanceRecordItem.SHOP_ID, searchParams.getShopId());
        map.put(PurchaseAcceptanceRecordItem.SUPPLIER_ID, searchParams.getSupplierId());
        StringBuilder builder = new StringBuilder();
        builder.append(" from t_shp_purchase_acceptance_record_item r, t_shp_goods_batch b where r.SHOP_ID=:shopId and r.SUPPLIER_ID=:supplierId and r.GOODS_BATCH_ID=b.ID and b.CURRENT_STOCK > 0");
        if (StringUtils.isNotBlank(searchParams.getGoodsNm())) {
            builder.append(" and r.GOODS_NM like :goodsNm");
            map.put(PurchaseAcceptanceRecordItem.GOODS_NM, "%" + searchParams.getGoodsNm() + "%");
        }
        if (StringUtils.isNotBlank(searchParams.getGoodsCode())) {
            builder.append(" and r.GOODS_CODE like :goodsCode");
            map.put(PurchaseAcceptanceRecordItem.GOODS_CODE, "%" + searchParams.getGoodsCode() + "%");
        }
        if (StringUtils.isNotBlank(searchParams.getProduceManufacturer())) {
            builder.append(" and r.PRODUCE_MANUFACTURER like :produceManufacturer");
            map.put(PurchaseAcceptanceRecordItem.PRODUCE_MANUFACTURER, "%" + searchParams.getProduceManufacturer() + "%");
        }
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger) totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select r.*" + sql, PurchaseAcceptanceRecordItem.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        return new PageImpl<PurchaseAcceptanceRecordItem>(resultQ.getResultList(), pageable, total);
    }

    @Override
    public Page<PurchaseAcceptanceRecordItemPageVo> query(Pageable pageable, Long shopId, Long supplierId, String acceptanceOrderNum) {
        Map<String, Object> map = new HashMap<>();
        map.put(PurchaseAcceptanceRecordItem.SHOP_ID, shopId);
        StringBuilder builder = new StringBuilder();
        builder.append(" from t_shp_purchase_acceptance_record_item i, t_shp_purchase_acceptance_record r where i.PURCHASE_ACCEPTANCE_RECORD_ID=r.ID and i.SHOP_ID=:shopId");
        if (supplierId != null){
            builder.append(" and i.SUPPLIER_ID=:supplierId");
            map.put(PurchaseAcceptanceRecordItem.SUPPLIER_ID, supplierId);
        }
        if (StringUtils.isNotBlank(acceptanceOrderNum)){
            builder.append(" and r.ACCEPTANCE_ORDER_NUM like :acceptanceOrderNum");
            map.put(PurchaseAcceptanceRecord.ACCEPTANCE_ORDER_NUM, "%" + acceptanceOrderNum + "%");
        }
        builder.append(" order by i.ID desc");
        String sql = builder.toString();
        Query totalQ = entityManager.createNativeQuery("select count(*)" + sql);
        CommonUtil.formatQueryParameter(totalQ, map);
        Long total = ((BigInteger)totalQ.getSingleResult()).longValue();

        Query resultQ = entityManager.createNativeQuery("select i.*, r.ACCEPTANCE_ORDER_NUM, r.ACCEPTANCE_MAN, r.PURCHASE_ORDER_ID" + sql)
                .setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());
        CommonUtil.formatQueryParameter(resultQ, map);
        SQLQuery sqlQuery = resultQ.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<PurchaseAcceptanceRecordItemPageVo> voList = new ArrayList<>();
        List<Map<String, Object>> mapList = sqlQuery.list();
        for (Map<String, Object> objectMap : mapList){
            voList.add(CommonUtil.copyFromDbMap(objectMap, new PurchaseAcceptanceRecordItemPageVo()));
        }
        return new PageImpl<PurchaseAcceptanceRecordItemPageVo>(voList, pageable, total);
    }
}
