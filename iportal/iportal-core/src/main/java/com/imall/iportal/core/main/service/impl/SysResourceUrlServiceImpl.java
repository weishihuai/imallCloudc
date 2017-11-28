package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.entity.SysResourceUrl;
import com.imall.iportal.core.main.repository.SysResourceUrlRepository;
import com.imall.iportal.core.main.service.SysResourceUrlService;
import com.imall.iportal.core.main.valid.SysResourceUrlSaveValid;
import com.imall.iportal.core.main.valid.SysResourceUrlUpdateValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_RESOUCE_URL【资源URL】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysResourceUrlServiceImpl extends AbstractBaseService<SysResourceUrl, Long> implements SysResourceUrlService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysResourceUrlRepository getSysResouceUrlRepository() {
		return (SysResourceUrlRepository) baseRepository;
	}

	@Override
	public Page<SysResourceUrl> query(Pageable pageable,Long resourceId , String url) {
		return getSysResouceUrlRepository().query(pageable, resourceId, url);
	}

	@Override
	public List<SysResourceUrl> findByResourceId(Long resourceId) {
		return getSysResouceUrlRepository().findByResourceId(resourceId);
	}

	@Transactional
	@Override
	public SysResourceUrl save(SysResourceUrlSaveValid sysResourceUrlSaveValid) {
		return save(CommonUtil.copyProperties(sysResourceUrlSaveValid, new SysResourceUrl()));
	}

	@Transactional
	@Override
	public SysResourceUrl update(SysResourceUrlUpdateValid sysResourceUrlUpdateValid) {
		return save(CommonUtil.copyProperties(sysResourceUrlUpdateValid, new SysResourceUrl()));
	}

}