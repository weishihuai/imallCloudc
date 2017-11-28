
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.Supplier;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SupplierRepository extends  IBaseRepository<Supplier, Long>,SupplierRepositoryCustom {

    Supplier findBySupplierCodeAndShopId(String supplierCode, Long shopId);

    List<Supplier> findBySupplierNmAndShopId(String supplierNm,Long shopId);
}

