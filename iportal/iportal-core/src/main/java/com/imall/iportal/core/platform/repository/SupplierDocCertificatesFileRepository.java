
package com.imall.iportal.core.platform.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.platform.entity.SupplierDocCertificatesFile;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SupplierDocCertificatesFileRepository extends  IBaseRepository<SupplierDocCertificatesFile, Long>,SupplierDocCertificatesFileRepositoryCustom {


    @Query("select s from SupplierDocCertificatesFile s where s.supplierDocId = ?1")
    List<SupplierDocCertificatesFile> findBySupplierDocId(Long supplierDocId);

    @Query("select s from SupplierDocCertificatesFile s where s.supplierDocId = ?1 and s.certificatesType = ?2 ")
    SupplierDocCertificatesFile findBySupplierDocIdAndCertificatesType(Long supplierDocId,String certificatesType);
}

