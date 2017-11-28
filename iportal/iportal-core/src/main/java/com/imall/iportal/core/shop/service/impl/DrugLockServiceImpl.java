package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.DrugLockRepository;
import com.imall.iportal.core.shop.service.DrugLockService;
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
import java.util.Date;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class DrugLockServiceImpl extends AbstractBaseService<DrugLock, Long> implements DrugLockService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DrugLockRepository getDrugLockRepository() {
        return (DrugLockRepository) baseRepository;
    }

    @Override
    public Page<DrugLockPageVo> query(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam) {
        Page<DrugLock> drugLockPage = getDrugLockRepository().query(pageable, problemDrugSearchParam);
        List<DrugLockPageVo> drugLockPageVoList = new ArrayList<>();
        for (DrugLock drugLock : drugLockPage.getContent()) {
            drugLockPageVoList.add(buildDrugLockPageVo(drugLock));
        }
        return new PageImpl<>(drugLockPageVoList, new PageRequest(drugLockPage.getNumber(), drugLockPage.getSize()), drugLockPage.getTotalElements());
    }

    @Override
    public Page<DrugLockPageVo> queryDrugLockDeal(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam) {
        Page<DrugLock> drugLockPage = getDrugLockRepository().queryDrugLockDeal(pageable, problemDrugSearchParam);
        List<DrugLockPageVo> drugLockPageVoList = new ArrayList<>();
        for (DrugLock drugLock : drugLockPage.getContent()) {
            drugLockPageVoList.add(buildDrugLockPageVo(drugLock));
        }
        return new PageImpl<>(drugLockPageVoList, pageable, drugLockPage.getTotalElements());
    }

    @Override
    @Transactional
    public void saveDrugLock(DrugLockSaveVo drugLockSaveVo) throws ParseException {
        if (CollectionUtils.isEmpty(drugLockSaveVo.getDrugLockGoodsSaveVoList())) {
            return;
        }
        for (DrugLockGoodsSaveVo drugLockGoodsSaveVo : drugLockSaveVo.getDrugLockGoodsSaveVoList()) {
            Long lockQuantity = drugLockGoodsSaveVo.getLockQuantity();   //锁定数量
            Long id = null;
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLockGoodsSaveVo.getBatchId());
            //检查锁定数量是否大于当前库存
            if (lockQuantity > goodsBatch.getCurrentStock()) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.GOODS_BATCH_QUANTITY_NOT_ENOUGH);
                throw new BusinessException(ResGlobalExt.GOODS_BATCH_QUANTITY_NOT_ENOUGH, String.format(message, new Object[]{goodsBatch.getBatch()}));
            }
            //更新库存(原批次库存减掉锁定的数量)
            ServiceManager.goodsBatchService.updateSubtractCurrentStock(drugLockGoodsSaveVo.getBatchId(), lockQuantity);

            //检查是否已经存在锁定的批次,如果存在则更新锁定批次库存,不存在就新增一个锁定批次。
            GoodsBatch drugLockBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(goodsBatch), GoodsBatchStateCodeEnum.LOCK.toCode());
            if (drugLockBatch != null) {   //更新锁定批次库存数量
                drugLockBatch.setCurrentStock(drugLockBatch.getCurrentStock() + lockQuantity);
                ServiceManager.goodsBatchService.update(drugLockBatch);
            } else {     //新增一个锁定批次
                GoodsBatch batch = new GoodsBatch();
                batch.setShopId(drugLockSaveVo.getShopId());
                batch.setGoodsId(drugLockGoodsSaveVo.getGoodsId());
                batch.setSkuId(goodsBatch.getSkuId());
                batch.setBatch(goodsBatch.getBatch());
                batch.setProduceDate(DateTimeUtils.convertStringToDate(goodsBatch.getProduceDateString()));
                batch.setValidDate(DateTimeUtils.convertStringToDate(goodsBatch.getValidDateString()));
                batch.setCurrentStock(lockQuantity);
                batch.setStorageSpaceId(goodsBatch.getStorageSpaceId());
                batch.setSupplierId(goodsBatch.getSupplierId());
                batch.setPinyin(goodsBatch.getPinyin());
                batch.setGoodsCode(goodsBatch.getGoodsCode());
                batch.setGoodsNm(goodsBatch.getGoodsNm());
                batch.setGoodsCommonNm(goodsBatch.getGoodsCommonNm());
                batch.setPurchasePrice(goodsBatch.getPurchasePrice());
                batch.setBatchState(GoodsBatchStateCodeEnum.LOCK.toCode());
                id = ServiceManager.goodsBatchService.save(batch).getId();
            }
            DrugLock drugLock = buildDrugLock(drugLockSaveVo, drugLockGoodsSaveVo, drugLockBatch, id);
            saveOutInStockLog(drugLock, false,null);
        }
    }

    private DrugLock buildDrugLock(DrugLockSaveVo drugLockSaveVo, DrugLockGoodsSaveVo drugLockGoodsSaveVo, GoodsBatch drugLockBatch, Long id) throws ParseException {
        DrugLock drugLock = new DrugLock();
        drugLock.setShopId(drugLockSaveVo.getShopId());
        drugLock.setLockManName(drugLockSaveVo.getLockManName());
        if (StringUtils.isNotBlank(drugLockSaveVo.getLockTimeString())) {
            drugLock.setLockTime(DateTimeUtils.convertStringToDate(drugLockSaveVo.getLockTimeString()));
        }
        drugLock.setLockStateCode(DrugLockStateCodeEnum.PENDING.toCode());
        drugLock.setLockQuantity(drugLockGoodsSaveVo.getLockQuantity());
        if (StringUtils.isNotBlank(drugLockGoodsSaveVo.getLockReason())) {
            drugLock.setLockReason(drugLockGoodsSaveVo.getLockReason());
        }
        if (StringUtils.isNotBlank(drugLockGoodsSaveVo.getRemark())) {
            drugLock.setRemark(drugLockGoodsSaveVo.getRemark());
        }
        drugLock.setGoodsBatchId(drugLockGoodsSaveVo.getBatchId());
        //锁定批次ID
        drugLock.setLockBatchId(drugLockBatch != null ? drugLockBatch.getId() : id);
        drugLock.setGoodsId(drugLockGoodsSaveVo.getGoodsId());
        drugLock.setGoodsCode(drugLockGoodsSaveVo.getGoodsCode());
        drugLock.setGoodsCommonNm(drugLockGoodsSaveVo.getCommonNm());
        drugLock.setPinyin(drugLockGoodsSaveVo.getPinyin());
        drugLock.setGoodsNm(drugLockGoodsSaveVo.getGoodsNm());
        drugLock.setSkuId(drugLockGoodsSaveVo.getSkuId());
        this.save(drugLock);
        return drugLock;
    }

    private void saveOutInStockLog(DrugLock drugLock, Boolean isInStock, String processResultCode) {
        OutInStockLog outInStockLog = new OutInStockLog();
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());
        outInStockLog.setShopId(drugLock.getShopId());
        outInStockLog.setGoodsId(drugLock.getGoodsId());
        outInStockLog.setGoodsBatchId(drugLock.getGoodsBatchId());
        outInStockLog.setSkuId(drugLock.getSkuId());
        outInStockLog.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        outInStockLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        outInStockLog.setClearingPrevAmount(BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
        outInStockLog.setLogSourceObjectId(drugLock.getId());
        Double amount = BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), drugLock.getLockQuantity());
        if (isInStock) {
            outInStockLog.setQuantity(drugLock.getLockQuantity());
            outInStockLog.setAmount(amount);
            outInStockLog.setTypeCode(OutInStockTypeCodeEnum.IN_STOCK.toCode());
            outInStockLog.setLogSourceTypeCode(DrugLockProcessResultCodeEnum.fromCode(processResultCode) == DrugLockProcessResultCodeEnum.UNLOCK?OutInStockLogSourceTypeCodeEnum.RELEASE_LOCK.toCode() : OutInStockLogSourceTypeCodeEnum.NOT_QUALIFIED.toCode());
        } else {
            outInStockLog.setQuantity(0 - drugLock.getLockQuantity());
            outInStockLog.setAmount(0 - amount);
            outInStockLog.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
            outInStockLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.DRUG_LOCK.toCode());
        }

        ServiceManager.outInStockLogService.save(outInStockLog);
    }

    private DrugLockPageVo buildDrugLockPageVo(DrugLock drugLock) {
        DrugLockPageVo drugLockPageVo = CommonUtil.copyProperties(drugLock, new DrugLockPageVo());
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        drugLockPageVo.setGoodsNm(goods.getGoodsNm());
        drugLockPageVo.setCommonNm(goods.getCommonNm());
        drugLockPageVo.setSpec(goods.getSpec());
        drugLockPageVo.setGoodsCode(goods.getGoodsCode());
        drugLockPageVo.setProduceManufacturer(goods.getProduceManufacturer());
        drugLockPageVo.setUnit(goods.getUnit());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                drugLockPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                drugLockPageVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                drugLockPageVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                drugLockPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                drugLockPageVo.setDosageForm(sysDictItem.getDictItemNm());
                drugLockPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                drugLockPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goodsBatch.getGoodsId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                drugLockPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        drugLockPageVo.setLockTimeString(drugLock.getLockTimeString());
        drugLockPageVo.setCurrentStock(goodsBatch.getCurrentStock());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        drugLockPageVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        drugLockPageVo.setBatch(goodsBatch.getBatch());
        drugLockPageVo.setProduceDateString(goodsBatch.getProduceDateString());
        drugLockPageVo.setValidDateString(goodsBatch.getValidDateString());
        return drugLockPageVo;
    }


    @Override
    @Transactional
    public void updateDrugLockDeal(DrugLockDealUpdateVo drugLockDealUpdateVo) throws ParseException {
        if (CollectionUtils.isEmpty(drugLockDealUpdateVo.getDrugLockDealGoodsVoList())) {
            return;
        }

        for (DrugLockDealIdsVo drugLockDealGoodsVo : drugLockDealUpdateVo.getDrugLockDealGoodsVoList()) {
            DrugLock drugLock = this.findOne(drugLockDealGoodsVo.getId());
            if (drugLock == null || !drugLockDealUpdateVo.getShopId().equals(drugLock.getShopId())) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品锁定"}));
            }
            if (StringUtils.isNotBlank(drugLockDealUpdateVo.getProcessTimeString())) {
                drugLock.setProcessTime(DateTimeUtils.convertStringToDate(drugLockDealUpdateVo.getProcessTimeString()));
            }
            if (StringUtils.isNotBlank(drugLockDealUpdateVo.getProcessReason())) {
                drugLock.setProcessReason(drugLockDealUpdateVo.getProcessReason());
            }

            //如果处理状态不合格的话将锁定状态更新为待销毁状态
            if (DrugLockProcessResultCodeEnum.DISQUALIFICATION == DrugLockProcessResultCodeEnum.fromCode(drugLockDealUpdateVo.getProcessResultCode())) {
                drugLock.setLockStateCode(DrugLockStateCodeEnum.WAIT_DESTROY.toCode());
                drugLock.setProcessResultCode(DrugLockProcessResultCodeEnum.DISQUALIFICATION.toCode());
                GoodsBatch batch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());

                GoodsBatch drugLockGoodsBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(batch), GoodsBatchStateCodeEnum.LOCK.toCode());
                //锁定批次的库存 == 锁定数量
                if (drugLock.getLockQuantity().equals(drugLockGoodsBatch.getCurrentStock())) {
                    GoodsBatch destroyGoodsBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(batch), GoodsBatchStateCodeEnum.WAIT_DESTROY.toCode());

                    if (destroyGoodsBatch != null) {   //如果已经存在待销毁的批次，那么更新待销毁批次数量并删除锁定批次信息
                        destroyGoodsBatch.setCurrentStock(destroyGoodsBatch.getCurrentStock() + drugLock.getLockQuantity());
                        ServiceManager.goodsBatchService.update(destroyGoodsBatch);
                        ServiceManager.goodsBatchService.deleteBatch(drugLockGoodsBatch);
                    } else {   //更新锁定批次的状态为待销毁
                        drugLockGoodsBatch.setBatchState(GoodsBatchStateCodeEnum.WAIT_DESTROY.toCode());
                        ServiceManager.goodsBatchService.update(drugLockGoodsBatch);
                    }

                } else {  //锁定批次的库存 != 锁定数量
                    GoodsBatch destroyGoodsBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(batch), GoodsBatchStateCodeEnum.WAIT_DESTROY.toCode());
                    if (destroyGoodsBatch != null) {
                        destroyGoodsBatch.setCurrentStock(destroyGoodsBatch.getCurrentStock() + drugLock.getLockQuantity());
                        ServiceManager.goodsBatchService.update(destroyGoodsBatch);
                        //更新锁定批次库存
                        drugLockGoodsBatch.setCurrentStock(drugLockGoodsBatch.getCurrentStock() - drugLock.getLockQuantity());
                        ServiceManager.goodsBatchService.update(drugLockGoodsBatch);
                    } else {
                        //新增一条待销毁批次信息
                        buildBatch(batch, drugLock);
                        //更新锁定批次库存
                        drugLockGoodsBatch.setCurrentStock(drugLockGoodsBatch.getCurrentStock() - drugLock.getLockQuantity());
                        ServiceManager.goodsBatchService.update(drugLockGoodsBatch);
                    }
                }

                //生成不合格药品处理记录
                DisqualificationDrugProcessRecord disqualificationDrugProcessRecord = new DisqualificationDrugProcessRecord();
                disqualificationDrugProcessRecord.setShopId(drugLock.getShopId());
                disqualificationDrugProcessRecord.setRecordDate(new Date());
                disqualificationDrugProcessRecord.setDocumentType(DocumentTypeCodeEnum.DRUG_LOCK_DEAL.toCode());
                disqualificationDrugProcessRecord.setGoodsCode(drugLock.getGoodsCode());
                disqualificationDrugProcessRecord.setGoodsNm(drugLock.getGoodsNm());
                disqualificationDrugProcessRecord.setGoodsPinyin(drugLock.getPinyin());
                disqualificationDrugProcessRecord.setCommonNm(drugLock.getGoodsCommonNm());
                Goods goods = ServiceManager.goodsService.findOne(drugLock.getGoodsId());
                disqualificationDrugProcessRecord.setSpec(goods.getSpec());
                disqualificationDrugProcessRecord.setUnit(goods.getUnit());
                disqualificationDrugProcessRecord.setProduceManufacturer(goods.getProduceManufacturer());
                switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
                    case CHINESE_MEDICINE_PIECES:       //中药饮片
                        GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(drugLock.getGoodsId());
                        if (goodsChineseMedicinePieces == null) {
                            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                        }
                        SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                        disqualificationDrugProcessRecord.setDosageForm(sysDictItem.getDictItemNm());
                        disqualificationDrugProcessRecord.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                        disqualificationDrugProcessRecord.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                        break;
                    case DAILY_NECESSITIES:     //日用品
                        GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                        if (goodsDailyNecessities == null) {
                            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                        }
                        disqualificationDrugProcessRecord.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                        break;
                    case COSMETIC:      //化妆品
                        GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                        if (goodsCosmetic == null) {
                            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                        }
                        disqualificationDrugProcessRecord.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                        break;
                    case OTHER:     //其他
                        GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                        if (goodsOther == null) {
                            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                        }
                        disqualificationDrugProcessRecord.setApprovalNumber(goodsOther.getApprovalNumber());
                        break;
                    case DRUG:        //药品
                        GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                        if (goodDrug == null) {
                            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                        }
                        disqualificationDrugProcessRecord.setApprovalNumber(goodDrug.getApprovalNumber());
                        SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                        disqualificationDrugProcessRecord.setDosageForm(sysDictItem2.getDictItemNm());
                        break;
                }
                GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());
                disqualificationDrugProcessRecord.setBatch(goodsBatch.getBatch());
                disqualificationDrugProcessRecord.setValidDate(DateTimeUtils.convertStringToDate(goodsBatch.getValidDateString()));
                disqualificationDrugProcessRecord.setQuantity(drugLock.getLockQuantity());
                disqualificationDrugProcessRecord.setProcessMeasure("转移到不合格库");
                disqualificationDrugProcessRecord.setRemark(drugLock.getRemark());
                ServiceManager.disqualificationDrugProcessRecordService.save(disqualificationDrugProcessRecord);
            } else if (DrugLockProcessResultCodeEnum.UNLOCK == DrugLockProcessResultCodeEnum.fromCode(drugLockDealUpdateVo.getProcessResultCode())) {
                drugLock.setLockStateCode(DrugLockStateCodeEnum.UNLOCK.toCode());
                drugLock.setProcessResultCode(DrugLockProcessResultCodeEnum.UNLOCK.toCode());

                //更新批次库存
                Long lockQuantity = drugLock.getLockQuantity();
                GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());
                goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() + lockQuantity);
                ServiceManager.goodsBatchService.update(goodsBatch);
                GoodsBatch drugLockGoodsBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(goodsBatch), GoodsBatchStateCodeEnum.LOCK.toCode());

                if (drugLockGoodsBatch.getCurrentStock().equals(drugLock.getLockQuantity())) {
                    ServiceManager.goodsBatchService.deleteBatch(drugLockGoodsBatch);  //删除锁定批次
                } else {
                    drugLockGoodsBatch.setCurrentStock(drugLockGoodsBatch.getCurrentStock() - drugLock.getLockQuantity());
                    ServiceManager.goodsBatchService.update(drugLockGoodsBatch);   //减掉锁定库存数量
                }
            }
            drugLock.setApproveManId(drugLockDealUpdateVo.getApproveManId());

            this.update(drugLock);
            saveOutInStockLog(drugLock, true, drugLockDealUpdateVo.getProcessResultCode());
        }
    }

    /**
     * 构建批次信息
     *
     * @param batch
     * @param drugLock
     * @throws ParseException
     */
    private void buildBatch(GoodsBatch batch, DrugLock drugLock) throws ParseException {
        GoodsBatch waitDestroyGoodsBatch = new GoodsBatch();
        waitDestroyGoodsBatch.setShopId(batch.getShopId());
        waitDestroyGoodsBatch.setGoodsId(batch.getGoodsId());
        waitDestroyGoodsBatch.setSkuId(batch.getSkuId());
        waitDestroyGoodsBatch.setBatch(batch.getBatch());
        waitDestroyGoodsBatch.setProduceDate(DateTimeUtils.convertStringToDate(batch.getProduceDateString()));
        waitDestroyGoodsBatch.setValidDate(DateTimeUtils.convertStringToDate(batch.getValidDateString()));
        waitDestroyGoodsBatch.setCurrentStock(drugLock.getLockQuantity());
        waitDestroyGoodsBatch.setStorageSpaceId(batch.getStorageSpaceId());
        waitDestroyGoodsBatch.setSupplierId(batch.getSupplierId());
        waitDestroyGoodsBatch.setPinyin(batch.getPinyin());
        waitDestroyGoodsBatch.setGoodsCode(batch.getGoodsCode());
        waitDestroyGoodsBatch.setGoodsNm(batch.getGoodsNm());
        waitDestroyGoodsBatch.setGoodsCommonNm(batch.getGoodsCommonNm());
        waitDestroyGoodsBatch.setPurchasePrice(batch.getPurchasePrice());
        waitDestroyGoodsBatch.setBatchState(GoodsBatchStateCodeEnum.WAIT_DESTROY.toCode());
        ServiceManager.goodsBatchService.save(waitDestroyGoodsBatch);
    }

    /**
     * 构建商品批次搜索参数
     *
     * @param batch 商品批次
     */
    private GoodsBatchSimpleSearchVo buildGoodsBatchSimpleSearchVo(GoodsBatch batch) {
        GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo = new GoodsBatchSimpleSearchVo();
        goodsBatchSimpleSearchVo.setBatch(batch.getBatch());
        goodsBatchSimpleSearchVo.setGoodsId(batch.getGoodsId());
        goodsBatchSimpleSearchVo.setStorageSpaceId(batch.getStorageSpaceId());
        goodsBatchSimpleSearchVo.setPurchasePrice(batch.getPurchasePrice());
        goodsBatchSimpleSearchVo.setSupplierId(batch.getSupplierId());
        return goodsBatchSimpleSearchVo;
    }

    @Override
    public Page<DrugLockDealGoodsBatchPageVo> queryDrugLockDealGoodsBatchList(Pageable pageable, DrugLockDealGoodsSearchParam drugLockDealGoodsSearchParam, Long shopId) {
        Page<DrugLock> drugLockPage = getDrugLockRepository().queryDrugLockDealGoodsBatchList(pageable, drugLockDealGoodsSearchParam, shopId);
        List<DrugLockDealGoodsBatchPageVo> drugLockDealGoodsBatchPageVoList = new ArrayList<>();
        for (DrugLock drugLock : drugLockPage.getContent()) {
            drugLockDealGoodsBatchPageVoList.add(buildDrugLockDealGoodsBatchPageVo(drugLock));
        }
        return new PageImpl<>(drugLockDealGoodsBatchPageVoList, new PageRequest(drugLockPage.getNumber(), drugLockPage.getSize()), drugLockPage.getTotalElements());
    }

    @Override
    public Page<DrugDestroyPageVo> queryDrugDestroyPage(Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam) {
        Page<DrugLock> drugLockPage = getDrugLockRepository().queryDrugDestroyPage(pageable, problemDrugSearchParam);
        List<DrugDestroyPageVo> destroyPageVoList = new ArrayList<>();
        for (DrugLock drugLock : drugLockPage.getContent()) {
            destroyPageVoList.add(buildDrugDestroyPageVo(drugLock));
        }
        return new PageImpl<>(destroyPageVoList, pageable, drugLockPage.getTotalElements());
    }

    @Override
    @Transactional
    public void drugLockDestroy(Long id, DrugLockDestroyVo drugLockDestroyVo) throws ParseException {
        DrugLock drugLock = this.findOne(id);
        if (drugLock == null || !drugLock.getShopId().equals(drugLockDestroyVo.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品锁定"}));
        }
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());
        Goods goods = ServiceManager.goodsService.findOne(drugLock.getGoodsId());

        OutInStockLog outInStockLog = new OutInStockLog();
        outInStockLog.setShopId(drugLock.getShopId());
        outInStockLog.setGoodsId(drugLock.getGoodsId());
        outInStockLog.setGoodsBatchId(drugLock.getGoodsBatchId());
        outInStockLog.setSkuId(drugLock.getSkuId());
        outInStockLog.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        outInStockLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        outInStockLog.setClearingPrevAmount(BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
        outInStockLog.setLogSourceObjectId(drugLock.getId());
        Double amount = BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), drugLock.getLockQuantity());
        outInStockLog.setQuantity(0 - drugLock.getLockQuantity());
        outInStockLog.setAmount(0 - amount);
        outInStockLog.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
        outInStockLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.DRUG_DESTROY.toCode());
        ServiceManager.outInStockLogService.save(outInStockLog);

        //生成销毁记录
        buildDestroyRecord(drugLock, drugLockDestroyVo, goodsBatch, goods);

        drugLock.setLockStateCode(DrugLockStateCodeEnum.DESTROYED.toCode());
        this.update(drugLock);

        //更新待销毁批次库存
        GoodsBatch destroyBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(goodsBatch), GoodsBatchStateCodeEnum.WAIT_DESTROY.toCode());
        if (destroyBatch.getCurrentStock().equals(drugLock.getLockQuantity())) {   //如果销毁数量等于待销毁批次库存，则删除待销毁批次
            ServiceManager.goodsBatchService.deleteBatch(destroyBatch);
        } else {     //减少待销毁批次库存
            destroyBatch.setCurrentStock(destroyBatch.getCurrentStock() - drugLock.getLockQuantity());
            ServiceManager.goodsBatchService.update(destroyBatch);
        }

        //生成不合格药品销毁记录
        buildDisqualificationDrugProcessRecord(goodsBatch, drugLock, goods);
    }

    /**
     * 构建不合格药品处理记录
     *
     * @param goodsBatch
     * @param drugLock
     */
    private void buildDisqualificationDrugProcessRecord(GoodsBatch goodsBatch, DrugLock drugLock, Goods goods) throws ParseException {
        DisqualificationDrugProcessRecord disqualificationDrugProcessRecord = new DisqualificationDrugProcessRecord();
        disqualificationDrugProcessRecord.setShopId(drugLock.getShopId());
        disqualificationDrugProcessRecord.setRecordDate(new Date());
        disqualificationDrugProcessRecord.setDocumentType(DocumentTypeCodeEnum.DRUG_LOCK_DESTROY.toCode());
        disqualificationDrugProcessRecord.setGoodsCode(drugLock.getGoodsCode());
        disqualificationDrugProcessRecord.setGoodsNm(drugLock.getGoodsNm());
        disqualificationDrugProcessRecord.setGoodsPinyin(drugLock.getPinyin());
        disqualificationDrugProcessRecord.setCommonNm(drugLock.getGoodsCommonNm());
        disqualificationDrugProcessRecord.setSpec(goods.getSpec());
        disqualificationDrugProcessRecord.setUnit(goods.getUnit());
        disqualificationDrugProcessRecord.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(drugLock.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                disqualificationDrugProcessRecord.setDosageForm(sysDictItem.getDictItemNm());
                disqualificationDrugProcessRecord.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                disqualificationDrugProcessRecord.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                disqualificationDrugProcessRecord.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                disqualificationDrugProcessRecord.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                disqualificationDrugProcessRecord.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                disqualificationDrugProcessRecord.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                disqualificationDrugProcessRecord.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        disqualificationDrugProcessRecord.setBatch(goodsBatch.getBatch());
        disqualificationDrugProcessRecord.setValidDate(DateTimeUtils.convertStringToDate(goodsBatch.getValidDateString()));
        disqualificationDrugProcessRecord.setQuantity(drugLock.getLockQuantity());
        disqualificationDrugProcessRecord.setProcessMeasure("不合格销毁");
        disqualificationDrugProcessRecord.setRemark(drugLock.getRemark());
        ServiceManager.disqualificationDrugProcessRecordService.save(disqualificationDrugProcessRecord);
    }

    /**
     * 构建销毁记录
     *
     * @param drugLock
     * @param drugLockDestroyVo
     * @param goodsBatch
     * @param goods
     * @throws ParseException
     */
    private void buildDestroyRecord(DrugLock drugLock, DrugLockDestroyVo drugLockDestroyVo, GoodsBatch goodsBatch, Goods goods) throws ParseException {
        DestroyRecord destroyRecord = new DestroyRecord();
        destroyRecord.setShopId(drugLockDestroyVo.getShopId());
        destroyRecord.setGoodsCode(drugLock.getGoodsCode());
        destroyRecord.setGoodsNm(drugLock.getGoodsNm());
        destroyRecord.setGoodsPinyin(drugLock.getPinyin());
        destroyRecord.setCommonNm(drugLock.getGoodsCommonNm());
        destroyRecord.setSpec(goods.getSpec());
        destroyRecord.setUnit(goods.getUnit());
        destroyRecord.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(drugLock.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                destroyRecord.setDosageForm(sysDictItem.getDictItemNm());
                destroyRecord.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                destroyRecord.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                destroyRecord.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                destroyRecord.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                destroyRecord.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                destroyRecord.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                destroyRecord.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
        }
        destroyRecord.setSupplierNm(ServiceManager.supplierService.findOne(goodsBatch.getSupplierId()).getSupplierNm());
        destroyRecord.setBatch(goodsBatch.getBatch());
        destroyRecord.setStockQuantity(goodsBatch.getCurrentStock());
        destroyRecord.setLockQuantity(drugLock.getLockQuantity());
        destroyRecord.setLockReason(drugLock.getLockReason());
        destroyRecord.setProduceDate(DateTimeUtils.convertStringToDate(goodsBatch.getProduceDateString()));
        destroyRecord.setValidDate(DateTimeUtils.convertStringToDate(goodsBatch.getValidDateString()));
        destroyRecord.setStorageSpaceNm(ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId()).getStorageSpaceNm());
        destroyRecord.setDestroyMan(drugLockDestroyVo.getDestroyMan());
        destroyRecord.setDestroyPlace(drugLockDestroyVo.getDestroyPlace());
        destroyRecord.setDestroyReason(drugLockDestroyVo.getDestroyReason());
        destroyRecord.setDestroyTime(StringUtils.isNotBlank(drugLockDestroyVo.getDestroyTimeString()) ? DateTimeUtils.convertStringToDate(drugLockDestroyVo.getDestroyTimeString()) : new Date());
        destroyRecord.setKeeper(drugLockDestroyVo.getKeeper());
        destroyRecord.setApproveManId(drugLockDestroyVo.getApproveManId());
        destroyRecord.setReviewerId(drugLockDestroyVo.getReviewerId());
        ServiceManager.destroyRecordService.save(destroyRecord);
    }


    private DrugDestroyPageVo buildDrugDestroyPageVo(DrugLock drugLock) {
        DrugDestroyPageVo drugDestroyPageVo = CommonUtil.copyProperties(drugLock, new DrugDestroyPageVo());
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        drugDestroyPageVo.setGoodsNm(goods.getGoodsNm());
        drugDestroyPageVo.setCommonNm(goods.getCommonNm());
        drugDestroyPageVo.setSpec(goods.getSpec());
        drugDestroyPageVo.setGoodsCode(goods.getGoodsCode());
        drugDestroyPageVo.setProduceManufacturer(goods.getProduceManufacturer());
        drugDestroyPageVo.setUnit(goods.getUnit());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(drugLock.getGoodsId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                drugDestroyPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(drugLock.getGoodsId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                drugDestroyPageVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                drugDestroyPageVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(drugLock.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                drugDestroyPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                drugDestroyPageVo.setDosageForm(sysDictItem.getDictItemNm());
                drugDestroyPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(drugLock.getGoodsId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                drugDestroyPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(drugLock.getGoodsId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                drugDestroyPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
        }
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        drugDestroyPageVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        drugDestroyPageVo.setBatch(goodsBatch.getBatch());
        drugDestroyPageVo.setSupplierNm(ServiceManager.supplierService.findOne(goodsBatch.getSupplierId()).getSupplierNm());
        return drugDestroyPageVo;
    }

    private DrugLockDealGoodsBatchPageVo buildDrugLockDealGoodsBatchPageVo(DrugLock drugLock) {
        DrugLockDealGoodsBatchPageVo drugLockDealGoodsBatchPageVo = CommonUtil.copyProperties(drugLock, new DrugLockDealGoodsBatchPageVo());
        Goods goods = ServiceManager.goodsService.findOne(drugLock.getGoodsId());
        drugLockDealGoodsBatchPageVo.setGoodsCode(goods.getGoodsCode());
        drugLockDealGoodsBatchPageVo.setGoodsNm(goods.getGoodsNm());
        drugLockDealGoodsBatchPageVo.setProduceManufacturer(goods.getProduceManufacturer());
        drugLockDealGoodsBatchPageVo.setCommonNm(goods.getCommonNm());
        drugLockDealGoodsBatchPageVo.setSpec(goods.getSpec());
        drugLockDealGoodsBatchPageVo.setUnit(goods.getUnit());
        drugLockDealGoodsBatchPageVo.setPinyin(goods.getPinyin());

        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(drugLock.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                drugLockDealGoodsBatchPageVo.setDosageForm(sysDictItem.getDictItemNm());
                drugLockDealGoodsBatchPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                drugLockDealGoodsBatchPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                drugLockDealGoodsBatchPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                drugLockDealGoodsBatchPageVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                drugLockDealGoodsBatchPageVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                drugLockDealGoodsBatchPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                drugLockDealGoodsBatchPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
        }
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(drugLock.getGoodsBatchId());
        drugLockDealGoodsBatchPageVo.setCurrentStock(goodsBatch.getCurrentStock());
        drugLockDealGoodsBatchPageVo.setBatch(goodsBatch.getBatch());
        drugLockDealGoodsBatchPageVo.setProduceDateString(goodsBatch.getProduceDateString());
        drugLockDealGoodsBatchPageVo.setValidDateString(goodsBatch.getValidDateString());
        drugLockDealGoodsBatchPageVo.setStorageSpaceNm(ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId()).getStorageSpaceNm());
        return drugLockDealGoodsBatchPageVo;
    }

}