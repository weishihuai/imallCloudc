
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.DrugStopSaleNotice;
import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApprove;
import com.imall.iportal.core.shop.repository.DrugStopSaleNoticeRepositoryCustom;
import com.imall.iportal.core.shop.vo.DrugStopSaleNoticeSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.annotation.Resource;
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
public class DrugStopSaleNoticeRepositoryImpl implements DrugStopSaleNoticeRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugStopSaleNotice> query(Pageable pageable, DrugStopSaleNoticeSearchParam drugStopSaleNoticeSearchParam, Long shopId) throws ParseException {

        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_stop_sale_notice where 1=1 AND SHOP_ID=:shopId");
        paramMap.put(DrugStopSaleNotice.SHOP_ID, shopId);

        //复核人名称
        if (StringUtils.isNotBlank(drugStopSaleNoticeSearchParam.getApproveManName())) {
            sql.append(" AND APPROVE_MAN_ID IN( SELECT ID FROM t_pt_sys_user WHERE REAL_NAME LIKE:realName )  ");
            paramMap.put(SysUser.REAL_NAME, "%" + drugStopSaleNoticeSearchParam.getApproveManName() + "%");
        }

        //制单人名称
        if (StringUtils.isNotBlank(drugStopSaleNoticeSearchParam.getDocMakerNm())) {
            sql.append(" AND DOC_MAKER_NM LIKE:docMakerNm ");
            paramMap.put(DrugStopSaleNotice.DOC_MAKER_NM, "%" + drugStopSaleNoticeSearchParam.getDocMakerNm() + "%");
        }

        //停售单号
        if (StringUtils.isNotBlank(drugStopSaleNoticeSearchParam.getStopSaleNum())) {
            sql.append(" AND STOP_SALE_NUM =:stopSaleNum ");
            paramMap.put(DrugStopSaleNotice.STOP_SALE_NUM,  drugStopSaleNoticeSearchParam.getStopSaleNum() );
        }

        //停售时间范围
        if (StringUtils.isNotBlank(drugStopSaleNoticeSearchParam.getFromStopSaleDateStr())) {
            sql.append(" and STOP_SALE_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(drugStopSaleNoticeSearchParam.getFromStopSaleDateStr()));
        }
        //停售时间范围
        if (StringUtils.isNotBlank(drugStopSaleNoticeSearchParam.getToStopSaleDateStr())) {
            sql.append(" and STOP_SALE_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(drugStopSaleNoticeSearchParam.getToStopSaleDateStr()));
        }


        //制单时间范围
        if (StringUtils.isNotBlank(drugStopSaleNoticeSearchParam.getFromMakeTimeStr())) {
            sql.append(" and MAKE_TIME >=:beginTimeStr ");
            paramMap.put("beginTimeStr", DateTimeUtils.convertStringToDate(drugStopSaleNoticeSearchParam.getFromMakeTimeStr()));
        }
        //制单时间范围
        if (StringUtils.isNotBlank(drugStopSaleNoticeSearchParam.getToMakeTimeStr())) {
            sql.append(" and MAKE_TIME <=:endTimeStr ");
            paramMap.put("endTimeStr", DateTimeUtils.getDayEndTime(drugStopSaleNoticeSearchParam.getToMakeTimeStr()));
        }


        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, DrugStopSaleNotice.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<DrugStopSaleNotice>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
