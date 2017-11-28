package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.vo.SysRoleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ygw on 2015/12/31.
 */
public interface SysAuthRepositoryCustom {

    Page<SysRoleVo> query(Pageable pageable, Long jobId, String roleName, String roleDescription);
}
