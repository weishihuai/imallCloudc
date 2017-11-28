
package com.imall.iportal.core.platform.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.platform.entity.SupplierDoc;

import java.util.List;

/**
 * 供应商 档案(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SupplierDocRepository extends  IBaseRepository<SupplierDoc, Long>,SupplierDocRepositoryCustom {


    SupplierDoc findBySupplierCode(String supplierCode);

    List<SupplierDoc> findBySupplierNm(String supplierNm);
}

