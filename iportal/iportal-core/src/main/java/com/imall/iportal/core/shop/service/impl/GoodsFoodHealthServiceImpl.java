package com.imall.iportal.core.shop.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.GoodsFoodHealth;
import com.imall.iportal.core.shop.repository.GoodsFoodHealthRepository;
import com.imall.iportal.core.shop.service.GoodsFoodHealthService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsFoodHealthServiceImpl extends AbstractBaseService<GoodsFoodHealth, Long> implements GoodsFoodHealthService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsFoodHealthRepository getGoodsFoodHealthRepository() {
		return (GoodsFoodHealthRepository) baseRepository;
	}

	@Override
	public GoodsFoodHealth findByGoodsId(Long id) {
		return getGoodsFoodHealthRepository().findByGoodsId(id);
	}
}