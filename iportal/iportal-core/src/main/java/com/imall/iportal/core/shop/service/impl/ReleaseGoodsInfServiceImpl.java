package com.imall.iportal.core.shop.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.ReleaseGoodsInf;
import com.imall.iportal.core.shop.repository.ReleaseGoodsInfRepository;
import com.imall.iportal.core.shop.service.ReleaseGoodsInfService;
import com.imall.commons.base.service.impl.AbstractBaseService;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ReleaseGoodsInfServiceImpl extends AbstractBaseService<ReleaseGoodsInf, Long> implements ReleaseGoodsInfService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ReleaseGoodsInfRepository getReleaseGoodsInfRepository() {
		return (ReleaseGoodsInfRepository) baseRepository;
	}

	@Override
	public List<ReleaseGoodsInf> findByDrugReleaseNoticeId(Long id) {
		return getReleaseGoodsInfRepository().findByDrugReleaseNoticeId(id);
	}
}