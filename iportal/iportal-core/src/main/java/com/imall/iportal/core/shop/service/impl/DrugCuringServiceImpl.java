package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.DrugCuringStateCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.DrugCuringRepository;
import com.imall.iportal.core.shop.service.DrugCuringService;
import com.imall.iportal.core.shop.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class DrugCuringServiceImpl extends AbstractBaseService<DrugCuring, Long> implements DrugCuringService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCuringRepository getDrugCuringRepository() {
		return (DrugCuringRepository) baseRepository;
	}

	@Override
	@Transactional
	public DrugCuring save(DrugCuringSaveVo drugCuringSaveVo) throws ParseException {
		DrugCuring drugCuring = new DrugCuring();
		CommonUtil.copyProperties(drugCuringSaveVo, drugCuring);
		drugCuring.setPlanCuringTime(drugCuringSaveVo.getPlanCuringTime());
		drugCuring.setCuringStateCode(DrugCuringStateCodeEnum.NOT_CURED.toCode());
		drugCuring.setCuringDocumentNum(DateSerialGenerator.genSerialCode(DateSerialGenerator.DRUG_CURING_PREFIX));

		super.save(drugCuring);

		for(DrugCuringItemSaveVo drugCuringItemSaveVo : drugCuringSaveVo.getItemList()){
			DrugCuringItem drugCuringItem = new DrugCuringItem();
			drugCuringItem.setDrugCuringId(drugCuring.getId());
			drugCuringItem.setGoodsBatchId(drugCuringItemSaveVo.getGoodsBatchId());
			drugCuringItem.setGoodsId(drugCuringItemSaveVo.getGoodsId());
			drugCuringItem.setShopId(drugCuringSaveVo.getShopId());
			ServiceManager.drugCuringItemService.save(drugCuringItem);
		}

		return drugCuring;
	}

	/**
	 * 创建药品养护记录
	 * @param drugCuring
	 * @param drugCuringItem
	 */
	private void createDrugCuringRecord(DrugCuring drugCuring, DrugCuringItem drugCuringItem){
		Goods goods = ServiceManager.goodsService.findOne(drugCuringItem.getGoodsId());

		DrugCuringRecordSaveVo drugCuringRecordSaveVo = new DrugCuringRecordSaveVo();
		drugCuringRecordSaveVo.setShopId(drugCuring.getShopId());
		drugCuringRecordSaveVo.setCuringPlanNum(drugCuring.getCuringDocumentNum());
		drugCuringRecordSaveVo.setCuringDate(drugCuring.getCuringFinishTime());
		drugCuringRecordSaveVo.setCuringManId(drugCuring.getCuringManId());
		drugCuringRecordSaveVo.setGoodsCode(goods.getGoodsCode());
		drugCuringRecordSaveVo.setGoodsNm(goods.getGoodsNm());
		drugCuringRecordSaveVo.setGoodsNmFirstSpell(goods.getPinyin());
		drugCuringRecordSaveVo.setCommonNm(goods.getCommonNm());
		drugCuringRecordSaveVo.setSpec(goods.getSpec());
		drugCuringRecordSaveVo.setUnit(goods.getUnit());
		drugCuringRecordSaveVo.setManufacture(goods.getProduceManufacturer());

		Long goodId = goods.getId();
		switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
			case DRUG:	//药品
				GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodId);
				SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
				drugCuringRecordSaveVo.setDosageForm(drugSysDictItem.getDictItemNm());
				drugCuringRecordSaveVo.setApprovalNumber(goodsDrug.getApprovalNumber());
				break;
			case OTHER:	//其他
				GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodId);
				drugCuringRecordSaveVo.setApprovalNumber(goodsOther.getApprovalNumber());
				break;
			case CHINESE_MEDICINE_PIECES:	//中药饮片
				GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodId);
				SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
				drugCuringRecordSaveVo.setDosageForm(cmpSysDictItem.getDictItemNm());
				drugCuringRecordSaveVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
				drugCuringRecordSaveVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
				break;
			case FOOD_HEALTH:	//食品保健品
				break;
			case DAILY_NECESSITIES:	//日用品
				GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodId);
				drugCuringRecordSaveVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
				break;
			case MEDICAL_INSTRUMENTS:	//医疗器械
				break;
			case COSMETIC:	//化妆品
				GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodId);
				drugCuringRecordSaveVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
				break;
		}

		GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugCuringItem.getGoodsBatchId());
		drugCuringRecordSaveVo.setBatch(goodsBatch.getBatch());
		drugCuringRecordSaveVo.setProduceTime(goodsBatch.getProduceDate());
		drugCuringRecordSaveVo.setValidDate(goodsBatch.getValidDate());
		drugCuringRecordSaveVo.setStockQuantity(goodsBatch.getCurrentStock());

		StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
		drugCuringRecordSaveVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());

		drugCuringRecordSaveVo.setCuringQuantity(drugCuringItem.getCuringQuantity());
		drugCuringRecordSaveVo.setCuringPrj(drugCuringItem.getCuringPrj());
		drugCuringRecordSaveVo.setDisqualificationQuantity(drugCuringItem.getNotQualifiedQuantity());
		drugCuringRecordSaveVo.setProcessSuggest(drugCuringItem.getProcessOpinion());
		drugCuringRecordSaveVo.setVerdict(drugCuringItem.getConclusion());
		drugCuringRecordSaveVo.setRemark(drugCuringItem.getRemark());

		ServiceManager.drugCuringRecordService.save(drugCuringRecordSaveVo);
	}

	@Override
	public DrugCuringDetailVo findById(Long shopId, Long id) {
		DrugCuring drugCuring = this.getDrugCuringRepository().findOne(shopId, id);
		if(drugCuring==null){
			logger.error("药品养护对象不存在，shopId:" + shopId + ", id:" + id);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品养护"}));
		}else{
			DrugCuringDetailVo drugCuringDetailVo = new DrugCuringDetailVo();
			CommonUtil.copyProperties(drugCuring, drugCuringDetailVo);
			drugCuringDetailVo.setItemList(ServiceManager.drugCuringItemService.listByDrugCuringId(shopId, drugCuring.getId()));
			return drugCuringDetailVo;
		}
	}

	@Override
	@Transactional
	public DrugCuring update(DrugCuringUpdateVo drugCuringUpdateVo) {
		DrugCuring drugCuring = this.getDrugCuringRepository().findOne(drugCuringUpdateVo.getShopId(), drugCuringUpdateVo.getId());
		if(drugCuring==null){
			logger.error("药品养护对象不存在，shopId:" + drugCuringUpdateVo.getShopId() + ", id:" + drugCuringUpdateVo.getId());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品养护"}));
		}

		drugCuring.setCuringManId(drugCuringUpdateVo.getCuringManId());
		drugCuring.setCuringStateCode(DrugCuringStateCodeEnum.CURED.toCode());
		drugCuring.setCuringFinishTime(new Date());
		this.update(drugCuring);

		for(DrugCuringItemUpdateVo drugCuringItemUpdateVo : drugCuringUpdateVo.getItemList()){
			DrugCuringItem drugCuringItem = ServiceManager.drugCuringItemService.findOne(drugCuringUpdateVo.getShopId(), drugCuringItemUpdateVo.getId());
			drugCuringItem.setCuringQuantity(drugCuringItemUpdateVo.getCuringQuantity());
			drugCuringItem.setCuringPrj(drugCuringItemUpdateVo.getCuringPrj());
			drugCuringItem.setNotQualifiedQuantity(drugCuringItemUpdateVo.getNotQualifiedQuantity());
			drugCuringItem.setProcessOpinion(drugCuringItemUpdateVo.getProcessOpinion());
			drugCuringItem.setConclusion(drugCuringItemUpdateVo.getConclusion());
			drugCuringItem.setRemark(drugCuringItemUpdateVo.getRemark());
			ServiceManager.drugCuringItemService.update(drugCuringItem);

			//保存养护记录
			createDrugCuringRecord(drugCuring, drugCuringItem);
		}

		return drugCuring;
	}

	@Override
	public Page<DrugCuringPageVo> query(Pageable pageable, DrugCuringSearchParam drugCuringSearchParam) {
		return this.getDrugCuringRepository().query(pageable, drugCuringSearchParam);
	}
}