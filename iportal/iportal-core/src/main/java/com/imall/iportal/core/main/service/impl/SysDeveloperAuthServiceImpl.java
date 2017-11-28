package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.core.main.entity.SysDeveloperAuth;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.repository.SysDeveloperAuthRepository;
import com.imall.iportal.core.main.service.SysDeveloperAuthService;
import com.imall.iportal.core.main.valid.SysDeveloperAuthSaveValid;
import com.imall.iportal.core.main.valid.SysDeveloperAuthUpdateValid;
import com.imall.iportal.core.main.vo.SysDeveloperAuthVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_DEVELOPER_AUTH【开发者授权】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysDeveloperAuthServiceImpl extends AbstractBaseService<SysDeveloperAuth, Long> implements SysDeveloperAuthService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysDeveloperAuthRepository getSysDeveloperAuthRepository() {
		return (SysDeveloperAuthRepository) baseRepository;
	}

	@Override
	public List<SysDeveloperAuth> findByUserIdAndIsAvailable(Long userId) {
		return getSysDeveloperAuthRepository().findByUserIdAndIsAvailable(userId);
	}

	@Override
	public Page<SysDeveloperAuthVo> query(Pageable pageable, String userName, String isAvailable) {
		BoolCodeEnum isAvailableEnum = StringUtils.isBlank(isAvailable) ? null : BoolCodeEnum.fromCode(isAvailable);
		return getSysDeveloperAuthRepository().query(pageable, userName, isAvailableEnum == null ? null : isAvailableEnum.toCode());
	}

	@Transactional
	@Override
	public SysDeveloperAuth save(SysDeveloperAuthSaveValid sysDeveloperAuthSaveValid) {
		SysDeveloperAuthVo sysDeveloperAuthVo = CommonUtil.copyProperties(sysDeveloperAuthSaveValid, new SysDeveloperAuthVo());
		SysUser sysUser = ServiceManager.sysUserService.findByUserName(sysDeveloperAuthVo.getUserName());
		if (sysUser == null) {
			throw new  RuntimeException("用户不存在，请检查用户名称是否填写正确");
		}
		sysDeveloperAuthVo.setUserId(sysUser.getId());
		SysDeveloperAuth sysDeveloperAuth = CommonUtil.copyProperties(sysDeveloperAuthVo, new SysDeveloperAuth());
		SysApp sysApp = ServiceManager.sysAppService.findOne(sysDeveloperAuthVo.getAppId());
		sysDeveloperAuth.setAppName(sysApp.getAppName());
		return save(sysDeveloperAuth);
	}

	@Override
	public SysDeveloperAuthVo findByDeveloperAuthId(Long id) {
		return getSysDeveloperAuthRepository().findByDeveloperAuthId(id);
	}

	@Transactional
	@Override
	public SysDeveloperAuth update(SysDeveloperAuthUpdateValid sysDeveloperAuthUpdateValid) {
		return save(CommonUtil.copyProperties(sysDeveloperAuthUpdateValid, new SysDeveloperAuthVo()));
	}

}