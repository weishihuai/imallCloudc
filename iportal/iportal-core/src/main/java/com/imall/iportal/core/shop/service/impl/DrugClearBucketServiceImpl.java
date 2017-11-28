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
import com.imall.iportal.core.shop.repository.DrugClearBucketRepository;
import com.imall.iportal.core.shop.service.DrugClearBucketService;
import com.imall.iportal.core.shop.vo.DrugClearBucketGoodsSaveVo;
import com.imall.iportal.core.shop.vo.DrugClearBucketSaveVo;
import com.imall.iportal.core.shop.vo.DrugClearBucketSearchParam;
import com.imall.iportal.core.shop.vo.DrugClearBucketVo;
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
public class DrugClearBucketServiceImpl extends AbstractBaseService<DrugClearBucket, Long> implements DrugClearBucketService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DrugClearBucketRepository getDrugClearBucketRepository() {
        return (DrugClearBucketRepository) baseRepository;
    }

    @Override
    public Page<DrugClearBucketVo> query(Pageable pageable, DrugClearBucketSearchParam drugClearBucketSearchParam) {
        Page<DrugClearBucket> drugClearBucketPage = getDrugClearBucketRepository().query(pageable, drugClearBucketSearchParam);
        List<DrugClearBucketVo> drugClearBucketVoList = new ArrayList<>();
        for (DrugClearBucket drugClearBucket : drugClearBucketPage.getContent()) {
            drugClearBucketVoList.add(buildDrugClearBucketVo(drugClearBucket));
        }
        return new PageImpl<>(drugClearBucketVoList, new PageRequest(drugClearBucketPage.getNumber(), drugClearBucketPage.getSize()), drugClearBucketPage.getTotalElements());
    }

    @Override
    public DrugClearBucketVo findById(Long shopId, Long id) {
        DrugClearBucket drugClearBucket = this.findOne(id);
        if (drugClearBucket == null || !drugClearBucket.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        return buildDrugClearBucketVo(drugClearBucket);
    }

    @Override
    @Transactional
    public void saveDrugClearBucket(DrugClearBucketSaveVo drugClearBucketSaveVo) throws ParseException {
        if (CollectionUtils.isEmpty(drugClearBucketSaveVo.getDrugClearBucketGoodsSaveVoList())) {
            return;
        }
        for (DrugClearBucketGoodsSaveVo drugClearBucketGoodsSaveVo : drugClearBucketSaveVo.getDrugClearBucketGoodsSaveVoList()) {
            DrugClearBucket drugClearBucket = CommonUtil.copyProperties(drugClearBucketGoodsSaveVo, new DrugClearBucket());
            drugClearBucket.setIsQualityQualified(BoolCodeEnum.fromCode(drugClearBucketGoodsSaveVo.getIsQualityQualified()).toCode());
            drugClearBucket.setClearBucketManName(drugClearBucketSaveVo.getClearBucketManName());
            drugClearBucket.setShopId(drugClearBucketSaveVo.getShopId());
            if (StringUtils.isNotBlank(drugClearBucketSaveVo.getClearBucketTimeString())) {
                drugClearBucket.setClearBucketTime(DateTimeUtils.convertStringToDate(drugClearBucketSaveVo.getClearBucketTimeString()));
            }
            drugClearBucket.setApproveManId(drugClearBucketSaveVo.getApproveManId());

            save(drugClearBucket);


            //保存出入库日志
            saveOutInStockLog(drugClearBucket);

            //清斗需要扣减库存，
            ServiceManager.goodsBatchService.updateSubtractCurrentStock(drugClearBucket.getBatchId(), drugClearBucket.getQuantity());

            //生成清斗记录
            ServiceManager.chineseMedicinePiecesCleaningBucketRecordService.saveChineseMedicinePiecesCleaningBucketRecord(buildChineseMedicinePiecesCleaningBucketRecord(drugClearBucket));

        }

    }

    private ChineseMedicinePiecesCleaningBucketRecord buildChineseMedicinePiecesCleaningBucketRecord(DrugClearBucket drugClearBucket) throws ParseException {
        ChineseMedicinePiecesCleaningBucketRecord chineseMedicinePiecesCleaningBucketRecord = new ChineseMedicinePiecesCleaningBucketRecord();
        chineseMedicinePiecesCleaningBucketRecord.setShopId(drugClearBucket.getShopId());
        chineseMedicinePiecesCleaningBucketRecord.setCleaningBucketDate(drugClearBucket.getClearBucketTimeString());
        chineseMedicinePiecesCleaningBucketRecord.setCleaningBucketQuantity(drugClearBucket.getQuantity());
        Goods goods = ServiceManager.goodsService.findOne(drugClearBucket.getGoodsId());
        chineseMedicinePiecesCleaningBucketRecord.setGoodsCode(goods.getGoodsCode());
        chineseMedicinePiecesCleaningBucketRecord.setGoodsNm(goods.getGoodsNm());
        chineseMedicinePiecesCleaningBucketRecord.setGoodsPinyin(goods.getPinyin());
        chineseMedicinePiecesCleaningBucketRecord.setCommonNm(goods.getCommonNm());
        chineseMedicinePiecesCleaningBucketRecord.setSpec(goods.getSpec());
        chineseMedicinePiecesCleaningBucketRecord.setUnit(goods.getUnit());
        chineseMedicinePiecesCleaningBucketRecord.setProduceManufacturer(goods.getProduceManufacturer());

        GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(drugClearBucket.getGoodsId());
        chineseMedicinePiecesCleaningBucketRecord.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
        chineseMedicinePiecesCleaningBucketRecord.setDosageForm(goodsChineseMedicinePieces.getDosageForm());
        chineseMedicinePiecesCleaningBucketRecord.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());

        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugClearBucket.getBatchId());
        chineseMedicinePiecesCleaningBucketRecord.setBatch(goodsBatch.getBatch());

        chineseMedicinePiecesCleaningBucketRecord.setProduceTime(goodsBatch.getProduceDate());
        chineseMedicinePiecesCleaningBucketRecord.setValidDate(goodsBatch.getValidDate());


        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        chineseMedicinePiecesCleaningBucketRecord.setStorageSpaceNm(storageSpace.getStorageSpaceNm());

        chineseMedicinePiecesCleaningBucketRecord.setCleaningBucketManNm(drugClearBucket.getClearBucketManName());
        chineseMedicinePiecesCleaningBucketRecord.setApproveManId(drugClearBucket.getApproveManId());
        return chineseMedicinePiecesCleaningBucketRecord;
    }
    /**
     * 出入库日志
     * @param drugClearBucket
     */
    private void saveOutInStockLog(DrugClearBucket drugClearBucket) {
        OutInStockLog log = new OutInStockLog();
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugClearBucket.getBatchId());
        log.setShopId(drugClearBucket.getShopId());
        log.setGoodsId(drugClearBucket.getGoodsId());
        log.setSkuId(drugClearBucket.getSkuId());
        log.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
        log.setGoodsBatchId(drugClearBucket.getBatchId());
        log.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        log.setQuantity(0 - drugClearBucket.getQuantity());
        log.setAmount(0 - BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), drugClearBucket.getQuantity()));
        log.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        log.setClearingPrevAmount(BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
        log.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.DRUG_CLEAR_BUCKET.toCode());
        log.setLogSourceObjectId(drugClearBucket.getId());
        ServiceManager.outInStockLogService.save(log);
    }



    private DrugClearBucketVo buildDrugClearBucketVo(DrugClearBucket drugClearBucket) {
        SysUser sysUser = ServiceManager.sysUserService.findOne(drugClearBucket.getApproveManId());
        DrugClearBucketVo drugClearBucketVo = CommonUtil.copyProperties(drugClearBucket, new DrugClearBucketVo());
        drugClearBucketVo.setApproveManNm(sysUser.getRealName());

        drugClearBucketVo.setIsQualityQualified(BoolCodeEnum.fromCode(drugClearBucket.getIsQualityQualified()).toCode());
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugClearBucket.getBatchId());
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        drugClearBucketVo.setGoodsNm(goods.getGoodsNm());
        drugClearBucketVo.setCommonNm(goods.getCommonNm());
        drugClearBucketVo.setSpec(goods.getSpec());
        drugClearBucketVo.setGoodsCode(goods.getGoodsCode());
        drugClearBucketVo.setProduceManufacturer(goods.getProduceManufacturer());
        drugClearBucketVo.setUnit(goods.getUnit());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugClearBucketVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                drugClearBucketVo.setDosageForm(sysDictItem.getDictItemNm());
                drugClearBucketVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugClearBucketVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                drugClearBucketVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugClearBucketVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugClearBucketVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
                }
                drugClearBucketVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(drugClearBucket.getStorageSpaceId());
        drugClearBucketVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        drugClearBucketVo.setBatch(goodsBatch.getBatch());
        drugClearBucketVo.setProduceDateString(goodsBatch.getProduceDateString());
        drugClearBucketVo.setValidDateString(goodsBatch.getValidDateString());
        drugClearBucketVo.setClearBucketTimeString(drugClearBucket.getClearBucketTimeString());
        return drugClearBucketVo;
    }
}