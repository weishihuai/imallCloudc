package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.GoodsSplitZeroRepository;
import com.imall.iportal.core.shop.service.GoodsSplitZeroService;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroDetailVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroPageVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSaveVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsSplitZeroServiceImpl extends AbstractBaseService<GoodsSplitZero, Long> implements GoodsSplitZeroService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private GoodsSplitZeroRepository getGoodsSplitZeroRepository() {
		return (GoodsSplitZeroRepository) baseRepository;
	}

	@Override
	public Page<GoodsSplitZeroPageVo> queryGoodsSplitZero(Pageable pageable, GoodsSplitZeroSearchParam goodsSplitZeroSearchParam) {
		Page<GoodsSplitZeroPageVo> page = this.getGoodsSplitZeroRepository().query(pageable, goodsSplitZeroSearchParam);
		for(GoodsSplitZeroPageVo goodsSplitZeroPageVo : page.getContent()){
			Sku sku = ServiceManager.skuService.findOne(goodsSplitZeroPageVo.getSkuId());
			goodsSplitZeroPageVo.setSplitZeroUnit(sku.getSplitZeroUnit());
			goodsSplitZeroPageVo.setSplitZeroSpec(sku.getSplitZeroSpec());

			Goods goods = ServiceManager.goodsService.findOne(goodsSplitZeroPageVo.getGoodsId());
			goodsSplitZeroPageVo.setProduceManufacturer(goods.getProduceManufacturer());
			goodsSplitZeroPageVo.setSpec(goods.getSpec());
			goodsSplitZeroPageVo.setUnit(goods.getUnit());
			Long goodId = goods.getId();
			switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
				case DRUG:	//药品
					GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodId);
					SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
					goodsSplitZeroPageVo.setDosageForm(drugSysDictItem.getDictItemNm());
					break;
				case OTHER:	//其他
					break;
				case CHINESE_MEDICINE_PIECES:	//中药饮片
					GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodId);
					SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
					goodsSplitZeroPageVo.setDosageForm(cmpSysDictItem.getDictItemNm());
					break;
				case FOOD_HEALTH:	//食品保健品
					break;
				case DAILY_NECESSITIES:	//日用品
					break;
				case MEDICAL_INSTRUMENTS:	//医疗器械
					break;
				case COSMETIC:	//化妆品
					break;
			}

			GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(goodsSplitZeroPageVo.getGoodsBatchId());
			goodsSplitZeroPageVo.setBatch(goodsBatch.getBatch());
			goodsSplitZeroPageVo.setValidDate(goodsBatch.getValidDateString());

			StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
			goodsSplitZeroPageVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
		}

		return page;
	}

	@Override
	public GoodsSplitZeroDetailVo findById(Long shopId, Long id) {
		GoodsSplitZero goodsSplitZero = this.getGoodsSplitZeroRepository().findOne(shopId, id);
		GoodsSplitZeroDetailVo goodsSplitZeroDetailVo = CommonUtil.copyProperties(goodsSplitZero, new GoodsSplitZeroDetailVo());
		Goods goods = ServiceManager.goodsService.findOne(goodsSplitZero.getGoodsId());
		GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(goodsSplitZero.getGoodsBatchId());
		StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
		Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());

		goodsSplitZeroDetailVo.setGoodsCode(goods.getGoodsCode());
		goodsSplitZeroDetailVo.setGoodsNm(goods.getGoodsNm());
		goodsSplitZeroDetailVo.setCommonNm(goods.getCommonNm());
		goodsSplitZeroDetailVo.setSpec(goods.getSpec());
		goodsSplitZeroDetailVo.setUnit(goods.getUnit());

		Long goodId = goods.getId();
		switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
			case DRUG:	//药品
				GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodId);
				SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
				goodsSplitZeroDetailVo.setDosageForm(drugSysDictItem.getDictItemNm());
				break;
			case OTHER:	//其他
				break;
			case CHINESE_MEDICINE_PIECES:	//中药饮片
				GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodId);
				SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
				goodsSplitZeroDetailVo.setDosageForm(cmpSysDictItem.getDictItemNm());
				break;
			case FOOD_HEALTH:	//食品保健品
				break;
			case DAILY_NECESSITIES:	//日用品
				break;
			case MEDICAL_INSTRUMENTS:	//医疗器械
				break;
			case COSMETIC:	//化妆品
				break;
		}

		goodsSplitZeroDetailVo.setProduceManufacturer(goods.getProduceManufacturer());
		goodsSplitZeroDetailVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
		goodsSplitZeroDetailVo.setBatch(goodsBatch.getBatch());
		goodsSplitZeroDetailVo.setValidDate(goodsBatch.getValidDateString());
		goodsSplitZeroDetailVo.setProduceDate(DateTimeUtils.convertDateToString(goodsBatch.getProduceDate()));
		goodsSplitZeroDetailVo.setInStockDate(DateTimeUtils.convertDateToString(goodsBatch.getCreateDate()));

		goodsSplitZeroDetailVo.setSplitZeroUnit(sku.getSplitZeroUnit());
		goodsSplitZeroDetailVo.setSplitZeroSpec(sku.getSplitZeroSpec());
		goodsSplitZeroDetailVo.setSplitZeroRetailPrice(sku.getSplitZeroRetailPrice());
		goodsSplitZeroDetailVo.setSplitZeroMemberPrice(sku.getSplitZeroMemberPrice());

		return goodsSplitZeroDetailVo;
	}

	@Override
	@Transactional
	public Long save(GoodsSplitZeroSaveVo goodsSplitZeroSaveVo) {
		//扣减库存
		ServiceManager.goodsBatchService.updateSubtractCurrentStock(goodsSplitZeroSaveVo.getGoodsBatchId(), goodsSplitZeroSaveVo.getSplitZeroQuantity());

		//生成拆零商品信息
		Goods splitGoods = ServiceManager.goodsService.saveSpiltGoods(goodsSplitZeroSaveVo.getGoodsId(), goodsSplitZeroSaveVo.getShopId());

		//生成批次信息
		GoodsBatch sourceGoodsBatch = ServiceManager.goodsBatchService.findOne(goodsSplitZeroSaveVo.getGoodsBatchId());
		Sku sourceSku = ServiceManager.skuService.findOne(sourceGoodsBatch.getSkuId());
		GoodsBatch splitBatch = new GoodsBatch();
		CommonUtil.copyProperties(sourceGoodsBatch, splitBatch);
		splitBatch.setId(null);
		splitBatch.setGoodsId(splitGoods.getId());
		splitBatch.setSkuId(ServiceManager.skuService.findByGoodsId(splitGoods.getId()).getId());
		splitBatch.setCurrentStock(goodsSplitZeroSaveVo.getSplitSmallPackageQuantity());
		splitBatch.setGoodsNm(splitGoods.getGoodsNm());
		splitBatch.setGoodsCommonNm(splitGoods.getCommonNm());
		splitBatch.setPurchasePrice(BigDecimalUtil.divide(sourceGoodsBatch.getPurchasePrice(), sourceSku.getSplitZeroQuantity()));
		ServiceManager.goodsBatchService.save(splitBatch);

		//保存拆零信息
		GoodsSplitZero goodsSplitZero = CommonUtil.copyProperties(goodsSplitZeroSaveVo, new GoodsSplitZero());
		return save(goodsSplitZero).getId();
	}

	@Override
	public List<GoodsSplitZero> findByGoodsId(Long goodsId) {
		return getGoodsSplitZeroRepository().findByGoodsId(goodsId);
	}
}