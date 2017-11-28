package com.imall.iportal.core.shop.service.impl;

import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.ConsultGoodsInfRepository;
import com.imall.iportal.core.shop.service.ConsultGoodsInfService;
import com.imall.iportal.core.shop.vo.ConsultGoodsInfDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ConsultGoodsInfServiceImpl extends AbstractBaseService<ConsultGoodsInf, Long> implements ConsultGoodsInfService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ConsultGoodsInfRepository getConsultGoodsInfRepository() {
		return (ConsultGoodsInfRepository) baseRepository;
	}

	@Override
	public List<ConsultGoodsInfDetailVo> findDetailByConsultServiceId(Long consultServiceId) {
		List<ConsultGoodsInf> goodsInfList = getConsultGoodsInfRepository().findByConsultServiceId(consultServiceId);
		List<ConsultGoodsInfDetailVo> detailVoList = new ArrayList<>();
		for(ConsultGoodsInf goodsInf : goodsInfList) {
			detailVoList.add(build(goodsInf));
		}
		return detailVoList;
	}

	private ConsultGoodsInfDetailVo build(ConsultGoodsInf goodsInf) {
		ConsultGoodsInfDetailVo detailVo = CommonUtil.copyProperties(goodsInf, new ConsultGoodsInfDetailVo());
		Goods goods = ServiceManager.goodsService.findOne(goodsInf.getGoodsId());
		Sku sku = ServiceManager.skuService.findByGoodsId(goodsInf.getGoodsId());
		detailVo.setGoodsCode(goods.getGoodsCode());
		detailVo.setGoodsNm(goods.getGoodsNm());
		detailVo.setCommonNm(goods.getCommonNm());
		detailVo.setSpec(goods.getSpec());
		detailVo.setUnit(goods.getUnit());
		detailVo.setProduceManufacturer(goods.getProduceManufacturer());
		detailVo.setRetailPrice(sku.getRetailPrice());
		detailVo.setCurrentStock(sku.getCurrentStock());
		detailVo.setInstructionsStr(goods.getInstructionsStr());
		detailVo.setMedicationGuideStr(goods.getMedicationGuideStr());

		if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
			GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
			if (goodsDrug != null) {
				SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
				detailVo.setDosageForm(sysDictItem.getDictItemNm());
				detailVo.setApprovalNumber(goodsDrug.getApprovalNumber());
			}
		} else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
			GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
			if (goodsChineseMedicinePieces != null) {
				SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
				detailVo.setDosageForm(sysDictItem.getDictItemNm());
				detailVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
				detailVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
			}
		}

		return detailVo;
	}
}