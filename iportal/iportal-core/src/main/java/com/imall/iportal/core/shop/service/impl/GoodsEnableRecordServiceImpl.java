package com.imall.iportal.core.shop.service.impl;



import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.vo.GoodsEnableRecordPageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.GoodsEnableRecord;
import com.imall.iportal.core.shop.repository.GoodsEnableRecordRepository;
import com.imall.iportal.core.shop.service.GoodsEnableRecordService;
import com.imall.commons.base.service.impl.AbstractBaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsEnableRecordServiceImpl extends AbstractBaseService<GoodsEnableRecord, Long> implements GoodsEnableRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsEnableRecordRepository getGoodsEnableRecordRepository() {
		return (GoodsEnableRecordRepository) baseRepository;
	}

	@Override
	public Page<GoodsEnableRecordPageVo> query(Pageable pageable, Long id, Long shopId) {
		Page<GoodsEnableRecord> goodsEnableRecordPage = getGoodsEnableRecordRepository().query(pageable,id,shopId);
		List<GoodsEnableRecordPageVo> goodsEnableRecordPageVoList = new ArrayList<>();
		for(GoodsEnableRecord goodsEnableRecord:goodsEnableRecordPage.getContent()){
			goodsEnableRecordPageVoList.add(buildPage(goodsEnableRecord));
		}

		return new PageImpl<GoodsEnableRecordPageVo>(goodsEnableRecordPageVoList,pageable,goodsEnableRecordPage.getTotalElements());
	}

	private GoodsEnableRecordPageVo buildPage(GoodsEnableRecord goodsEnableRecord){
		GoodsEnableRecordPageVo goodsEnableRecordPageVo = CommonUtil.copyProperties(goodsEnableRecord,new GoodsEnableRecordPageVo());
		Goods goods = ServiceManager.goodsService.findOne(goodsEnableRecord.getGoodsId());
		goodsEnableRecordPageVo.setGoodsCode(goods.getGoodsCode());
		goodsEnableRecordPageVo.setGoodsNm(goods.getGoodsNm());
		return goodsEnableRecordPageVo;
	}
}