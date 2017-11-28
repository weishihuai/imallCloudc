
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.ApproveStateCodeEnum;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.repository.GoodsRepositoryCustom;
import com.imall.iportal.core.shop.vo.StockWarningSearchParam;
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
 * 商品(JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class GoodsRepositoryImpl implements GoodsRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<Goods> queryOutOfStockWarningPage(Pageable pageable, StockWarningSearchParam stockWarningSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_goods a left join t_shp_sku b on a.ID = b.GOODS_ID where 1=1 ");

        if (stockWarningSearchParam.getShopId() != null) {
            sql.append(" and a.SHOP_ID =:shopId");
            paramMap.put(Goods.SHOP_ID, stockWarningSearchParam.getShopId());
        }
        //商品编码
        if (StringUtils.isNotBlank(stockWarningSearchParam.getSearchFields())) {
            sql.append(" and (a.GOODS_CODE =:searchFields or a.GOODS_NM like :searchFields1 or a.COMMON_NM like :searchFields1 or a.PINYIN like :searchFields1) ");
            paramMap.put("searchFields", stockWarningSearchParam.getSearchFields());
            paramMap.put("searchFields1", "%" + stockWarningSearchParam.getSearchFields() + "%");
        }
        //当前库存小于安全库存
        sql.append(" and b.CURRENT_STOCK <= b.SECURITY_STOCK ");
        sql.append(" and a.APPROVE_STATE_CODE =:approveStateCode ");
        paramMap.put(Goods.APPROVE_STATE_CODE, ApproveStateCodeEnum.PASS_APPROVE.toCode());

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, Goods.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<>(resultQ.getResultList(), pageable, total);
    }

    @Override
    public Page<Goods> query(Pageable pageable, Long shopId, String searchFields) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_goods a where 1=1 ");

        if (shopId != null) {
            sql.append(" and a.SHOP_ID =:shopId");
            paramMap.put(Goods.SHOP_ID, shopId);
        }

        //商品名称
        if (StringUtils.isNotBlank(searchFields)) {
            sql.append(" and (GOODS_CODE =:searchFields1 or GOODS_NM like:searchFields2 or COMMON_NM like:searchFields2 or PINYIN like:searchFields2) ");
            paramMap.put("searchFields1", searchFields);
            paramMap.put("searchFields2", "%" + searchFields + "%");
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, Goods.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<Goods>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());

    }


    public Integer queryGoodsToQueue() {
        Query query = entityManager.createNativeQuery("insert into t_pt_es_index_queue(object_id,index_type_code,execute_state,version)  select id,'goods','UN_PROCESSED',0 FROM t_shp_goods");
        return query.executeUpdate();

    }
}
