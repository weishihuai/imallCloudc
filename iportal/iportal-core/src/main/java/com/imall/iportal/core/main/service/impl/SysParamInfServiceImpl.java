package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysParamInf;
import com.imall.iportal.core.main.repository.SysParamInfRepository;
import com.imall.iportal.core.main.service.SysParamInfService;
import com.imall.iportal.core.main.valid.SysParamInfBatchUpdateValid;
import com.imall.iportal.core.main.valid.SysParamInfSaveValid;
import com.imall.iportal.core.main.valid.SysParamInfUpdateValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_PARAM_INF【系统参数】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysParamInfServiceImpl extends AbstractBaseService<SysParamInf, Long> implements SysParamInfService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysParamInfRepository getSysParamInfRepository() {
		return (SysParamInfRepository) baseRepository;
	}

	@Override
	public SysParamInf findOneByOrgAndInnerCode(Long sysOrgId, String innerCode) throws BusinessException {
		return getSysParamInfRepository().findBySysOrgIdAndInnerCode(sysOrgId, innerCode);
	}

	@Transactional
	@Override
	public SysParamInf save(SysParamInfSaveValid sysParamInfSaveValid) {
		SysParamInf sysParamInf = CommonUtil.copyProperties(sysParamInfSaveValid, new SysParamInf());
		sysParamInf.setParamValueStr(sysParamInfSaveValid.getParamValue());
		return save(sysParamInf);
	}

	@Transactional
	@Override
	public SysParamInf update(SysParamInfUpdateValid sysParamInfUpdateValid) {
		SysParamInf sysParamInf = CommonUtil.copyProperties(sysParamInfUpdateValid, new SysParamInf());
		sysParamInf.setParamValueStr(sysParamInfUpdateValid.getParamValue());
		return save(sysParamInf);
	}

	@Override
	public Page<SysParamInf> query(Pageable pageable,Long sysOrgId, String innerCode, String paramNm) {
		return getSysParamInfRepository().query(pageable,sysOrgId,innerCode,paramNm);
	}

	@Transactional
	@Override
	public void updateByBatch(Long sysOrgId, List<SysParamInfBatchUpdateValid> paramInfs) {
		for(SysParamInfBatchUpdateValid paramInf: paramInfs){
			SysParamInf dbParamInf = findOneByOrgAndInnerCode(sysOrgId, paramInf.getInnerCode());
			dbParamInf.setParamValueStr(paramInf.getParamValueStr());
			save(dbParamInf);
		}
	}

	@Transactional
	@Override
	public void updateParamInf(Long sysOrgId, String innerCode, String paramValueStr) {
		SysParamInf dbParamInf = findOneByOrgAndInnerCode(sysOrgId, innerCode);
		if (dbParamInf == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.DEFAULT_ERROR_OBJECT_NOT_EXIST);
			throw new BusinessException(ResGlobal.APP_RELATE_DELETE_ERROR,String.format(message,new Object[]{SysParamInf.class.getSimpleName()})); //异常处理
		}
		dbParamInf.setParamValueStr(paramValueStr);
		save(dbParamInf);
	}

	@Override
	public List<SysParamInf> listBySysOrgIdAndGroupTypeCode(Long sysOrgId, String groupTypeCode) {
		return getSysParamInfRepository().listBySysOrgIdAndGroupTypeCode(sysOrgId, groupTypeCode);
	}

	@Override
	@Transactional
	public void createOrgParamInfByDefault(Long fromOrgId,Long toOrgId) {
		List<SysParamInf> paramInfList = getSysParamInfRepository().findBySysOrgId(fromOrgId);
		for(SysParamInf paramInf : paramInfList){
			SysParamInf sysParamInf = new SysParamInf();
			BeanUtils.copyProperties(paramInf,sysParamInf);
			sysParamInf.setId(null);
			sysParamInf.setSysOrgId(toOrgId);

			save(sysParamInf);
		}
	}
}