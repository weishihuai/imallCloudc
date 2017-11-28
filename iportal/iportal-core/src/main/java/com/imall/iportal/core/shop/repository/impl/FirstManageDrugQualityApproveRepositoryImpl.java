
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApprove;
import com.imall.iportal.core.shop.repository.FirstManageDrugQualityApproveRepositoryCustom;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class FirstManageDrugQualityApproveRepositoryImpl implements FirstManageDrugQualityApproveRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<FirstManageDrugQualityApprove> query(Pageable pageable, FirstManageDrugQualityApproveSearchParam firstManageDrugQualityApproveSearchParam, Long shopId) throws ParseException {

        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_first_manage_drug_quality_approve where 1=1 AND SHOP_ID=:shopId");
        paramMap.put(FirstManageDrugQualityApprove.SHOP_ID, shopId);

        //审核状态
        if(StringUtils.isNotBlank(firstManageDrugQualityApproveSearchParam.getApproveStateCode())){
            sql.append(" AND APPROVE_STATE_CODE =:approveStateCode");
            paramMap.put(FirstManageDrugQualityApprove.APPROVE_STATE_CODE, firstManageDrugQualityApproveSearchParam.getApproveStateCode());
        }

        //创建时间范围
        if (StringUtils.isNotBlank(firstManageDrugQualityApproveSearchParam.getFromDateString())) {
            sql.append(" and CREATE_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(firstManageDrugQualityApproveSearchParam.getFromDateString()));
        }
        //创建时间范围
        if (StringUtils.isNotBlank(firstManageDrugQualityApproveSearchParam.getToDateString())) {
            sql.append(" and CREATE_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(firstManageDrugQualityApproveSearchParam.getToDateString()));
        }

        //商品名称
        if (StringUtils.isNotBlank(firstManageDrugQualityApproveSearchParam.getKeyWords())) {
            sql.append(" AND ( GOODS_NM like:goodsNm");
            paramMap.put(FirstManageDrugQualityApprove.GOODS_NM, "%" + firstManageDrugQualityApproveSearchParam.getKeyWords() + "%");
        }
        //商品编码
        if (StringUtils.isNotBlank(firstManageDrugQualityApproveSearchParam.getKeyWords())) {
            sql.append(" or GOODS_CODE like:goodsCode");
            paramMap.put(FirstManageDrugQualityApprove.GOODS_CODE,"%" + firstManageDrugQualityApproveSearchParam.getKeyWords() + "%");
        }
        //通用名称
        if (StringUtils.isNotBlank(firstManageDrugQualityApproveSearchParam.getKeyWords())) {
            sql.append(" or COMMON_NM like:commonNm");
            paramMap.put(FirstManageDrugQualityApprove.COMMON_NM, "%" + firstManageDrugQualityApproveSearchParam.getKeyWords() + "%");
        }
        //拼音
        if (StringUtils.isNotBlank(firstManageDrugQualityApproveSearchParam.getKeyWords())) {
            sql.append(" or COMMON_NM_FIRST_SPELL like:commonNmFirstSpell ) ");
            paramMap.put(FirstManageDrugQualityApprove.COMMON_NM_FIRST_SPELL, "%" + firstManageDrugQualityApproveSearchParam.getKeyWords() + "%");
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

        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, FirstManageDrugQualityApprove.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<FirstManageDrugQualityApprove>(resultQ.getResultList(), pageable, total);
    }
}
