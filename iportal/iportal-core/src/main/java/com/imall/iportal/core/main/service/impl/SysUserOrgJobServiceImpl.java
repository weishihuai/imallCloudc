package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.entity.SysOrg;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.repository.SysUserOrgJobRepository;
import com.imall.iportal.core.main.service.SysUserOrgJobService;
import com.imall.iportal.core.main.valid.SysUserOrgJobSaveValid;
import com.imall.iportal.core.main.valid.SysUserOrgJobUpdateValid;
import com.imall.iportal.core.main.vo.SysUserOrgJobVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_USER_ORG_JOB【组织岗位】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysUserOrgJobServiceImpl extends AbstractBaseService<SysUserOrgJob, Long> implements SysUserOrgJobService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysUserOrgJobRepository getSysUserOrgJobRepository() {
		return (SysUserOrgJobRepository) baseRepository;
	}

	@Override
	public SysUserOrgJobVo findOneVo(Long paramID) {
		SysUserOrgJob sysUserOrgJob = findOne(paramID);
		SysUserOrgJobVo resultVo = CommonUtil.copyProperties(sysUserOrgJob, new SysUserOrgJobVo());
		SysUser sysUser = ServiceManager.sysUserService.findOne(sysUserOrgJob.getUserId());
		resultVo.setUserName(sysUser.getUserName());
		resultVo.setRealName(sysUser.getRealName());
		SysOrg sysOrg = ServiceManager.sysOrgService.findOne(sysUserOrgJob.getOrgId());
		resultVo.setOrgCode(sysOrg.getOrgCode());
		resultVo.setOrgName(sysOrg.getOrgName());
		SysJob sysJob = ServiceManager.sysJobService.findOne(sysUserOrgJob.getJobId());
		resultVo.setJobCode(sysJob.getJobCode());
		resultVo.setJobName(sysJob.getJobName());
		return resultVo;
	}

	@Transactional
	@Override
	public SysUserOrgJob update(SysUserOrgJobUpdateValid sysUserOrgJobUpdateValid) {
		return save(CommonUtil.copyProperties(sysUserOrgJobUpdateValid, new SysUserOrgJob()));
	}

	@Transactional
	@Override
	public SysUserOrgJob save(SysUserOrgJobSaveValid sysUserOrgJobSaveValid) {
		return save(CommonUtil.copyProperties(sysUserOrgJobSaveValid, new SysUserOrgJob()));
	}

	@Transactional
	@Override
	public void deleteByUserId(Long userId) {
		getSysUserOrgJobRepository().deleteByUserId(userId);
	}

	@Override
	public List<SysUserOrgJobVo> findVoByUserId(Long userId) {
		return getSysUserOrgJobRepository().findVoByUserId(userId);
	}

	@Override
	public List<SysUserOrgJob> findByUserId(Long userId) {
		return getSysUserOrgJobRepository().findByUserId(userId);
	}

	@Override
	public List<SysUserOrgJob> findByOrgId(Long orgId) {
		return getSysUserOrgJobRepository().findByOrgId(orgId);
	}

	@Override
	public List<SysUserOrgJob> findByOrgIdAndIsmain(Long orgId) {
		return getSysUserOrgJobRepository().findByOrgIdAndIsmain(orgId);
	}

	@Override
	public boolean isExist(Long userId, Long orgId, Long jobId) {
		return getSysUserOrgJobRepository().existsByUserIdAndOrgIdAndJobId(userId, orgId, jobId);
	}

	@Override
	public SysUserOrgJob getByUserIdOrgIdJobId(Long userId, Long orgId, Long jobId) {
		return getSysUserOrgJobRepository().findByUserIdAndOrgIdAndJobId(userId, orgId, jobId);
	}

	@Override
	public SysUserOrgJob getIsmainByUserId(Long userId) {
        SysUserOrgJob sysUserOrgJob = getSysUserOrgJobRepository().findByUserIdAndIsmain(userId);
		if(sysUserOrgJob==null){
			SysUser sysUser = ServiceManager.sysUserService.findOne(userId);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.USER_MAIN_JOB_NOT_EXIST);
			throw new BusinessException(ResGlobal.USER_MAIN_JOB_NOT_EXIST,String.format(message,new Object[]{sysUser.getUserName()}));
		}
		return sysUserOrgJob;
	}

	@Transactional
	@Override
	public void updateJobIsmain(Long userId, Long jobId) {
		List<SysUserOrgJob> userOrgJobList = getSysUserOrgJobRepository().findByUserId( userId);
		for(SysUserOrgJob userOrgJob:userOrgJobList){
			if(BoolCodeEnum.YES == BoolCodeEnum.fromCode(userOrgJob.getIsmain())){
				userOrgJob.setIsmain(BoolCodeEnum.NO.toCode());
				baseRepository.save(userOrgJob);
				break;
			}
		}

		userOrgJobList = getSysUserOrgJobRepository().findByUserId(userId);
		for(SysUserOrgJob userOrgJob:userOrgJobList){
			if(userOrgJob.getJobId().equals(jobId)){
				userOrgJob.setIsmain(BoolCodeEnum.YES.toCode());
				baseRepository.save(userOrgJob);
				break;
			}
		}
	}

	@Transactional
	@Override
	public SysUserOrgJob saveOrUpdate(Long userId, Long orgId, Long jobId, String ismain) throws BusinessException {
		SysUserOrgJob dbObj = null;
		if(!isExist(userId, orgId, jobId)){
			SysUserOrgJob userOrgJob = new SysUserOrgJob();
			userOrgJob.setUserId(userId);
			userOrgJob.setOrgId(orgId);
			userOrgJob.setJobId(jobId);
			userOrgJob.setIsmain(ismain);
			dbObj = baseRepository.save(userOrgJob);
		}
		else {
			dbObj = getByUserIdOrgIdJobId(userId, orgId, jobId);
			dbObj.setIsmain(ismain);
			baseRepository.save(dbObj);
		}
		return dbObj;
	}

	@Override
	public Boolean existsUserByOrgId(Long orgId) {
		return getSysUserOrgJobRepository().existsUserByOrgId(orgId);
	}

	@Override
	public Boolean existsUserByUserId(Long userId) {
		return getSysUserOrgJobRepository().existsUserByUserId(userId);
	}
}