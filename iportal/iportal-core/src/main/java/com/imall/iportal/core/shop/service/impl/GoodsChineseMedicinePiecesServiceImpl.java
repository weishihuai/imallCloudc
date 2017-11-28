package com.imall.iportal.core.shop.service.impl;



import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.shop.entity.GoodsChineseMedicinePieces;
import com.imall.iportal.core.shop.repository.GoodsChineseMedicinePiecesRepository;
import com.imall.iportal.core.shop.service.GoodsChineseMedicinePiecesService;
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
public class GoodsChineseMedicinePiecesServiceImpl extends AbstractBaseService<GoodsChineseMedicinePieces, Long> implements GoodsChineseMedicinePiecesService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsChineseMedicinePiecesRepository getGoodsChineseMedicinePiecesRepository() {
		return (GoodsChineseMedicinePiecesRepository) baseRepository;
	}

	@Override
	public GoodsChineseMedicinePieces findByGoodsId(Long goodsId) {
		return getGoodsChineseMedicinePiecesRepository().findByGoodsId(goodsId);
	}
}