
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApprove;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.repository.FirstManageSupplierQualityApproveRepositoryCustom;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveSearchParam;
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
public class FirstManageSupplierQualityApproveRepositoryImpl implements FirstManageSupplierQualityApproveRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<FirstManageSupplierQualityApprove> query(Pageable pageable, FirstManageSupplierQualityApproveSearchParam firstManageSupplierQualityApproveSearchParam) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sql.append(" FROM T_SHP_FIRST_MANAGE_SUPPLIER_QUALITY_APPROVE t  WHERE 1=1 and t.SHOP_ID =:shopId  ");
        paramMap.put(FirstManageSupplierQualityApprove.SHOP_ID, firstManageSupplierQualityApproveSearchParam.getShopId());
        if (StringUtils.isNotBlank(firstManageSupplierQualityApproveSearchParam.getState())) {
            sql.append(" AND t.APPROVE_STATE_CODE =:approveStateCode ");
            paramMap.put(FirstManageSupplierQualityApprove.APPROVE_STATE_CODE, firstManageSupplierQualityApproveSearchParam.getState());
        }
        //审核 开始 时间
        if (StringUtils.isNotBlank(firstManageSupplierQualityApproveSearchParam.getStartTimeString())) {
            sql.append(" and t.QUALITY_APPROVE_TIME >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(firstManageSupplierQualityApproveSearchParam.getStartTimeString()));
        }
        //审核 结束 时间
        if (StringUtils.isNotBlank(firstManageSupplierQualityApproveSearchParam.getEndTimeString())) {
            sql.append(" and t.QUALITY_APPROVE_TIME <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(firstManageSupplierQualityApproveSearchParam.getEndTimeString()));
        }

        if (StringUtils.isNotBlank(firstManageSupplierQualityApproveSearchParam.getSearchValue())) {
            sql.append(" AND t.SUPPLIER_ID IN(SELECT s.id FROM t_shp_supplier s WHERE s.PINYIN like:pinyin Or s.SUPPLIER_NM like:supplierNm  Or s.SUPPLIER_CODE =:supplierCode)");
            paramMap.put(Supplier.PINYIN, "%"+firstManageSupplierQualityApproveSearchParam.getSearchValue()+"%");
            paramMap.put(Supplier.SUPPLIER_NM, "%"+firstManageSupplierQualityApproveSearchParam.getSearchValue()+"%");
            paramMap.put(Supplier.SUPPLIER_CODE, firstManageSupplierQualityApproveSearchParam.getSearchValue());
        }

        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultTotal = entityManager.createNativeQuery("select count(*)" + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        int total = ((BigInteger)resultTotal.getSingleResult()).intValue();
        if (firstIdx >= total) {
            int intValue = total != 0 ? total - 1 : total;
            firstIdx = intValue / 10 * pageSize;
            pageable = new PageRequest((total - 1) / 10, pageSize);
        }
        Query resultQ = entityManager.createNativeQuery("select t.*" + sqlStr, FirstManageSupplierQualityApprove.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<FirstManageSupplierQualityApprove>(resultQ.getResultList(), pageable, total);
    }
}
