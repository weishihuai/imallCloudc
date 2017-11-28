package com.imall.iportal.core.platform.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.platform.entity.GoodsDocCosmetic;
import com.imall.iportal.core.platform.repository.GoodsDocCosmeticRepository;
import com.imall.iportal.core.platform.service.GoodsDocCosmeticService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDocCosmeticServiceImpl extends AbstractBaseService<GoodsDocCosmetic, Long> implements GoodsDocCosmeticService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsDocCosmeticRepository getGoodsDocCosmeticRepository() {
		return (GoodsDocCosmeticRepository) baseRepository;
	}

	@Override
	public GoodsDocCosmetic findByGoodsDocId(Long id) {
		return getGoodsDocCosmeticRepository().findByGoodsDocId(id);
	}
}