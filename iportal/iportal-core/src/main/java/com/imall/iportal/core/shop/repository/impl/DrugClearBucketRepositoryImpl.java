
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.DrugClearBucket;
import com.imall.iportal.core.shop.repository.DrugClearBucketRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugClearBucketSearchParam;
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
public class DrugClearBucketRepositoryImpl implements DrugClearBucketRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugClearBucket> query(Pageable pageable, DrugClearBucketSearchParam drugClearBucketSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_clear_bucket a where 1=1 ");

        if (drugClearBucketSearchParam.getShopId() != null) {
            sql.append(" and a.SHOP_ID =:shopId");
            paramMap.put(DrugClearBucket.SHOP_ID, drugClearBucketSearchParam.getShopId());
        }

        //商品名称、拼音码、编码
        if (StringUtils.isNotBlank(drugClearBucketSearchParam.getSearchFields())) {
            sql.append(" and a.GOODS_ID IN (SELECT g.ID FROM t_shp_goods g WHERE (g.GOODS_CODE like:searchFields1 or g.GOODS_NM like:searchFields2 or g.PINYIN like:searchFields3 or g.COMMON_NM like:searchFields4))");
            paramMap.put("searchFields1", "%" + drugClearBucketSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields2", "%" + drugClearBucketSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields3", "%" + drugClearBucketSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields4", "%" + drugClearBucketSearchParam.getSearchFields() + "%");
        }
        //商品批号
        if (StringUtils.isNotBlank(drugClearBucketSearchParam.getBatch())) {
            sql.append(" and BATCH_ID IN (SELECT ID FROM t_shp_goods_batch WHERE BATCH like:batch) ");
            paramMap.put("batch","%"+ drugClearBucketSearchParam.getBatch()+"%");
        }
        //清斗人
        if (StringUtils.isNotBlank(drugClearBucketSearchParam.getClearBucketManName())) {
            sql.append(" and CLEAR_BUCKET_MAN_NAME like:clearBucketManName");
            paramMap.put(DrugClearBucket.CLEAR_BUCKET_MAN_NAME, "%" + drugClearBucketSearchParam.getClearBucketManName() + "%");
        }
        //清斗时间
        if (StringUtils.isNotBlank(drugClearBucketSearchParam.getClearBucketStartTimeString())) {
            sql.append(" and CLEAR_BUCKET_TIME >= :fromDate ");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(drugClearBucketSearchParam.getClearBucketStartTimeString()));
        }

        if (StringUtils.isNotBlank(drugClearBucketSearchParam.getClearBucketEndTimeString())) {
            sql.append(" and CLEAR_BUCKET_TIME <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(drugClearBucketSearchParam.getClearBucketEndTimeString()));
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
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, DrugClearBucket.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<DrugClearBucket>(resultQ.getResultList(), pageable, total);
    }
}
