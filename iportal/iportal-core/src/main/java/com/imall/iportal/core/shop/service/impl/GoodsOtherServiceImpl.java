package com.imall.iportal.core.shop.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.GoodsOther;
import com.imall.iportal.core.shop.repository.GoodsOtherRepository;
import com.imall.iportal.core.shop.service.GoodsOtherService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsOtherServiceImpl extends AbstractBaseService<GoodsOther, Long> implements GoodsOtherService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsOtherRepository getGoodsOtherRepository() {
		return (GoodsOtherRepository) baseRepository;
	}

	@Override
	public GoodsOther findByGoodsId(Long id) {
		return getGoodsOtherRepository().findByGoodsId(id);
	}
}