package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.DrugCheckStateCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.DrugCheckRepository;
import com.imall.iportal.core.shop.service.DrugCheckService;
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
public class DrugCheckServiceImpl extends AbstractBaseService<DrugCheck, Long> implements DrugCheckService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCheckRepository getDrugCheckRepository() {
		return (DrugCheckRepository) baseRepository;
	}

	@Override
	@Transactional
	public DrugCheck save(DrugCheckSaveVo drugCheckVo) throws ParseException {
		DrugCheck drugCheck = new DrugCheck();
		CommonUtil.copyProperties(drugCheckVo, drugCheck);
		drugCheck.setPlanCheckTime(drugCheckVo.getPlanCheckTime());
		drugCheck.setCheckStateCode(DrugCheckStateCodeEnum.NOT_CHECKED.toCode());
		drugCheck.setCheckDocumentNum(DateSerialGenerator.genSerialCode(DateSerialGenerator.DRUG_CHECK_PREFIX));
		super.save(drugCheck);

		for(DrugCheckItemSaveVo drugCheckItemSaveVo : drugCheckVo.getDrugCheckItemSaveVoList()){
			DrugCheckItem drugCheckItem = new DrugCheckItem();
			drugCheckItem.setDrugCheckId(drugCheck.getId());
			drugCheckItem.setShopId(drugCheckVo.getShopId());
			drugCheckItem.setGoodsId(drugCheckItemSaveVo.getGoodsId());
			drugCheckItem.setGoodsBatchId(drugCheckItemSaveVo.getGoodsBatchId());
			ServiceManager.drugCheckItemService.save(drugCheckItem);
		}

		return drugCheck;
	}

	@Override
	public DrugCheckDetailVo findById(Long shopId, Long id) {
		DrugCheck drugCheck = this.getDrugCheckRepository().findOne(shopId, id);
		if(drugCheck==null){
			logger.error("药品检查对象不存在，shopId:" + shopId + ", id:" + id);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品检查"}));
		}else{
			DrugCheckDetailVo drugCheckDetailVo = new DrugCheckDetailVo();
			CommonUtil.copyProperties(drugCheck, drugCheckDetailVo);
			drugCheckDetailVo.setDrugCheckItemDetailVoList(ServiceManager.drugCheckItemService.listByDrugCheckId(shopId, drugCheck.getId()));
			return drugCheckDetailVo;
		}
	}

	@Override
	@Transactional
	public DrugCheck update(DrugCheckUpdateVo drugCheckUpdateVo) {
		DrugCheck drugCheck = this.getDrugCheckRepository().findOne(drugCheckUpdateVo.getShopId(), drugCheckUpdateVo.getId());
		if(drugCheck==null){
			logger.error("药品检查对象不存在，shopId:" + drugCheckUpdateVo.getShopId() + ", id:" + drugCheckUpdateVo.getId());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品检查"}));
		}

		drugCheck.setApproveManId(drugCheckUpdateVo.getApproveManId());
		drugCheck.setCheckStateCode(DrugCheckStateCodeEnum.CHECKED.toCode());
		drugCheck.setCheckFinishTime(new Date());
		this.update(drugCheck);

		for(DrugCheckItemUpdateVo drugCheckItemUpdateVo : drugCheckUpdateVo.getDrugCheckItemUpdateVoList()){
			DrugCheckItem drugCheckItem = ServiceManager.drugCheckItemService.findOne(drugCheckUpdateVo.getShopId(), drugCheckItemUpdateVo.getId());
			drugCheckItem.setCheckQuantity(drugCheckItemUpdateVo.getCheckQuantity());
			drugCheckItem.setCheckPrj(drugCheckItemUpdateVo.getCheckPrj());
			drugCheckItem.setNotQualifiedQuantity(drugCheckItemUpdateVo.getNotQualifiedQuantity());
			drugCheckItem.setProcessOpinion(drugCheckItemUpdateVo.getProcessOpinion());
			drugCheckItem.setConclusion(drugCheckItemUpdateVo.getConclusion());
			drugCheckItem.setRemark(drugCheckItemUpdateVo.getRemark());
			ServiceManager.drugCheckItemService.update(drugCheckItem);

			//保存检查记录
			createDrugCheckRecord(drugCheck, drugCheckItem);
		}

		return drugCheck;
	}

	/**
	 * 创建药品检查记录
	 */
	private void createDrugCheckRecord(DrugCheck drugCheck, DrugCheckItem drugCheckItem){
		Goods goods = ServiceManager.goodsService.findOne(drugCheckItem.getGoodsId());

		DrugCheckRecordSaveVo drugCheckRecordSaveVo = new DrugCheckRecordSaveVo();
		drugCheckRecordSaveVo.setShopId(drugCheck.getShopId());
		drugCheckRecordSaveVo.setCheckNum(drugCheck.getCheckDocumentNum());
		drugCheckRecordSaveVo.setCheckTime(drugCheck.getCheckFinishTime());
		drugCheckRecordSaveVo.setApproveManId(drugCheck.getApproveManId());
		drugCheckRecordSaveVo.setGoodsCode(goods.getGoodsCode());
		drugCheckRecordSaveVo.setGoodsNm(goods.getGoodsNm());
		drugCheckRecordSaveVo.setGoodsNmFirstSpell(goods.getPinyin());
		drugCheckRecordSaveVo.setCommonNm(goods.getCommonNm());
		drugCheckRecordSaveVo.setSpec(goods.getSpec());
		drugCheckRecordSaveVo.setUnit(goods.getUnit());
		drugCheckRecordSaveVo.setManufacture(goods.getProduceManufacturer());

		Long goodId = goods.getId();
		switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
			case DRUG:	//药品
				GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodId);
				SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
				drugCheckRecordSaveVo.setDosageForm(drugSysDictItem.getDictItemNm());
				drugCheckRecordSaveVo.setApprovalNumber(goodsDrug.getApprovalNumber());
				break;
			case OTHER:	//其他
				GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodId);
				drugCheckRecordSaveVo.setApprovalNumber(goodsOther.getApprovalNumber());
				break;
			case CHINESE_MEDICINE_PIECES:	//中药饮片
				GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodId);
				SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
				drugCheckRecordSaveVo.setDosageForm(cmpSysDictItem.getDictItemNm());
				drugCheckRecordSaveVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
				drugCheckRecordSaveVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
				break;
			case FOOD_HEALTH:	//食品保健品
				break;
			case DAILY_NECESSITIES:	//日用品
				GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodId);
				drugCheckRecordSaveVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
				break;
			case MEDICAL_INSTRUMENTS:	//医疗器械
				break;
			case COSMETIC:	//化妆品
				GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodId);
				drugCheckRecordSaveVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
				break;
		}

		GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugCheckItem.getGoodsBatchId());
		drugCheckRecordSaveVo.setBatch(goodsBatch.getBatch());
		drugCheckRecordSaveVo.setProduceTime(goodsBatch.getProduceDate());
		drugCheckRecordSaveVo.setValidDate(goodsBatch.getValidDate());
		drugCheckRecordSaveVo.setStockQuantity(goodsBatch.getCurrentStock());

		StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
		drugCheckRecordSaveVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());

		drugCheckRecordSaveVo.setCheckQuantity(drugCheckItem.getCheckQuantity());
		drugCheckRecordSaveVo.setCheckPrj(drugCheckItem.getCheckPrj());
		drugCheckRecordSaveVo.setDisqualificationQuantity(drugCheckItem.getNotQualifiedQuantity());
		drugCheckRecordSaveVo.setProcessSuggest(drugCheckItem.getProcessOpinion());
		drugCheckRecordSaveVo.setVerdict(drugCheckItem.getConclusion());
		drugCheckRecordSaveVo.setRemark(drugCheckItem.getRemark());

		ServiceManager.drugCheckRecordService.save(drugCheckRecordSaveVo);
	}

	@Override
	public Page<DrugCheckPageVo> query(Pageable pageable, DrugCheckSearchParam drugCheckSearchParam) {
		return this.getDrugCheckRepository().query(pageable, drugCheckSearchParam);
	}
}