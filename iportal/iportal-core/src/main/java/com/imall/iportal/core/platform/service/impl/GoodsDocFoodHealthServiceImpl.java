package com.imall.iportal.core.platform.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.platform.entity.GoodsDocFoodHealth;
import com.imall.iportal.core.platform.repository.GoodsDocFoodHealthRepository;
import com.imall.iportal.core.platform.service.GoodsDocFoodHealthService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDocFoodHealthServiceImpl extends AbstractBaseService<GoodsDocFoodHealth, Long> implements GoodsDocFoodHealthService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsDocFoodHealthRepository getGoodsDocFoodHealthRepository() {
		return (GoodsDocFoodHealthRepository) baseRepository;
	}


	@Override
	public GoodsDocFoodHealth findByGoodsDocId(Long id) {
		return getGoodsDocFoodHealthRepository().findByGoodsDocId(id);
	}
}