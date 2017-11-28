
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApproveInf;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FirstManageSupplierQualityApproveInfRepository extends  IBaseRepository<FirstManageSupplierQualityApproveInf, Long>,FirstManageSupplierQualityApproveInfRepositoryCustom {

    List<FirstManageSupplierQualityApproveInf> findByFirstSupplierDrugQualityApproveId(Long firstSupplierDrugQualityApproveId);
    /**
     * 通过供应商id查询列表
     * @param supplierId
     * @return
     */
    List<FirstManageSupplierQualityApproveInf> findBySupplierId( Long supplierId);
}

