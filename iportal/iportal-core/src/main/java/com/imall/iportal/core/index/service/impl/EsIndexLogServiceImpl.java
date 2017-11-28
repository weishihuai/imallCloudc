package com.imall.iportal.core.index.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.imall.iportal.core.index.entity.EsIndexLog;
import com.imall.iportal.core.index.repository.EsIndexLogRepository;
import com.imall.iportal.core.index.service.EsIndexLogService;
import com.imall.commons.base.service.impl.AbstractBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * T_PT_ES_INDEX_LOG【索引日志】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class EsIndexLogServiceImpl extends AbstractBaseService<EsIndexLog, Long> implements EsIndexLogService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private EsIndexLogRepository getEsIndexLogRepository() {
		return (EsIndexLogRepository) baseRepository;
	}

}