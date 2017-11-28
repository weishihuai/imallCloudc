
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.DrugLockStateCodeEnum;
import com.imall.iportal.core.shop.entity.DrugLock;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.repository.DrugLockRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugLockDealGoodsSearchParam;
import com.imall.iportal.core.shop.vo.ProblemDrugSearchParam;
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
public class DrugLockRepositoryImpl implements DrugLockRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugLock> query(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_lock  where 1=1 ");
        //门店ID
        if (problemDrugSearchParam.getShopId() != null) {
            sql.append("and SHOP_ID =:shopId ");
            paramMap.put(DrugLock.SHOP_ID, problemDrugSearchParam.getShopId());
        }

        sql.append(" and LOCK_STATE_CODE =:lockStateCode ");
        paramMap.put(DrugLock.LOCK_STATE_CODE, DrugLockStateCodeEnum.PENDING.toCode());

        //商品批号
        if (StringUtils.isNotEmpty(problemDrugSearchParam.getBatch())) {
            sql.append("and GOODS_BATCH_ID in (SELECT ID FROM t_shp_goods_batch WHERE BATCH like:batch)");
            paramMap.put("batch", "%" + problemDrugSearchParam.getBatch() + "%");
        }

        //商品名称、拼音码、编码
        if (StringUtils.isNotBlank(problemDrugSearchParam.getKeyword())) {
            sql.append(" and (GOODS_CODE like:goodsCode or GOODS_NM like:goodsNm or PINYIN like:pinyin or GOODS_COMMON_NM like:goodsCommonNm)");
            paramMap.put("goodsCode", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("goodsNm", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("pinyin", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("goodsCommonNm", "%" + problemDrugSearchParam.getKeyword() + "%");
        }

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
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, DrugLock.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<DrugLock>(resultQ.getResultList(), pageable, total);
    }

    @Override
    public Page<DrugLock> queryDrugLockDeal(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_lock where 1=1 ");
        //门店ID
        if (problemDrugSearchParam.getShopId() != null) {
            sql.append("and SHOP_ID =:shopId ");
            paramMap.put(DrugLock.SHOP_ID, problemDrugSearchParam.getShopId());
        }

        sql.append(" and LOCK_STATE_CODE =:lockStateCode ");
        paramMap.put(DrugLock.LOCK_STATE_CODE, DrugLockStateCodeEnum.PENDING.toCode());

        //商品批号
        if (StringUtils.isNotEmpty(problemDrugSearchParam.getBatch())) {
            sql.append("and GOODS_BATCH_ID in (SELECT ID FROM t_shp_goods_batch WHERE BATCH like:batch)");
            paramMap.put("batch", "%" + problemDrugSearchParam.getBatch() + "%");
        }

        //商品名称、拼音码、编码
        if (StringUtils.isNotBlank(problemDrugSearchParam.getKeyword())) {
            sql.append(" and (GOODS_CODE like:goodsCode or GOODS_NM like:goodsNm or PINYIN like:pinyin or GOODS_COMMON_NM like:goodsCommonNm)");
            paramMap.put("goodsNm", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("goodsCode", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("pinyin", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("goodsCommonNm", "%" + problemDrugSearchParam.getKeyword() + "%");
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, DrugLock.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<DrugLock>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }

    @Override
    public Page<DrugLock> queryDrugLockDealGoodsBatchList(Pageable pageable, DrugLockDealGoodsSearchParam drugLockDealGoodsSearchParam, Long shopId) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_lock a where 1=1 ");
        if (shopId != null) {
            sql.append(" AND a.SHOP_ID =:shopId");
            paramMap.put(DrugLock.SHOP_ID, shopId);
        }

        sql.append(" AND a.LOCK_STATE_CODE =:lockStateCode ");
        paramMap.put(DrugLock.LOCK_STATE_CODE, DrugLockStateCodeEnum.PENDING.toCode());

        if (StringUtils.isNotBlank(drugLockDealGoodsSearchParam.getBatch())) {
            sql.append(" AND a.GOODS_BATCH_ID in (select id from t_shp_goods_batch where BATCH like:batch) ");
            paramMap.put("batch", "%" + drugLockDealGoodsSearchParam.getBatch() + "%");
        }

        if (StringUtils.isNotBlank(drugLockDealGoodsSearchParam.getGoodsCode()) || StringUtils.isNotBlank(drugLockDealGoodsSearchParam.getGoodsNm())) {
            sql.append(" AND a.GOODS_ID IN (SELECT ID FROM t_shp_goods b WHERE b.IS_DELETE = 'N' ");
            if (StringUtils.isNotBlank(drugLockDealGoodsSearchParam.getGoodsCode())) {
                sql.append(" AND b.GOODS_CODE like:goodsCode ");
                paramMap.put(Goods.GOODS_CODE, "%" + drugLockDealGoodsSearchParam.getGoodsCode() + "%");
            }
            if (StringUtils.isNotBlank(drugLockDealGoodsSearchParam.getGoodsNm())) {
                sql.append(" AND b.GOODS_NM like:goodsNm ");
                paramMap.put(Goods.GOODS_NM, "%" + drugLockDealGoodsSearchParam.getGoodsNm() + "%");
            }
            sql.append(" )");
        }

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
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, DrugLock.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);

        return new PageImpl<DrugLock>(resultQ.getResultList(), pageable, total);
    }

    @Override
    public Page<DrugLock> queryDrugDestroyPage(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_lock where 1=1 ");
        //门店ID
        if (problemDrugSearchParam.getShopId() != null) {
            sql.append("and SHOP_ID =:shopId ");
            paramMap.put(DrugLock.SHOP_ID, problemDrugSearchParam.getShopId());
        }

        sql.append(" and LOCK_STATE_CODE =:lockStateCode ");
        paramMap.put(DrugLock.LOCK_STATE_CODE, DrugLockStateCodeEnum.WAIT_DESTROY.toCode());

        //商品批号
        if (StringUtils.isNotEmpty(problemDrugSearchParam.getBatch())) {
            sql.append("and GOODS_BATCH_ID in (SELECT ID FROM t_shp_goods_batch WHERE BATCH like:batch)");
            paramMap.put("batch",  "%" + problemDrugSearchParam.getBatch() + "%");
        }

        //商品名称、拼音码、编码
        if (StringUtils.isNotBlank(problemDrugSearchParam.getKeyword())) {
            sql.append(" and (GOODS_CODE like:goodsCode or GOODS_NM like:goodsNm or PINYIN like:pinyin or GOODS_COMMON_NM like:goodsCommonNm)");
            paramMap.put("goodsNm", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("pinyin", "%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("goodsCode","%" + problemDrugSearchParam.getKeyword() + "%");
            paramMap.put("goodsCommonNm", "%" + problemDrugSearchParam.getKeyword() + "%");
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, DrugLock.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<DrugLock>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
