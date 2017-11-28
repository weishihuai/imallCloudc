package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.vo.SysUserOrgJobVo;

import java.util.List;

/**
 * Created by ygw on 2015/12/29.
 */
public interface SysUserOrgJobRepositoryCustom {

    List<SysUserOrgJobVo> findVoByUserId(Long userId);

}
