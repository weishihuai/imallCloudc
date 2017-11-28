
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesCleaningBucketRecord;
import com.imall.iportal.core.shop.repository.ChineseMedicinePiecesCleaningBucketRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesCleaningBucketRecordSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class ChineseMedicinePiecesCleaningBucketRecordRepositoryImpl implements ChineseMedicinePiecesCleaningBucketRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<ChineseMedicinePiecesCleaningBucketRecord> query(Pageable pageable, ChineseMedicinePiecesCleaningBucketRecordSearchParam chineseMedicinePiecesCleaningBucketRecordSearchParam) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_chinese_medicine_pieces_cleaning_bucket_record  where 1=1 and SHOP_ID =:shopId ");
        paramMap.put(ChineseMedicinePiecesCleaningBucketRecord.SHOP_ID, chineseMedicinePiecesCleaningBucketRecordSearchParam.getShopId());
        //拼音码
        if (StringUtils.isNotEmpty(chineseMedicinePiecesCleaningBucketRecordSearchParam.getSearchValue())) {
            sql.append("and( GOODS_PINYIN like:goodsPinyin or COMMON_NM like:commonNm or GOODS_NM like:goodsNm)");
            paramMap.put(ChineseMedicinePiecesCleaningBucketRecord.GOODS_PINYIN, "%" +chineseMedicinePiecesCleaningBucketRecordSearchParam.getSearchValue()  + "%");
            paramMap.put(ChineseMedicinePiecesCleaningBucketRecord.COMMON_NM, "%" + chineseMedicinePiecesCleaningBucketRecordSearchParam.getSearchValue()  + "%");
            paramMap.put(ChineseMedicinePiecesCleaningBucketRecord.GOODS_NM, "%" + chineseMedicinePiecesCleaningBucketRecordSearchParam.getSearchValue()  + "%");
        }
        //批号
        if (StringUtils.isNotEmpty(chineseMedicinePiecesCleaningBucketRecordSearchParam.getBatch())) {
            sql.append("and BATCH like:batch ");
            paramMap.put(ChineseMedicinePiecesCleaningBucketRecord.BATCH, "%" + chineseMedicinePiecesCleaningBucketRecordSearchParam.getBatch()  + "%");
        }
        //清斗人
        if (StringUtils.isNotEmpty(chineseMedicinePiecesCleaningBucketRecordSearchParam.getCleaningBucketManNm())) {
            sql.append("and CLEANING_BUCKET_MAN_NM like:cleaningBucketManNm ");
            paramMap.put(ChineseMedicinePiecesCleaningBucketRecord.CLEANING_BUCKET_MAN_NM,"%" + chineseMedicinePiecesCleaningBucketRecordSearchParam.getCleaningBucketManNm() + "%");
        }

        //创建时间范围
        if (StringUtils.isNotEmpty(chineseMedicinePiecesCleaningBucketRecordSearchParam.getStartTimeString())) {
            sql.append(" and CLEANING_BUCKET_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.getDayStartTime(chineseMedicinePiecesCleaningBucketRecordSearchParam.getStartTimeString()));
        }
        //创建时间范围
        if (StringUtils.isNotEmpty(chineseMedicinePiecesCleaningBucketRecordSearchParam.getEndTimeString())) {
            sql.append(" and CLEANING_BUCKET_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(chineseMedicinePiecesCleaningBucketRecordSearchParam.getEndTimeString()));
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, ChineseMedicinePiecesCleaningBucketRecord.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<ChineseMedicinePiecesCleaningBucketRecord>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
