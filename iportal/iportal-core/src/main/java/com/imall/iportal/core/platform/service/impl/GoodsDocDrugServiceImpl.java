package com.imall.iportal.core.platform.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.platform.entity.GoodsDocDrug;
import com.imall.iportal.core.platform.repository.GoodsDocDrugRepository;
import com.imall.iportal.core.platform.service.GoodsDocDrugService;
import com.imall.commons.base.service.impl.AbstractBaseService;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDocDrugServiceImpl extends AbstractBaseService<GoodsDocDrug, Long> implements GoodsDocDrugService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsDocDrugRepository getGoodsDocDrugRepository() {
		return (GoodsDocDrugRepository) baseRepository;
	}


	@Override
	public GoodsDocDrug findByGoodsDocId(Long id) {
		return getGoodsDocDrugRepository().findByGoodsDocId(id);
	}

}