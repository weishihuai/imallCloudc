package com.imall.iportal.core.shop.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.GoodsMedicalInstruments;
import com.imall.iportal.core.shop.repository.GoodsMedicalInstrumentsRepository;
import com.imall.iportal.core.shop.service.GoodsMedicalInstrumentsService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsMedicalInstrumentsServiceImpl extends AbstractBaseService<GoodsMedicalInstruments, Long> implements GoodsMedicalInstrumentsService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsMedicalInstrumentsRepository getGoodsMedicalInstrumentsRepository() {
		return (GoodsMedicalInstrumentsRepository) baseRepository;
	}

	@Override
	public GoodsMedicalInstruments findByGoodsId(Long id) {
		return getGoodsMedicalInstrumentsRepository().findByGoodsId(id);
	}
}