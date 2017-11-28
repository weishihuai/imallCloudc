
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DocInf;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DocInfRepository extends  IBaseRepository<DocInf, Long>,DocInfRepositoryCustom {

    List<DocInf> findByDocType(String docType);

}

