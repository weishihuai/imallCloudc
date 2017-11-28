
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysLogData;

import java.util.List;

/**
 * 系统 日志 数据(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysLogDataRepository extends  IBaseRepository<SysLogData, Long>,SysLogDataRepositoryCustom {

    List<SysLogData> findByLogInnerCode(String logInnerCode);


}

