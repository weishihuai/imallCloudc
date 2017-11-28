package com.imall.iportal.core.platform.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.platform.entity.GoodsDocDailyNecessities;
import com.imall.iportal.core.platform.repository.GoodsDocDailyNecessitiesRepository;
import com.imall.iportal.core.platform.service.GoodsDocDailyNecessitiesService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDocDailyNecessitiesServiceImpl extends AbstractBaseService<GoodsDocDailyNecessities, Long> implements GoodsDocDailyNecessitiesService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsDocDailyNecessitiesRepository getGoodsDocDailyNecessitiesRepository() {
		return (GoodsDocDailyNecessitiesRepository) baseRepository;
	}

	@Override
	public GoodsDocDailyNecessities findByGoodsDocId(Long id) {
		return getGoodsDocDailyNecessitiesRepository().findByGoodsDocId(id);
	}
}