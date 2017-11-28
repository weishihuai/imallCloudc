
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.GoodsBatchStateCodeEnum;
import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.repository.GoodsBatchRepositoryCustom;
import com.imall.iportal.core.shop.vo.StockWarningSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class GoodsBatchRepositoryImpl implements GoodsBatchRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Page<GoodsBatch> query(Pageable pageable, String searchFields, String batch, Long shopId) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_goods_batch a where 1=1 ");

        if (shopId != null) {
            sql.append(" AND a.SHOP_ID =:shopId");
            paramMap.put(GoodsBatch.SHOP_ID, shopId);
        }

        //商品编码
        if (StringUtils.isNotBlank(searchFields)) {
            sql.append(" and (GOODS_CODE like:searchFields1 or GOODS_NM like:searchFields2 or GOODS_COMMON_NM like:searchFields2 or PINYIN like:searchFields2) ");
            paramMap.put("searchFields1", "%" + searchFields + "%");
            paramMap.put("searchFields2", "%" + searchFields + "%");
        }
        //商品批号
        if (StringUtils.isNotBlank(batch)) {
            sql.append(" and BATCH like:batch");
            paramMap.put(GoodsBatch.BATCH, "%" + batch + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;

        String sqlStr = sql.append(" order by GOODS_ID desc, BATCH desc ").toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, GoodsBatch.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<GoodsBatch>(resultQ.getResultList(), pageable,total);
    }

    @Override
    public Page<GoodsBatch> queryExpireDrugWarningPage(Pageable pageable, StockWarningSearchParam stockWarningSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_goods_batch a where 1=1 ");

        if (stockWarningSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(GoodsBatch.SHOP_ID, stockWarningSearchParam.getShopId());
        }
        //商品编码
        if (StringUtils.isNotBlank(stockWarningSearchParam.getSearchFields())) {
            sql.append(" and (GOODS_CODE like:goodsCode or GOODS_NM like:goodsName or PINYIN like:goodsPinyin or GOODS_COMMON_NM like:searchFields4) ");
            paramMap.put("goodsCode","%" +  stockWarningSearchParam.getSearchFields() + "%");
            paramMap.put("goodsName", "%" + stockWarningSearchParam.getSearchFields() + "%");
            paramMap.put("goodsPinyin", "%" + stockWarningSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields4", "%" + stockWarningSearchParam.getSearchFields() + "%");
        }
        //商品批号
        if (StringUtils.isNotBlank(stockWarningSearchParam.getBatch())) {
            sql.append(" and BATCH like:batch");
            paramMap.put(GoodsBatch.BATCH, "%" + stockWarningSearchParam.getBatch() +  "%" );
        }
        sql.append(" and VALID_DATE < :now ");
        paramMap.put("now", new Date());
        sql.append(" and CURRENT_STOCK > 0 and BATCH_STATE != :batchState");
        paramMap.put(GoodsBatch.BATCH_STATE, GoodsBatchStateCodeEnum.DELETE.toCode());
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String countSqlStr = sql.toString();
        String sqlStr = sql.append(" group by GOODS_ID, BATCH order by GOODS_ID desc, BATCH desc ").toString();
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, GoodsBatch.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(distinct GOODS_ID, BATCH) " + countSqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<GoodsBatch>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }

    @Override
    public Page<GoodsBatch> queryNearExpireDatePage(Pageable pageable, StockWarningSearchParam stockWarningSearchParam, Integer expireDate) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_goods_batch a where 1=1 ");

        if (stockWarningSearchParam.getShopId() != null) {
            sql.append(" and SHOP_ID =:shopId");
            paramMap.put(GoodsBatch.SHOP_ID, stockWarningSearchParam.getShopId());
        }
        //商品编码
        if (StringUtils.isNotBlank(stockWarningSearchParam.getSearchFields())) {
            sql.append(" and (GOODS_CODE like:code or GOODS_NM like:goodsName or PINYIN like:pinyin or GOODS_COMMON_NM like:searchFields4) ");
            paramMap.put("code", "%" + stockWarningSearchParam.getSearchFields() + "%");
            paramMap.put("goodsName", "%" + stockWarningSearchParam.getSearchFields() + "%");
            paramMap.put("pinyin", "%" + stockWarningSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields4", "%" + stockWarningSearchParam.getSearchFields() + "%");
        }
        //商品批号
        if (StringUtils.isNotBlank(stockWarningSearchParam.getBatch())) {
            sql.append(" and BATCH like:batch");
            paramMap.put(GoodsBatch.BATCH, "%" + stockWarningSearchParam.getBatch() + "%");
        }

        Date expireDateTime =  DateTimeUtils.addMonths(new Date(), expireDate);
        sql.append(" and VALID_DATE <= :expireDateTime and VALID_DATE >:now");
        paramMap.put("expireDateTime", expireDateTime);
        paramMap.put("now", new Date());
        sql.append(" and CURRENT_STOCK > 0 and BATCH_STATE != :batchState");
        paramMap.put(GoodsBatch.BATCH_STATE, GoodsBatchStateCodeEnum.DELETE.toCode());
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String countSqlStr = sql.toString();
        String sqlStr = sql.append(" group by GOODS_ID, BATCH order by GOODS_ID desc, BATCH desc ").toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(distinct GOODS_ID, BATCH) " + countSqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, GoodsBatch.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<GoodsBatch>(resultQ.getResultList(), pageable, total);
    }


    @Override
    public List<GoodsBatch> findHasStockGoodsByGoodsId(Long goodsId) {

        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append("select a.* from t_shp_goods_batch a where 1=1 ");
        sql.append(" AND a.GOODS_ID =:goodsId AND a.CURRENT_STOCK > 0 AND a.BATCH_STATE = :batchState order by PRODUCE_DATE desc, CURRENT_STOCK asc");
        paramMap.put(GoodsBatch.GOODS_ID,goodsId);
        paramMap.put(GoodsBatch.BATCH_STATE, GoodsBatchStateCodeEnum.NORMAL.toCode());

        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery(sqlStr, GoodsBatch.class) ;
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return resultQ.getResultList();
    }

    @Override
    public List<GoodsBatch> findHasStockGoodsByGoodsIdAndBatch(Long goodsId, String batch) {

        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append("select a.* from t_shp_goods_batch a where 1=1 ");
        if(goodsId != null) {
            sql.append(" AND a.GOODS_ID =:goodsId ");
            paramMap.put(GoodsBatch.GOODS_ID, goodsId);
        }
        if(StringUtils.isNotBlank(batch)) {
            sql.append(" AND a.BATCH =:batch ");
            paramMap.put(GoodsBatch.BATCH, batch);
        }
        sql.append(" AND a.CURRENT_STOCK > 0 AND a.BATCH_STATE = :batchState order by PRODUCE_DATE asc, CURRENT_STOCK asc");
        paramMap.put(GoodsBatch.BATCH_STATE, GoodsBatchStateCodeEnum.NORMAL.toCode());

        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery(sqlStr, GoodsBatch.class) ;
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return resultQ.getResultList();
    }

    @Override
    public Long findCurrentStockByGoodsId(Long goodsId) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from  t_shp_goods_batch a where 1=1 and GOODS_ID = :goodsId and BATCH_STATE = :batchState and CURRENT_STOCK > 0");
        paramMap.put(GoodsBatch.GOODS_ID, goodsId);
        paramMap.put(GoodsBatch.BATCH_STATE, GoodsBatchStateCodeEnum.NORMAL.toCode());
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT SUM(a.CURRENT_STOCK) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        Object object = resultTotal.getSingleResult();
        return object == null ? 0L : ((BigDecimal) resultTotal.getSingleResult()).longValue();
    }

    @Override
    public Long findCurrentStockByGoodsIdAndBatch(Long goodsId, String batch) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from  t_shp_goods_batch a where 1=1 ");

        if(goodsId != null) {
            sql.append(" and GOODS_ID = :goodsId ");
            paramMap.put(GoodsBatch.GOODS_ID, goodsId);
        }

        if(StringUtils.isNotBlank(batch)) {
            sql.append(" and BATCH = :batch ");
            paramMap.put(GoodsBatch.BATCH, batch);
        }

        sql.append(" and BATCH_STATE = :batchState and CURRENT_STOCK > 0");
        paramMap.put(GoodsBatch.BATCH_STATE, GoodsBatchStateCodeEnum.NORMAL.toCode());
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("SELECT SUM(a.CURRENT_STOCK) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        Object object = resultTotal.getSingleResult();
        return object == null ? 0L : ((BigDecimal) resultTotal.getSingleResult()).longValue();
    }

    @Override
    public GoodsBatch findOneByCustom(Long id) {
        EntityManager tempEntityManager = entityManagerFactory.createEntityManager();
        GoodsBatch goodsBatch = tempEntityManager.find(GoodsBatch.class, id);
        tempEntityManager.close();
        return goodsBatch;
    }

    @Override
    public Integer queryGoodsBatchToQueue() {
        Query query = entityManager.createNativeQuery("insert into t_pt_es_index_queue(object_id,index_type_code,execute_state,version)  select id,'goods_batch','UN_PROCESSED',0 FROM t_shp_goods_batch");
        return query.executeUpdate();
    }

    @Override
    public void updateOverdueBatch(){

        String sql = "update t_shp_goods_batch set BATCH_STATE = 'OVERDUE' where VALID_DATE < :now";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("now", new Date());
        Query query = entityManager.createNativeQuery(sql);
        CommonUtil.formatQueryParameter(query, paramMap);
        query.executeUpdate();
    }

}
