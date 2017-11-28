
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.*;
import com.imall.commons.base.dao.IBaseRepository;

import java.util.List;

/**
 * T_PT_SYS_PARAM_INF【系统参数】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysParamInfRepository extends  IBaseRepository<SysParamInf, Long>,SysParamInfRepositoryCustom {


    List<SysParamInf> findBySysOrgId(Long sysOrgId);

    SysParamInf findBySysOrgIdAndInnerCode(Long sysOrgId , String innerCode);
}

