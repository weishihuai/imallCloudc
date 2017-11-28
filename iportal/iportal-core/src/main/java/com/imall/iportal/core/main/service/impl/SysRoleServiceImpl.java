
package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysRole;
import com.imall.iportal.core.main.repository.SysRoleRepository;
import com.imall.iportal.core.main.service.SysRoleService;
import com.imall.iportal.core.main.valid.SysRoleSaveValid;
import com.imall.iportal.core.main.valid.SysRoleUpdateValid;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_ROLE【角色】(服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends AbstractBaseService<SysRole, Long> implements SysRoleService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private SysRoleRepository getSysRoleRepository() {
		return (SysRoleRepository) baseRepository;
	}

	@Override
	public Page<SysRole> query(Pageable pageable, Long orgId) {
		return getSysRoleRepository().query(pageable, orgId);
	}

    @Override
    public List<SysRole> findByOrgId(Long orgId) {
        return getSysRoleRepository().findByOrgIdAndIsAvailableAndIsDefaultAdmin(orgId, BoolCodeEnum.YES.toCode(),BoolCodeEnum.NO.toCode());
    }

	@Override
	public List<String> findRoleNameByJobId(Long jobId) {
		return getSysRoleRepository().findRoleNameByJobId(jobId);
	}

	@Transactional
	@Override
	public SysRole save(SysRoleSaveValid sysRoleSaveValid) {
		return save(CommonUtil.copyProperties(sysRoleSaveValid, new SysRole()));
	}

    @Transactional
    @Override
    public SysRole update(SysRoleUpdateValid sysRoleUpdateValid) {
        SysRole sysRole = findOne(sysRoleUpdateValid.getId());
        if ( sysRole== null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        sysRole.setRoleName(sysRoleUpdateValid.getRoleName());
        sysRole.setRoleCode(sysRoleUpdateValid.getRoleCode());
        sysRole.setDescription(sysRoleUpdateValid.getDescription());
        sysRole.setOrderby(sysRoleUpdateValid.getOrderby());
        sysRole.setIsAvailable(BoolCodeEnum.fromCode(sysRoleUpdateValid.getIsAvailable()).toCode());
        return save(CommonUtil.copyProperties(sysRoleUpdateValid, new SysRole()));
    }

	@Override
	public Boolean checkRoleCodeIsExist(String roleCode, Long roleId) {
		SysRole sysRole = getSysRoleRepository().findByRoleCode(roleCode);
		return (sysRole == null && roleId == null) || sysRole == null || sysRole.getId().equals(roleId);

	}
}