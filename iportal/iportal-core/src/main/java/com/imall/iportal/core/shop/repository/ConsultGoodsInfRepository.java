
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.ConsultGoodsInf;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ConsultGoodsInfRepository extends  IBaseRepository<ConsultGoodsInf, Long>,ConsultGoodsInfRepositoryCustom {

    List<ConsultGoodsInf> findByConsultServiceId(Long consultServiceId);
}

