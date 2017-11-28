
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysLogData;
import com.imall.iportal.core.main.vo.SysLogDataVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 系统 日志 数据(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysLogDataRepositoryCustom{

    Page<SysLogDataVo> query(Pageable pageable, String tableKey, Long objectId, String searchText);

}

