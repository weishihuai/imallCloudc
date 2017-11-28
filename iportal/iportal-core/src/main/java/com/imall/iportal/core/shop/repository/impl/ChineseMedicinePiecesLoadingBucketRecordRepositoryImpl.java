
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.ChineseMedicinePiecesLoadingBucketRecord;
import com.imall.iportal.core.shop.repository.ChineseMedicinePiecesLoadingBucketRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.ChineseMedicinePiecesLoadingBucketRecordSearchParam;
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
public class ChineseMedicinePiecesLoadingBucketRecordRepositoryImpl implements ChineseMedicinePiecesLoadingBucketRecordRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<ChineseMedicinePiecesLoadingBucketRecord> query(Pageable pageable, ChineseMedicinePiecesLoadingBucketRecordSearchParam chineseMedicinePiecesLoadingBucketRecordSearchParam) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_chinese_medicine_pieces_loading_bucket_record  where 1=1 and SHOP_ID =:shopId ");
        paramMap.put(ChineseMedicinePiecesLoadingBucketRecord.SHOP_ID, chineseMedicinePiecesLoadingBucketRecordSearchParam.getShopId());
        //拼音码
        if (StringUtils.isNotEmpty(chineseMedicinePiecesLoadingBucketRecordSearchParam.getSearchValue())) {
            sql.append("and( GOODS_PINYIN like:goodsPinyin or COMMON_NM like:commonNm   or GOODS_NM like:goodsNm)");
            paramMap.put(ChineseMedicinePiecesLoadingBucketRecord.GOODS_PINYIN, "%" +chineseMedicinePiecesLoadingBucketRecordSearchParam.getSearchValue()  + "%");
            paramMap.put(ChineseMedicinePiecesLoadingBucketRecord.COMMON_NM, "%" + chineseMedicinePiecesLoadingBucketRecordSearchParam.getSearchValue()  + "%");
            paramMap.put(ChineseMedicinePiecesLoadingBucketRecord.GOODS_NM, "%" + chineseMedicinePiecesLoadingBucketRecordSearchParam.getSearchValue()  + "%");

        }
        //批号
        if (StringUtils.isNotEmpty(chineseMedicinePiecesLoadingBucketRecordSearchParam.getBatch())) {
            sql.append("and BATCH like:batch ");
            paramMap.put(ChineseMedicinePiecesLoadingBucketRecord.BATCH,"%" + chineseMedicinePiecesLoadingBucketRecordSearchParam.getBatch()  + "%");
        }
        //装斗人
        if (StringUtils.isNotEmpty(chineseMedicinePiecesLoadingBucketRecordSearchParam.getLoadingBucketManNm())) {
            sql.append("and LOADING_BUCKET_MAN_NM like:loadingBucketManNm ");
            paramMap.put(ChineseMedicinePiecesLoadingBucketRecord.LOADING_BUCKET_MAN_NM,"%" + chineseMedicinePiecesLoadingBucketRecordSearchParam.getLoadingBucketManNm() + "%");
        }

        //创建时间范围
        if (StringUtils.isNotEmpty(chineseMedicinePiecesLoadingBucketRecordSearchParam.getStartTimeString())) {
            sql.append(" and LOADING_BUCKET_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.getDayStartTime(chineseMedicinePiecesLoadingBucketRecordSearchParam.getStartTimeString()));
        }
        //创建时间范围
        if (StringUtils.isNotEmpty(chineseMedicinePiecesLoadingBucketRecordSearchParam.getEndTimeString())) {
            sql.append(" and LOADING_BUCKET_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(chineseMedicinePiecesLoadingBucketRecordSearchParam.getEndTimeString()));
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, ChineseMedicinePiecesLoadingBucketRecord.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<ChineseMedicinePiecesLoadingBucketRecord>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
