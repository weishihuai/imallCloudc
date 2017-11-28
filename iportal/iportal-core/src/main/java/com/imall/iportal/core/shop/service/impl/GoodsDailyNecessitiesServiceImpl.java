package com.imall.iportal.core.shop.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.GoodsDailyNecessities;
import com.imall.iportal.core.shop.repository.GoodsDailyNecessitiesRepository;
import com.imall.iportal.core.shop.service.GoodsDailyNecessitiesService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDailyNecessitiesServiceImpl extends AbstractBaseService<GoodsDailyNecessities, Long> implements GoodsDailyNecessitiesService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsDailyNecessitiesRepository getGoodsDailyNecessitiesRepository() {
		return (GoodsDailyNecessitiesRepository) baseRepository;
	}

	@Override
	public GoodsDailyNecessities findByGoodsId(Long id) {
		return getGoodsDailyNecessitiesRepository().findByGoodsId(id);
	}
}