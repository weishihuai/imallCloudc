
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.vo.SysDeveloperAuthVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * T_PT_SYS_DEVELOPER_AUTH【开发者授权】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysDeveloperAuthRepositoryCustom {

    Page<SysDeveloperAuthVo> query(Pageable pageable, String userName, String isAvailable);

    SysDeveloperAuthVo findByDeveloperAuthId(Long id);
}

