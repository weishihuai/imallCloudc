
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApprove;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FirstManageSupplierQualityApproveRepositoryCustom{
    /**
     * 获取 供应商审核 列表
     * @param pageable
     * @param firstManageSupplierQualityApproveSearchParam
     * @return
     * @throws ParseException
     */
    Page<FirstManageSupplierQualityApprove> query(Pageable pageable, FirstManageSupplierQualityApproveSearchParam firstManageSupplierQualityApproveSearchParam) throws ParseException;

}

