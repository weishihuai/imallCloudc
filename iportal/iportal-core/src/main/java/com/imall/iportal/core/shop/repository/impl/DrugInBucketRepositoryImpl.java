
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.DrugInBucket;
import com.imall.iportal.core.shop.repository.DrugInBucketRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugInBucketSearchParam;
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
public class DrugInBucketRepositoryImpl implements DrugInBucketRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugInBucket> query(Pageable pageable, DrugInBucketSearchParam drugInBucketSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_in_bucket b where 1=1 ");

        if (drugInBucketSearchParam.getShopId() != null) {
            sql.append(" and b.SHOP_ID =:shopId");
            paramMap.put(DrugInBucket.SHOP_ID, drugInBucketSearchParam.getShopId());
        }

        //商品名称、拼音码、编码
        if (StringUtils.isNotBlank(drugInBucketSearchParam.getSearchFields())) {
            sql.append(" and b.GOODS_ID IN (SELECT g.ID FROM t_shp_goods g WHERE (g.GOODS_CODE like:searchFields1 or g.GOODS_NM like:searchFields2 or g.PINYIN like:searchFields3 or g.COMMON_NM like:searchFields4))");
            paramMap.put("searchFields1", "%" + drugInBucketSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields2", "%" + drugInBucketSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields3", "%" + drugInBucketSearchParam.getSearchFields() + "%");
            paramMap.put("searchFields4", "%" + drugInBucketSearchParam.getSearchFields() + "%");
        }
        //商品批号
        if (StringUtils.isNotBlank(drugInBucketSearchParam.getBatch())) {
            sql.append(" and BATCH_ID IN (SELECT ID FROM t_shp_goods_batch WHERE BATCH like:batch) ");
            paramMap.put("batch",  "%" + drugInBucketSearchParam.getBatch() + "%");
        }
        //装斗人
        if (StringUtils.isNotBlank(drugInBucketSearchParam.getInBucketManName())) {
            sql.append(" and IN_BUCKET_MAN_NAME like:inBucketManName");
            paramMap.put(DrugInBucket.IN_BUCKET_MAN_NAME, "%" + drugInBucketSearchParam.getInBucketManName() + "%");
        }
        //装斗时间
        if (StringUtils.isNotBlank(drugInBucketSearchParam.getInBucketStartTimeString())) {
            sql.append(" and IN_BUCKET_TIME >= :fromDate");
            paramMap.put("fromDate", DateTimeUtils.getDayStartTime(drugInBucketSearchParam.getInBucketStartTimeString()));
        }

        if (StringUtils.isNotBlank(drugInBucketSearchParam.getInBucketEndTimeString())){
            sql.append(" and IN_BUCKET_TIME <= :toDate ");
            paramMap.put("toDate", DateTimeUtils.getDayEndTime(drugInBucketSearchParam.getInBucketEndTimeString()));
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
        Query resultQ = entityManager.createNativeQuery("select b.*" + sqlStr, DrugInBucket.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<DrugInBucket>(resultQ.getResultList(), pageable, total);
    }
}
