
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.ApproveStateCodeEnum;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.entity.SupplierCertificatesFile;
import com.imall.iportal.core.shop.repository.SupplierRepositoryCustom;
import com.imall.iportal.core.shop.vo.SupplierSearchParam;
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
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class SupplierRepositoryImpl implements SupplierRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<Supplier> query(Pageable pageable, SupplierSearchParam supplierSearchParam) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_supplier  where 1=1 and SHOP_ID =:shopId ");
        paramMap.put(Supplier.SHOP_ID, supplierSearchParam.getShopId());
        //拼音码
        if (StringUtils.isNotBlank(supplierSearchParam.getPinyin())) {
            sql.append("and (PINYIN like:pinyin  OR SUPPLIER_NM like:supplierNm OR SUPPLIER_CODE like:supplierCode)");
            paramMap.put(Supplier.PINYIN, "%" + supplierSearchParam.getPinyin() + "%");
            paramMap.put(Supplier.SUPPLIER_NM, "%" + supplierSearchParam.getPinyin() + "%");
            paramMap.put(Supplier.SUPPLIER_NM, "%" + supplierSearchParam.getPinyin() + "%");
            paramMap.put(Supplier.SUPPLIER_CODE, "%" + supplierSearchParam.getPinyin() + "%");

        }
        //供货 状态
        if (StringUtils.isNotBlank(supplierSearchParam.getState())) {
            sql.append("and STATE=:state ");
            paramMap.put(Supplier.STATE, BoolCodeEnum.fromCode(supplierSearchParam.getState()).toCode());
        }
        //供货 审核 状态
        if (StringUtils.isNotBlank(supplierSearchParam.getApproveStateCode())) {
            sql.append("and APPROVE_STATE_CODE=:approveStateCode ");
            paramMap.put(Supplier.APPROVE_STATE_CODE, ApproveStateCodeEnum.fromCode(supplierSearchParam.getApproveStateCode()).toCode());
        }

        //创建时间范围
        if (StringUtils.isNotBlank(supplierSearchParam.getStartTimeString())) {
            sql.append(" and CREATE_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(supplierSearchParam.getStartTimeString()));
        }
        //创建时间范围
        if (StringUtils.isNotBlank(supplierSearchParam.getEndTimeString())) {
            sql.append(" and CREATE_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(supplierSearchParam.getEndTimeString()));
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
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, Supplier.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        return new PageImpl<Supplier>(resultQ.getResultList(), pageable, total);
    }

    @Override
    public Page<Supplier> queryPage(Pageable pageable, String supplierNm, String certificatesType, String certificatesNum, Long shopId) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //启用且审核通过
        sql.append(" FROM t_shp_supplier t  WHERE 1=1 and t.APPROVE_STATE_CODE ='PASS_APPROVE' and t.STATE ='Y'  and SHOP_ID =:shopId  ");
        paramMap.put(Supplier.SHOP_ID, shopId);
        if (StringUtils.isNotBlank(supplierNm)) {
            sql.append(" AND t.SUPPLIER_NM LIKE :supplierNm ");
            paramMap.put(Supplier.SUPPLIER_NM, "%" + supplierNm + "%");
        }

        if (StringUtils.isNotBlank(certificatesNum)) {
            sql.append(" AND t.id IN(SELECT s.SUPPLIER_ID FROM t_shp_supplier_certificates_file s WHERE s.CERTIFICATES_TYPE =:certificatesType AND s.CERTIFICATES_NUM =:certificatesNum )");
            paramMap.put(SupplierCertificatesFile.CERTIFICATES_TYPE, certificatesType);
            paramMap.put(SupplierCertificatesFile.CERTIFICATES_NUM, certificatesNum);
        }


        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select t.*" + sqlStr, Supplier.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);

        Query totalQ = entityManager.createNativeQuery("select count(*)" + sqlStr);
        CommonUtil.formatQueryParameter(totalQ, paramMap);
        Long total = ((BigInteger) totalQ.getSingleResult()).longValue();
        return new PageImpl<Supplier>(resultQ.getResultList(), pageable, total);
    }

}
