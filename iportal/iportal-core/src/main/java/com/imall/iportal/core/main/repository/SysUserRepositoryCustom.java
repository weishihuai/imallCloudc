package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.vo.SysUserVo;
import com.imall.iportal.core.main.vo.UserParamsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

/**
 * Created by ygw on 2015/12/29.
 */
public interface SysUserRepositoryCustom {

    Page<SysUser> query(Pageable pageable, UserParamsVo paramsVo) throws ParseException;

    List<SysUserVo> findVoByServiceOrgId(Long serviceOrgId);

    List<SysUser> findByServiceOrgIdNotDeleted(Long serviceOrgId);
}
