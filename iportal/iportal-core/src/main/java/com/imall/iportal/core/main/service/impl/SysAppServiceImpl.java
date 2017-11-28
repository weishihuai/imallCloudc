package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.IMallSignUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.core.main.repository.SysAppRepository;
import com.imall.iportal.core.main.service.SysAppService;
import com.imall.iportal.core.main.valid.SysAppSaveValid;
import com.imall.iportal.core.main.valid.SysAppUpdateValid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_APP【应用】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysAppServiceImpl extends AbstractBaseService<SysApp, Long> implements SysAppService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysAppRepository getSysAppRepository() {
		return (SysAppRepository) baseRepository;
	}

	@Override
	public Page<SysApp> query(Pageable pageable, String appName, String appCname, String hostname, String isAvailable) {
		BoolCodeEnum isAvailableEnum = StringUtils.isBlank(isAvailable) ? null : BoolCodeEnum.fromCode(isAvailable);
		return  getSysAppRepository().query(pageable,appName,appCname,hostname,isAvailableEnum==null ? null : isAvailableEnum.toCode());	//调用Repository数据层获取数据
		//return getSysAppRepository().findByAppName(appName,pageable);
	}

	@Override
	public SysApp findByAppKey(String appKey) {
		return getSysAppRepository().findByAppKey(appKey);			//调用Repository数据层获取数据
	}

	@Transactional
	@Override
	public SysApp save(SysAppSaveValid sysAppSaveValid) {
		return save(CommonUtil.copyProperties(sysAppSaveValid, new SysApp()));
	}

	@Transactional
	@Override
	public SysApp update(SysAppUpdateValid sysAppUpdateValid) {
		return save(CommonUtil.copyProperties(sysAppUpdateValid, new SysApp()));
	}

	@Override
	public boolean checkIsTrustApp(String appKey, String nonceStr, String sign) throws BusinessException {
		SysApp sysApp = getSysAppRepository().findByAppKey(appKey);
		if(sysApp==null || BoolCodeEnum.NO==BoolCodeEnum.fromCode(sysApp.getIsAvailable())){
			return false;
		}
		String newSign = IMallSignUtil.md5Hash(appKey, nonceStr, sysApp.getAppSecret());
		if(newSign.equals(sign)){
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void deleteSysApp(List<Long> sysAppIds) {
		for (Long appId:sysAppIds){
			List<String> existSysResource= ServiceManager.sysResourceService.findResourceNameByAppId(appId);
			if(!existSysResource.isEmpty()){
				SysApp sysApp = findOne(appId);
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.APP_RELATE_DELETE_ERROR);
				throw new BusinessException(ResGlobal.APP_RELATE_DELETE_ERROR,String.format(message,new Object[]{sysApp.getAppCname(), StringUtils.join(existSysResource.toArray(),",")})); //异常处理
			}
		}
		delete(sysAppIds);
	}

	@Override
	public List<SysApp> findByIsAvailable(String isAvailable) {
		return getSysAppRepository().findByIsAvailable(isAvailable);
	}

}