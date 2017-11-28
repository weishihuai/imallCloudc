package com.imall.iportal.core.shop.service.impl;



import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.shop.entity.GoodsCosmetic;
import com.imall.iportal.core.shop.repository.GoodsCosmeticRepository;
import com.imall.iportal.core.shop.service.GoodsCosmeticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsCosmeticServiceImpl extends AbstractBaseService<GoodsCosmetic, Long> implements GoodsCosmeticService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsCosmeticRepository getGoodsCosmeticRepository() {
		return (GoodsCosmeticRepository) baseRepository;
	}

	@Override
	public GoodsCosmetic findByGoodsId(Long id) {
		return getGoodsCosmeticRepository().findByGoodsId(id);
	}
}