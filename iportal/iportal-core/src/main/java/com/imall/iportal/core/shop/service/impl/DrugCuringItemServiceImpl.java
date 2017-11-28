package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.DrugCuringItemRepository;
import com.imall.iportal.core.shop.service.DrugCuringItemService;
import com.imall.iportal.core.shop.vo.DrugCuringItemDetailVo;
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
public class DrugCuringItemServiceImpl extends AbstractBaseService<DrugCuringItem, Long> implements DrugCuringItemService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCuringItemRepository getDrugCuringItemRepository() {
		return (DrugCuringItemRepository) baseRepository;
	}

	@Override
	public List<DrugCuringItemDetailVo> listByDrugCuringId(Long shopId, Long drugCuringId) {
		List<DrugCuringItem> drugCuringItemList = this.getDrugCuringItemRepository().listByDrugCuringId(shopId, drugCuringId);
		List<DrugCuringItemDetailVo> drugCuringItemDetailVoList = new ArrayList<>();
		for(DrugCuringItem drugCuringItem : drugCuringItemList){
			DrugCuringItemDetailVo drugCuringItemDetailVo = new DrugCuringItemDetailVo();
			BeanUtils.copyProperties(drugCuringItem, drugCuringItemDetailVo);
			drugCuringItemDetailVo.setId(drugCuringItem.getId());

			Goods goods = ServiceManager.goodsService.findOne(drugCuringItem.getGoodsId());
			drugCuringItemDetailVo.setGoodsCode(goods.getGoodsCode());
			drugCuringItemDetailVo.setGoodsNm(goods.getGoodsNm());
			drugCuringItemDetailVo.setCommonNm(goods.getCommonNm());
			drugCuringItemDetailVo.setSpec(goods.getSpec());
			drugCuringItemDetailVo.setUnit(goods.getUnit());
			drugCuringItemDetailVo.setProduceManufacturer(goods.getProduceManufacturer());

			Long goodId = goods.getId();
			switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
				case DRUG:	//药品
					GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodId);
					SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
					drugCuringItemDetailVo.setDosageForm(drugSysDictItem.getDictItemNm());
					drugCuringItemDetailVo.setApprovalNumber(goodsDrug.getApprovalNumber());
					break;
				case OTHER:	//其他
					GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodId);
					drugCuringItemDetailVo.setApprovalNumber(goodsOther.getApprovalNumber());
					break;
				case CHINESE_MEDICINE_PIECES:	//中药饮片
					GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodId);
					SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
					drugCuringItemDetailVo.setDosageForm(cmpSysDictItem.getDictItemNm());
					drugCuringItemDetailVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
					drugCuringItemDetailVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
					break;
				case FOOD_HEALTH:	//食品保健品
					break;
				case DAILY_NECESSITIES:	//日用品
					GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodId);
					drugCuringItemDetailVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
					break;
				case MEDICAL_INSTRUMENTS:	//医疗器械
					break;
				case COSMETIC:	//化妆品
					GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodId);
					drugCuringItemDetailVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
					break;
			}

			GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugCuringItem.getGoodsBatchId());
			drugCuringItemDetailVo.setBatch(goodsBatch.getBatch());
			drugCuringItemDetailVo.setPurchasePrice(goodsBatch.getPurchasePrice());
			drugCuringItemDetailVo.setProduceDate(goodsBatch.getProduceDate());
			drugCuringItemDetailVo.setValidDate(goodsBatch.getValidDate());
			drugCuringItemDetailVo.setCurrentStock(goodsBatch.getCurrentStock());

			StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
			drugCuringItemDetailVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());

			drugCuringItemDetailVoList.add(drugCuringItemDetailVo);
		}
		
		return drugCuringItemDetailVoList;
	}

	@Override
	public DrugCuringItem findOne(Long shopId, Long id) {
		return this.getDrugCuringItemRepository().findOne(shopId, id);
	}
}