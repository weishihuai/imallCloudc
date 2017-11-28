package com.imall.iportal.core.platform.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.platform.entity.GoodsDocMedicalInstruments;
import com.imall.iportal.core.platform.repository.GoodsDocMedicalInstrumentsRepository;
import com.imall.iportal.core.platform.service.GoodsDocMedicalInstrumentsService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDocMedicalInstrumentsServiceImpl extends AbstractBaseService<GoodsDocMedicalInstruments, Long> implements GoodsDocMedicalInstrumentsService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsDocMedicalInstrumentsRepository getGoodsDocMedicalInstrumentsRepository() {
		return (GoodsDocMedicalInstrumentsRepository) baseRepository;
	}

	@Override
	public GoodsDocMedicalInstruments findByGoodsDocId(Long id) {
		return getGoodsDocMedicalInstrumentsRepository().findByGoodsDocId(id);
	}
}