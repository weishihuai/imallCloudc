package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.repository.SysJobRepository;
import com.imall.iportal.core.main.service.SysJobService;
import com.imall.iportal.core.main.valid.SysJobSaveValid;
import com.imall.iportal.core.main.valid.SysJobUpdateValid;
import com.imall.iportal.core.main.vo.SysJobVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_JOB【岗位/职务】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysJobServiceImpl extends AbstractBaseService<SysJob, Long> implements SysJobService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysJobRepository getSysJobRepository() {
		return (SysJobRepository) baseRepository;
	}


	@Override
	public Page<SysJobVo> query(Pageable pageable,Long orgId) {
		Page<SysJobVo> sysJobPage =getSysJobRepository().query(pageable,orgId);
		for (SysJobVo sysJobVo:sysJobPage.getContent()){
			sysJobVo.setRoleNames(StringUtils.join(ServiceManager.sysRoleService.findRoleNameByJobId(sysJobVo.getId()).toArray(),","));
		}
		return sysJobPage;
	}

	@Transactional
	@Override
	public SysJob save(SysJobSaveValid sysJobSaveValid) {
		return save(CommonUtil.copyProperties(sysJobSaveValid, new SysJob()));
	}

	@Transactional
	@Override
	public SysJob update(SysJobUpdateValid sysJobUpdateValid) {
		SysJob sysJob = findOne(sysJobUpdateValid.getId());
		if ( sysJob== null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		sysJob.setJobName(sysJobUpdateValid.getJobName());
		sysJob.setJobCode(sysJobUpdateValid.getJobCode());
		sysJob.setDescription(sysJobUpdateValid.getDescription());
		sysJob.setOrderby(sysJobUpdateValid.getOrderby());
		sysJob.setIsAvailable(BoolCodeEnum.fromCode(sysJobUpdateValid.getIsAvailable()).toCode());

		return save(sysJob);
	}

	@Transactional
	@Override
	public void deleteSysJob(List<Long> ids) {
		for (Long id:ids){
			List<String> existsRoles = ServiceManager.sysRoleService.findRoleNameByJobId(id);
			if(!existsRoles.isEmpty()){
				SysJob sysJob = findOne(id);
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.JOB_RELATE_DELETE_ERROR);
				throw new BusinessException(ResGlobal.JOB_RELATE_DELETE_ERROR,String.format(message,new Object[]{sysJob.getJobName(), StringUtils.join(existsRoles.toArray(),",")}));
			}
		}
		getSysJobRepository().delete(ids);
	}

	@Override
	public List<SysJob> findByOrgId(Long orgId) {
		List<SysJob> sysJobs=getSysJobRepository().findByOrgIdAndIsDefaultAdmin(orgId, BoolCodeEnum.NO.toCode());
		return sysJobs;
	}

	@Override
	public Boolean checkJobCodeIsExist(String name, Long id) {
		SysJob sysJob = getSysJobRepository().findByJobCode(name);
		return (sysJob == null && sysJob == null) || sysJob == null || sysJob.getId().equals(id);

	}
}