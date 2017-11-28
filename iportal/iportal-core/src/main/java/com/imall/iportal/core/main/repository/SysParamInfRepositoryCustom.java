
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysParamInf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * T_PT_SYS_PARAM_INF【系统参数】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysParamInfRepositoryCustom{

    Page<SysParamInf> query(Pageable pageable,Long sysOrgId, String innerCode, String paramNm);

    List<SysParamInf> listBySysOrgIdAndGroupTypeCode(Long sysOrgId, String groupTypeCode);
}

