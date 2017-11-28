package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.DrugCheckItemRepository;
import com.imall.iportal.core.shop.service.DrugCheckItemService;
import com.imall.iportal.core.shop.vo.DrugCheckItemDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
public class DrugCheckItemServiceImpl extends AbstractBaseService<DrugCheckItem, Long> implements DrugCheckItemService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCheckItemRepository getDrugCheckItemRepository() {
		return (DrugCheckItemRepository) baseRepository;
	}

	@Override
	public List<DrugCheckItemDetailVo> listByDrugCheckId(Long shopId, Long drugCheckId) {
		List<DrugCheckItem> drugCheckItemList = this.getDrugCheckItemRepository().listByDrugCheckId(shopId, drugCheckId);
		List<DrugCheckItemDetailVo> drugCheckItemDetailVoList = new ArrayList<>();
		for(DrugCheckItem drugCheckItem : drugCheckItemList){
			DrugCheckItemDetailVo drugCheckItemDetailVo = new DrugCheckItemDetailVo();
			BeanUtils.copyProperties(drugCheckItem, drugCheckItemDetailVo);
			drugCheckItemDetailVo.setId(drugCheckItem.getId());

			Goods goods = ServiceManager.goodsService.findOne(drugCheckItem.getGoodsId());
			drugCheckItemDetailVo.setGoodsCode(goods.getGoodsCode());
			drugCheckItemDetailVo.setGoodsNm(goods.getGoodsNm());
			drugCheckItemDetailVo.setCommonNm(goods.getCommonNm());
			drugCheckItemDetailVo.setSpec(goods.getSpec());
			drugCheckItemDetailVo.setUnit(goods.getUnit());
			drugCheckItemDetailVo.setProduceManufacturer(goods.getProduceManufacturer());

			Long goodId = goods.getId();
			switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
				case DRUG:	//药品
					GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodId);
					SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
					drugCheckItemDetailVo.setDosageForm(drugSysDictItem.getDictItemNm());
					drugCheckItemDetailVo.setApprovalNumber(goodsDrug.getApprovalNumber());
					break;
				case OTHER:	//其他
					GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodId);
					drugCheckItemDetailVo.setApprovalNumber(goodsOther.getApprovalNumber());
					break;
				case CHINESE_MEDICINE_PIECES:	//中药饮片
					GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodId);
					SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
					drugCheckItemDetailVo.setDosageForm(cmpSysDictItem.getDictItemNm());
					drugCheckItemDetailVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
					drugCheckItemDetailVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
					break;
				case FOOD_HEALTH:	//食品保健品
					break;
				case DAILY_NECESSITIES:	//日用品
					GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodId);
					drugCheckItemDetailVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
					break;
				case MEDICAL_INSTRUMENTS:	//医疗器械
					break;
				case COSMETIC:	//化妆品
					GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodId);
					drugCheckItemDetailVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
					break;
			}

			GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugCheckItem.getGoodsBatchId());
			drugCheckItemDetailVo.setPurchasePrice(goodsBatch.getPurchasePrice());
			drugCheckItemDetailVo.setBatch(goodsBatch.getBatch());
			drugCheckItemDetailVo.setProduceDate(goodsBatch.getProduceDate());
			drugCheckItemDetailVo.setValidDate(goodsBatch.getValidDate());
			drugCheckItemDetailVo.setCurrentStock(goodsBatch.getCurrentStock());

			StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
			drugCheckItemDetailVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());

			drugCheckItemDetailVoList.add(drugCheckItemDetailVo);
		}

		return drugCheckItemDetailVoList;
	}

	@Override
	public DrugCheckItem findOne(Long shopId, Long id) {
		return this.getDrugCheckItemRepository().findOne(shopId, id);
	}
}