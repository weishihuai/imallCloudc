
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.SupplierCertificatesFile;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SupplierCertificatesFileRepository extends  IBaseRepository<SupplierCertificatesFile, Long>,SupplierCertificatesFileRepositoryCustom {

    @Query("select s from SupplierCertificatesFile s where s.supplierId = ?1")
    List<SupplierCertificatesFile> findBySupplierId(Long supplierId);

    @Query("select s from SupplierCertificatesFile s where s.supplierId = ?1 and s.certificatesType = ?2 ")
    List<SupplierCertificatesFile> findBySupplierIdAndCertificatesType(Long supplierId,String certificatesType);


}

