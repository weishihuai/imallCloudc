
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.DestroyRecord;
import com.imall.iportal.core.shop.entity.DisqualificationDrugProcessRecord;
import com.imall.iportal.core.shop.repository.DisqualificationDrugProcessRecordRepositoryCustom;
import com.imall.iportal.core.shop.vo.DisqualificationDrugProcessRecordSearchParam;
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
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class DisqualificationDrugProcessRecordRepositoryImpl implements DisqualificationDrugProcessRecordRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DisqualificationDrugProcessRecord> query(Pageable pageable, DisqualificationDrugProcessRecordSearchParam disqualificationDrugProcessRecordSearchParam) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_disqualification_drug_process_record R where 1=1 ");

        if (disqualificationDrugProcessRecordSearchParam.getShopId() != null) {
            sql.append(" AND R.SHOP_ID = :shopId ");
            paramMap.put(DestroyRecord.SHOP_ID, disqualificationDrugProcessRecordSearchParam.getShopId());
        }

        //商品编码、拼音码、名称
        if (StringUtils.isNotBlank(disqualificationDrugProcessRecordSearchParam.getKeyword())) {
            sql.append(" AND (R.GOODS_PINYIN LIKE :keyword OR R.COMMON_NM LIKE :keyword OR R.GOODS_NM LIKE :keyword OR R.GOODS_CODE like:goodsCode) ");
            paramMap.put("keyword", "%" + disqualificationDrugProcessRecordSearchParam.getKeyword() + "%");
            paramMap.put("goodsCode","%" +  disqualificationDrugProcessRecordSearchParam.getKeyword() + "%");
        }

        //日期
        if (StringUtils.isNotBlank(disqualificationDrugProcessRecordSearchParam.getRecordDateStartTimeString())) {
            sql.append(" AND R.RECORD_DATE >= :fromTime ");
            paramMap.put("fromTime", DateTimeUtils.getDayStartTime(disqualificationDrugProcessRecordSearchParam.getRecordDateStartTimeString()));
        }

        if (StringUtils.isNotBlank(disqualificationDrugProcessRecordSearchParam.getRecordDateEndTimeString())) {
            sql.append(" AND R.RECORD_DATE <= :toTime ");
            paramMap.put("toTime", DateTimeUtils.getDayEndTime(disqualificationDrugProcessRecordSearchParam.getRecordDateEndTimeString()));
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, DisqualificationDrugProcessRecord.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<DisqualificationDrugProcessRecord>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
