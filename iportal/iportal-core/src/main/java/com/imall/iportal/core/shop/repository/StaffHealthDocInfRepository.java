
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.StaffHealthDocInf;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StaffHealthDocInfRepository extends  IBaseRepository<StaffHealthDocInf, Long>,StaffHealthDocInfRepositoryCustom {

    List<StaffHealthDocInf> findByStaffHealthDocId(Long StaffHealthDocInf);
}

