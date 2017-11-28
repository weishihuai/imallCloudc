
package com.imall.iportal.core.platform.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.platform.entity.ShopCertificatesFile;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShopCertificatesFileRepository extends  IBaseRepository<ShopCertificatesFile, Long>,ShopCertificatesFileRepositoryCustom {

    @Query("select s from ShopCertificatesFile s where s.shopId = ?1")
    List<ShopCertificatesFile> findByShopId(Long shopId);

    @Query("select s from ShopCertificatesFile s where s.shopId = ?1 and s.certificatesType = ?2 ")
    List<ShopCertificatesFile> findByShopIdAndCertificatesType(Long shopId,String certificatesType);

}

