package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysExceptionCode;
import com.imall.iportal.core.main.repository.SysExceptionCodeRepository;
import com.imall.iportal.core.main.service.SysExceptionCodeService;
import com.imall.iportal.core.main.valid.SysExceptionCodeSaveValid;
import com.imall.iportal.core.main.valid.SysExceptionCodeUpdateValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysExceptionCodeServiceImpl extends AbstractBaseService<SysExceptionCode, Long> implements SysExceptionCodeService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysExceptionCodeRepository getSysExceptionCodeRepository() {
		return (SysExceptionCodeRepository) baseRepository;
	}

	@Override
	public Page<SysExceptionCode> query(Pageable pageable, String code, String exceptionMsg) {
		return getSysExceptionCodeRepository().query(pageable, code, exceptionMsg);
	}

	@Override
	@Transactional
	public SysExceptionCode save(SysExceptionCode code){
		SysExceptionCode dbCode = super.save(code);
		init();
		return dbCode;
	}

	@Override
	@Transactional
	public SysExceptionCode update(SysExceptionCode code){
		SysExceptionCode dbCode = super.update(code);
		init();
		return dbCode;
	}

	private Map<String, String> codeMessageMap;

	@PostConstruct
	public void init(){
		Map<String, String> codeMessageMap = new HashMap<String, String>();
		List<SysExceptionCode> list = baseRepository.findAll();
		for(SysExceptionCode code : list){
			codeMessageMap.put(code.getCode(), code.getExceptionMsg());
		}
		setCodeMessageMap(codeMessageMap);
	}

	@Override
	public String getMessageByCode(String code){
		return codeMessageMap.get(code);
	}


	@Transactional
	@Override
	public SysExceptionCode save(SysExceptionCodeSaveValid sysExceptionCodeSaveValid) {
		return save(CommonUtil.copyProperties(sysExceptionCodeSaveValid, new SysExceptionCode()));
	}

	@Transactional
	@Override
	public SysExceptionCode update(SysExceptionCodeUpdateValid sysExceptionCodeUpdateValid) {
		return save(CommonUtil.copyProperties(sysExceptionCodeUpdateValid, new SysExceptionCode()));
	}

	public void setCodeMessageMap(Map<String, String> codeMessageMap) {
		this.codeMessageMap = codeMessageMap;
	}
}