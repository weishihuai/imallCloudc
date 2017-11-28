
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.platform.entity.SupplierDoc;
import com.imall.iportal.core.platform.entity.SupplierDocCertificatesFile;
import com.imall.iportal.core.platform.repository.SupplierDocRepositoryCustom;
import com.imall.iportal.core.platform.vo.SupplierDocSearchParam;
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
 * 供应商 档案(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SupplierDocRepositoryImpl implements SupplierDocRepositoryCustom{

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SupplierDoc> query(Pageable pageable, SupplierDocSearchParam supplierDocSearchParam) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_ptfm_supplier_doc  where 1=1 ");
        //供货 状态
        if (StringUtils.isNotBlank(supplierDocSearchParam.getState())) {
            sql.append("and STATE=:state ");
            paramMap.put(SupplierDoc.STATE, BoolCodeEnum.fromCode(supplierDocSearchParam.getState()).toCode());
        }

        //创建时间范围
        if (StringUtils.isNotBlank(supplierDocSearchParam.getStartTimeString())) {
            sql.append(" and CREATE_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(supplierDocSearchParam.getStartTimeString()));
        }
        //创建时间范围
        if (StringUtils.isNotBlank(supplierDocSearchParam.getEndTimeString())) {
            sql.append(" and CREATE_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(supplierDocSearchParam.getEndTimeString()));
        }

        //供应商名称
        if (StringUtils.isNotBlank(supplierDocSearchParam.getSupplierNm())) {
            sql.append("and SUPPLIER_NM like:supplierNm ");
            paramMap.put(SupplierDoc.SUPPLIER_NM, "%" + supplierDocSearchParam.getSupplierNm() + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, SupplierDoc.class)
                .setFirstResult(firstIdx)              .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<SupplierDoc>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }

    @Override
    public Page<SupplierDoc> queryPage(Pageable pageable, String supplierNm, String certificatesNum, String certificatesType) {
        StringBuffer sql = new StringBuffer();
        sql.append(" FROM t_ptfm_supplier_doc t  WHERE 1=1 and t.STATE='Y' ");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(supplierNm)) {
            sql.append(" AND t.SUPPLIER_NM LIKE :supplierNm ");
            paramMap.put(SupplierDoc.SUPPLIER_NM, "%" + supplierNm + "%");
        }

        if (StringUtils.isNotBlank(certificatesNum)) {
            sql.append(" AND t.id IN(SELECT s.SUPPLIER_DOC_ID FROM t_ptfm_supplier_doc_certificates_file s WHERE s.CERTIFICATES_TYPE =:certificatesType AND s.CERTIFICATES_NUM =:certificatesNum )");
            paramMap.put(SupplierDocCertificatesFile.CERTIFICATES_TYPE, certificatesType);
            paramMap.put(SupplierDocCertificatesFile.CERTIFICATES_NUM, certificatesNum);
        }


        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select t.*" + sqlStr, SupplierDoc.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);

        Query totalQ = entityManager.createNativeQuery("select count(*)" + sqlStr);
        CommonUtil.formatQueryParameter(totalQ, paramMap);
        Long total = ((BigInteger) totalQ.getSingleResult()).longValue();
        return new PageImpl<SupplierDoc>(resultQ.getResultList(), pageable, total);
    }

}
