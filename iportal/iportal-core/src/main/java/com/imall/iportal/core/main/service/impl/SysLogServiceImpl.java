package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysLog;
import com.imall.iportal.core.main.entity.SysLogData;
import com.imall.iportal.core.main.repository.SysLogRepository;
import com.imall.iportal.core.main.service.SysLogService;
import com.imall.iportal.core.main.vo.SysLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统 日志(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysLogServiceImpl extends AbstractBaseService<SysLog, Long> implements SysLogService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysLogRepository getSysLogRepository() {
		return (SysLogRepository) baseRepository;
	}


	@Transactional
	@Override
	public Long saveLog(SysLogVo logVo) throws BusinessException {
		SysLog sysLog = new SysLog();
		CommonUtil.copyProperties(logVo, sysLog);
		save(sysLog);

		for(SysLogData logData:logVo.getLogDataList()){
			logData.setLogInnerCode(logVo.getLogInnerCode());
			logData.setOperationTime(logVo.getLogTime());
			ServiceManager.sysLogDataService.save(logData);
		}
		return sysLog.getId();
	}

}