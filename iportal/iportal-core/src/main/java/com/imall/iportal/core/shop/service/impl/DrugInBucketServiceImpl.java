package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.OutInStockLogSourceTypeCodeEnum;
import com.imall.commons.dicts.OutInStockTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.DrugInBucketRepository;
import com.imall.iportal.core.shop.service.DrugInBucketService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class DrugInBucketServiceImpl extends AbstractBaseService<DrugInBucket, Long> implements DrugInBucketService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DrugInBucketRepository getDrugInBucketRepository() {
        return (DrugInBucketRepository) baseRepository;
    }

    @Override
    public Page<DrugInBucketVo> query(Pageable pageable, DrugInBucketSearchParam drugInBucketSearchParam) {
        Page<DrugInBucket> drugInBucketPage = getDrugInBucketRepository().query(pageable, drugInBucketSearchParam);
        List<DrugInBucketVo> drugInBucketVoList = new ArrayList<>();
        for (DrugInBucket drugInBucket : drugInBucketPage.getContent()) {
            drugInBucketVoList.add(buildDrugInBucketVo(drugInBucket));
        }
        return new PageImpl<>(drugInBucketVoList, new PageRequest(drugInBucketPage.getNumber(),drugInBucketPage.getSize()), drugInBucketPage.getTotalElements());
    }

    @Override
    public DrugInBucketVo findById(Long shopId, Long id) {
        DrugInBucket drugInBucket = this.findOne(id);
        if (drugInBucket == null || !drugInBucket.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        return buildDrugInBucketVo(drugInBucket);
    }

    @Override
    @Transactional
    public void saveDrugInBucket(DrugInBucketSaveVo drugInBucketSaveVo) throws ParseException {
        if (CollectionUtils.isEmpty(drugInBucketSaveVo.getDrugInBucketGoodsSaveVoList())) {
            return;
        }
        for (DrugInBucketGoodsSaveVo drugClearBucketGoodsSaveVo : drugInBucketSaveVo.getDrugInBucketGoodsSaveVoList()) {
            DrugInBucket drugInBucket = CommonUtil.copyProperties(drugClearBucketGoodsSaveVo, new DrugInBucket());
            drugInBucket.setIsQualityQualified(BoolCodeEnum.fromCode(drugClearBucketGoodsSaveVo.getIsQualityQualified()).toCode());
            drugInBucket.setInBucketManName(drugInBucketSaveVo.getInBucketManName());
            drugInBucket.setShopId(drugInBucketSaveVo.getShopId());
            drugInBucket.setInBucketTime(StringUtils.isNotBlank(drugInBucketSaveVo.getInBucketTimeString()) ? DateTimeUtils.convertStringToDate(drugInBucketSaveVo.getInBucketTimeString()) : null);
            drugInBucket.setApproveManId(drugInBucketSaveVo.getApproveManId());

            save(drugInBucket);
            //保存装斗出入库日志
            saveOutInStockLog(drugInBucket);
            //生成装斗记录
            ServiceManager.chineseMedicinePiecesLoadingBucketRecordService.saveChineseMedicinePiecesLoadingBucketRecord(buildChineseMedicinePiecesLoadingBucketRecord(drugInBucket));

            //检查是否 存在 批次信息
            GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo = new GoodsBatchSimpleSearchVo();
            goodsBatchSimpleSearchVo.setBatch(drugClearBucketGoodsSaveVo.getBatch());
            goodsBatchSimpleSearchVo.setGoodsId(drugClearBucketGoodsSaveVo.getGoodsId());
            goodsBatchSimpleSearchVo.setStorageSpaceId(drugClearBucketGoodsSaveVo.getStorageSpaceId());
            goodsBatchSimpleSearchVo.setPurchasePrice(drugClearBucketGoodsSaveVo.getPurchasePrice());
            goodsBatchSimpleSearchVo.setSupplierId(drugClearBucketGoodsSaveVo.getSupplierId());
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.fingByGoodsBatch(goodsBatchSimpleSearchVo);
            if(goodsBatch==null){
                //没有批次信息 则添加批次信息 扣减 原始批号库存
                ServiceManager.goodsBatchService.updateSubtractCurrentStock(drugClearBucketGoodsSaveVo.getBatchId(), drugClearBucketGoodsSaveVo.getQuantity());
                saveGoodsBatch(drugInBucketSaveVo, drugClearBucketGoodsSaveVo, goodsBatchSimpleSearchVo);

            }else {
                //有批次信息 则修改库存  扣减 原始批号库存
                goodsBatch.setCurrentStock(goodsBatch.getCurrentStock()+drugClearBucketGoodsSaveVo.getQuantity());
                ServiceManager.goodsBatchService.update(goodsBatch);
                ServiceManager.goodsBatchService.updateSubtractCurrentStock(drugClearBucketGoodsSaveVo.getBatchId(), drugClearBucketGoodsSaveVo.getQuantity());

            }

        }
    }

    private void saveGoodsBatch(DrugInBucketSaveVo drugInBucketSaveVo, DrugInBucketGoodsSaveVo drugClearBucketGoodsSaveVo, GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo) throws ParseException {
        Goods goods = ServiceManager.goodsService.findOne(goodsBatchSimpleSearchVo.getGoodsId());
        GoodsBatch batch = new GoodsBatch();
        batch.setShopId(drugInBucketSaveVo.getShopId());
        batch.setGoodsId(drugClearBucketGoodsSaveVo.getGoodsId());
        batch.setSkuId(drugClearBucketGoodsSaveVo.getSkuId());
        batch.setBatch(drugClearBucketGoodsSaveVo.getBatch());
        batch.setProduceDate(StringUtils.isNotBlank(drugClearBucketGoodsSaveVo.getProduceDateString())? DateTimeUtils.convertStringToDate(drugClearBucketGoodsSaveVo.getProduceDateString()):null);
        batch.setValidDate(StringUtils.isNotBlank(drugClearBucketGoodsSaveVo.getValidDateString())?DateTimeUtils.convertStringToDate(drugClearBucketGoodsSaveVo.getValidDateString()):null);
        batch.setCurrentStock(drugClearBucketGoodsSaveVo.getQuantity());
        batch.setStorageSpaceId(drugClearBucketGoodsSaveVo.getStorageSpaceId());
        batch.setSupplierId(drugClearBucketGoodsSaveVo.getSupplierId());
        batch.setPinyin(goods.getPinyin());
        batch.setGoodsCode(goods.getGoodsCode());
        batch.setGoodsNm(goods.getGoodsNm());
        batch.setGoodsCommonNm(goods.getCommonNm());
        batch.setBatchState(drugClearBucketGoodsSaveVo.getBatchState());
        batch.setPurchasePrice(drugClearBucketGoodsSaveVo.getPurchasePrice());
        ServiceManager.goodsBatchService.save(batch);
    }

    private ChineseMedicinePiecesLoadingBucketRecord buildChineseMedicinePiecesLoadingBucketRecord(DrugInBucket drugInBucket) throws ParseException {
        ChineseMedicinePiecesLoadingBucketRecord chineseMedicinePiecesLoadingBucketRecord = new ChineseMedicinePiecesLoadingBucketRecord();
        Goods goods = ServiceManager.goodsService.findOne(drugInBucket.getGoodsId());
        GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(drugInBucket.getGoodsId());
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugInBucket.getBatchId());
        StorageSpace originalStorageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        StorageSpace targetStorageSpace = ServiceManager.storageSpaceService.findOne(drugInBucket.getStorageSpaceId());

        chineseMedicinePiecesLoadingBucketRecord.setShopId(drugInBucket.getShopId());
        chineseMedicinePiecesLoadingBucketRecord.setLoadingBucketDate(drugInBucket.getInBucketTime());
        chineseMedicinePiecesLoadingBucketRecord.setLoadingBucketManNm(drugInBucket.getInBucketManName());
        chineseMedicinePiecesLoadingBucketRecord.setApproveManId(drugInBucket.getApproveManId());
        chineseMedicinePiecesLoadingBucketRecord.setGoodsNm(goods.getGoodsNm());
        chineseMedicinePiecesLoadingBucketRecord.setGoodsPinyin(goods.getPinyin());
        chineseMedicinePiecesLoadingBucketRecord.setCommonNm(goods.getCommonNm());
        chineseMedicinePiecesLoadingBucketRecord.setSpec(goods.getSpec());
        chineseMedicinePiecesLoadingBucketRecord.setProduceManufacturer(goods.getProduceManufacturer());
        chineseMedicinePiecesLoadingBucketRecord.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
        chineseMedicinePiecesLoadingBucketRecord.setBatch(goodsBatch.getBatch());
        chineseMedicinePiecesLoadingBucketRecord.setProduceDate(goodsBatch.getProduceDate());
        chineseMedicinePiecesLoadingBucketRecord.setValidDate(goodsBatch.getValidDate());
        chineseMedicinePiecesLoadingBucketRecord.setOriginalStorageSpaceNm(originalStorageSpace.getStorageSpaceNm());
        chineseMedicinePiecesLoadingBucketRecord.setTargetStorageSpaceNm(targetStorageSpace.getStorageSpaceNm());
        chineseMedicinePiecesLoadingBucketRecord.setLoadingBucketQuantity(drugInBucket.getQuantity());

        return chineseMedicinePiecesLoadingBucketRecord;
    }

    /**
     * 保存装斗出入库日志
     *
     * @param drugInBucket
     */
    private void saveOutInStockLog(DrugInBucket drugInBucket) {
        OutInStockLog outLog = new OutInStockLog();
        OutInStockLog inLog = new OutInStockLog();
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugInBucket.getBatchId());
        Double moveAmount = BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), drugInBucket.getQuantity());
        Double clearingPrevAmount = BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice());
        outLog.setShopId(drugInBucket.getShopId());
        outLog.setGoodsId(drugInBucket.getGoodsId());
        outLog.setSkuId(drugInBucket.getSkuId());
        outLog.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
        outLog.setGoodsBatchId(drugInBucket.getBatchId());
        outLog.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        outLog.setQuantity(0 - drugInBucket.getQuantity());
        outLog.setAmount(0 - moveAmount);
        outLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        outLog.setClearingPrevAmount(clearingPrevAmount);
        outLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.DRUG_IN_BUCKET.toCode());
        outLog.setLogSourceObjectId(drugInBucket.getId());

        inLog.setShopId(drugInBucket.getShopId());
        inLog.setGoodsId(drugInBucket.getGoodsId());
        inLog.setSkuId(drugInBucket.getSkuId());
        inLog.setTypeCode(OutInStockTypeCodeEnum.IN_STOCK.toCode());
        inLog.setGoodsBatchId(drugInBucket.getBatchId());
        inLog.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        inLog.setQuantity(drugInBucket.getQuantity());
        inLog.setAmount(moveAmount);
        inLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        inLog.setClearingPrevAmount(clearingPrevAmount);
        inLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.DRUG_IN_BUCKET.toCode());
        inLog.setLogSourceObjectId(drugInBucket.getId());

        ServiceManager.outInStockLogService.save(outLog);
        ServiceManager.outInStockLogService.save(inLog);
    }

    /**
     * 构建DrugInBucketVo对象
     *
     * @param drugInBucket
     * @return
     */
    private DrugInBucketVo buildDrugInBucketVo(DrugInBucket drugInBucket) {
        SysUser sysUser = ServiceManager.sysUserService.findOne(drugInBucket.getApproveManId());
        DrugInBucketVo drugInBucketVo = CommonUtil.copyProperties(drugInBucket, new DrugInBucketVo());
        drugInBucketVo.setIsQualityQualified(BoolCodeEnum.fromCode(drugInBucket.getIsQualityQualified()).toCode());
        drugInBucketVo.setApproveManNm(sysUser.getRealName());
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugInBucket.getBatchId());
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        drugInBucketVo.setGoodsNm(goods.getGoodsNm());
        drugInBucketVo.setCommonNm(goods.getCommonNm());
        drugInBucketVo.setSpec(goods.getSpec());
        drugInBucketVo.setGoodsCode(goods.getGoodsCode());
        drugInBucketVo.setProduceManufacturer(goods.getProduceManufacturer());
        drugInBucketVo.setUnit(goods.getUnit());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugInBucketVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                drugInBucketVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugInBucketVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugInBucketVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                drugInBucketVo.setDosageForm(sysDictItem.getDictItemNm());
                drugInBucketVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugInBucketVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugInBucketVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(drugInBucket.getStorageSpaceId());
        drugInBucketVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        drugInBucketVo.setBatch(goodsBatch.getBatch());
        drugInBucketVo.setProduceDateString(goodsBatch.getProduceDateString());
        drugInBucketVo.setValidDateString(goodsBatch.getValidDateString());
        drugInBucketVo.setInBucketTimeString(drugInBucket.getInBucketTimeString());
        return drugInBucketVo;
    }
}