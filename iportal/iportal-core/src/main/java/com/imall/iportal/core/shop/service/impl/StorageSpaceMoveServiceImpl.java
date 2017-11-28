package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.OutInStockLogSourceTypeCodeEnum;
import com.imall.commons.dicts.OutInStockTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.StorageSpaceMoveRepository;
import com.imall.iportal.core.shop.service.StorageSpaceMoveService;
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
public class StorageSpaceMoveServiceImpl extends AbstractBaseService<StorageSpaceMove, Long> implements StorageSpaceMoveService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private StorageSpaceMoveRepository getStorageSpaceMoveRepository() {
        return (StorageSpaceMoveRepository) baseRepository;
    }

    @Override
    public Page<StorageSpaceMovePageVo> query(Pageable pageable, StorageSpaceMoveSearchParam storageSpaceMoveSearchParam) {
        Page<StorageSpaceMove> storageSpaceMovePage = getStorageSpaceMoveRepository().query(pageable, storageSpaceMoveSearchParam);
        List<StorageSpaceMovePageVo> storageSpaceMovePageVoList = new ArrayList<>();
        for (StorageSpaceMove storageSpaceMove : storageSpaceMovePage.getContent()) {
            storageSpaceMovePageVoList.add(buildStorageSpaceMovePageVo(storageSpaceMove));
        }
        return new PageImpl<>(storageSpaceMovePageVoList, new PageRequest(storageSpaceMovePage.getNumber(), storageSpaceMovePage.getSize()), storageSpaceMovePage.getTotalElements());
    }

    @Override
    public StorageSpaceMoveDetailVo findByMoveOrderNum(String moveOrderNum, Long shopId) {
        List<StorageSpaceMove> storageSpaceMoveList = getStorageSpaceMoveRepository().listByMoveOrderNum(moveOrderNum, shopId);
        StorageSpaceMoveDetailVo storageSpaceMoveDetailVo = new StorageSpaceMoveDetailVo();
        StorageSpaceMove spaceMove = storageSpaceMoveList.get(0);
        storageSpaceMoveDetailVo.setMoveOrderNum(spaceMove.getMoveOrderNum());
        storageSpaceMoveDetailVo.setMoveManName(spaceMove.getMoveManName());
        storageSpaceMoveDetailVo.setRemark(spaceMove.getRemark());
        storageSpaceMoveDetailVo.setMoveTimeString(spaceMove.getMoveTimeString());
        SysUser sysUser = ServiceManager.sysUserService.findOne(spaceMove.getApproveManId());
        storageSpaceMoveDetailVo.setApproveManName(sysUser.getRealName());
        List<StorageSpaceMoveGoodsVo> storageSpaceMoveGoodsVoList = new ArrayList<>();
        for (StorageSpaceMove storageSpaceMove : storageSpaceMoveList) {
            storageSpaceMoveGoodsVoList.add(buildStorageSpaceMoveGoodsVo(storageSpaceMove));
        }
        storageSpaceMoveDetailVo.setStorageSpaceMoveGoodsVoList(storageSpaceMoveGoodsVoList);
        return storageSpaceMoveDetailVo;
    }

    @Override
    @Transactional
    public void saveStorageSpaceMove(StorageSpaceMoveSaveVo storageSpaceMoveSaveVo) throws ParseException {
        if (CollectionUtils.isEmpty(storageSpaceMoveSaveVo.getStorageSpaceMoveGoodsSaveVoList())) {
            return;
        }
        for (StorageSpaceMoveGoodsSaveVo storageSpaceMoveGoodsSaveVo : storageSpaceMoveSaveVo.getStorageSpaceMoveGoodsSaveVoList()) {
            //检查移动数量是否大于当前库存
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(storageSpaceMoveGoodsSaveVo.getBatchId());
            if (storageSpaceMoveGoodsSaveVo.getQuantity() > goodsBatch.getCurrentStock()) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{goodsBatch.getBatch()}));
            }
            StorageSpaceMove storageSpaceMove = buildStorageSpaceMove(storageSpaceMoveSaveVo, storageSpaceMoveGoodsSaveVo);

            //保存货位移动出入库日志
            saveOutInStockLog(storageSpaceMove);

            //更新批次的货位信息
            if (storageSpaceMoveGoodsSaveVo.getQuantity().equals(goodsBatch.getCurrentStock())) {  //批次库存与移动数量相等
                GoodsBatch storageSpaceMoveBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(goodsBatch, storageSpaceMoveGoodsSaveVo), goodsBatch.getBatchState());
                if (storageSpaceMoveBatch == null) {
                    goodsBatch.setStorageSpaceId(storageSpaceMoveGoodsSaveVo.getTargetStorageSpaceId());
                    ServiceManager.goodsBatchService.update(goodsBatch);
                } else {
                    storageSpaceMoveBatch.setCurrentStock(storageSpaceMoveBatch.getCurrentStock() + storageSpaceMoveGoodsSaveVo.getQuantity());
                    ServiceManager.goodsBatchService.update(storageSpaceMoveBatch);
                    ServiceManager.goodsBatchService.deleteBatch(goodsBatch);
                }
            } else {   //批次库存与移动数量不相等
                GoodsBatch storageSpaceMoveBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(buildGoodsBatchSimpleSearchVo(goodsBatch, storageSpaceMoveGoodsSaveVo), goodsBatch.getBatchState());
                if (storageSpaceMoveBatch == null) {
                    //新增一个库存为移动数量的批次
                    buildBatch(storageSpaceMoveSaveVo, storageSpaceMoveGoodsSaveVo, goodsBatch);
                    //更新原批次的库存（减去移动数量）
                    goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() - storageSpaceMoveGoodsSaveVo.getQuantity());
                    ServiceManager.goodsBatchService.update(goodsBatch);
                } else {
                    //更新目标货位批次的库存
                    storageSpaceMoveBatch.setCurrentStock(storageSpaceMoveBatch.getCurrentStock() + storageSpaceMoveGoodsSaveVo.getQuantity());
                    ServiceManager.goodsBatchService.update(storageSpaceMoveBatch);
                    //更新原批次的库存（减去移动数量）
                    goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() - storageSpaceMoveGoodsSaveVo.getQuantity());
                    ServiceManager.goodsBatchService.update(goodsBatch);
                }
            }
        }
    }

    /**
     * 构建商品批次信息
     *
     * @param storageSpaceMoveSaveVo
     * @param storageSpaceMoveGoodsSaveVo
     * @param goodsBatch
     * @throws ParseException
     */
    private void buildBatch(StorageSpaceMoveSaveVo storageSpaceMoveSaveVo, StorageSpaceMoveGoodsSaveVo storageSpaceMoveGoodsSaveVo, GoodsBatch goodsBatch) throws ParseException {
        GoodsBatch batch = new GoodsBatch();
        batch.setShopId(storageSpaceMoveSaveVo.getShopId());
        batch.setGoodsId(storageSpaceMoveGoodsSaveVo.getGoodsId());
        batch.setSkuId(goodsBatch.getSkuId());
        batch.setBatch(goodsBatch.getBatch());
        batch.setProduceDate(DateTimeUtils.convertStringToDate(goodsBatch.getProduceDateString()));
        batch.setValidDate(DateTimeUtils.convertStringToDate(goodsBatch.getValidDateString()));
        batch.setCurrentStock(storageSpaceMoveGoodsSaveVo.getQuantity());
        batch.setStorageSpaceId(storageSpaceMoveGoodsSaveVo.getTargetStorageSpaceId());
        batch.setSupplierId(goodsBatch.getSupplierId());
        batch.setPinyin(goodsBatch.getPinyin());
        batch.setGoodsCode(goodsBatch.getGoodsCode());
        batch.setGoodsNm(goodsBatch.getGoodsNm());
        batch.setGoodsCommonNm(goodsBatch.getGoodsCommonNm());
        batch.setPurchasePrice(goodsBatch.getPurchasePrice());
        batch.setBatchState(goodsBatch.getBatchState());
        ServiceManager.goodsBatchService.save(batch);
    }

    /**
     * 构建货位移动信息
     *
     * @param storageSpaceMoveSaveVo
     * @param storageSpaceMoveGoodsSaveVo
     * @return
     * @throws ParseException
     */
    private StorageSpaceMove buildStorageSpaceMove(StorageSpaceMoveSaveVo storageSpaceMoveSaveVo, StorageSpaceMoveGoodsSaveVo storageSpaceMoveGoodsSaveVo) throws ParseException {
        StorageSpaceMove storageSpaceMove = new StorageSpaceMove();
        storageSpaceMove.setMoveOrderNum(storageSpaceMoveSaveVo.getMoveNum());
        storageSpaceMove.setApproveManId(storageSpaceMoveSaveVo.getApproveManId());
        if (StringUtils.isNotBlank(storageSpaceMoveSaveVo.getRemark())) {
            storageSpaceMove.setRemark(storageSpaceMoveSaveVo.getRemark());
        }
        storageSpaceMove.setMoveManName(storageSpaceMoveSaveVo.getMoveManName());
        storageSpaceMove.setShopId(storageSpaceMoveSaveVo.getShopId());
        if (StringUtils.isNotBlank(storageSpaceMoveSaveVo.getMoveTimeString())) {
            storageSpaceMove.setMoveTime(DateTimeUtils.convertStringToDate(storageSpaceMoveSaveVo.getMoveTimeString()));
        }
        storageSpaceMove.setBatchId(storageSpaceMoveGoodsSaveVo.getBatchId());
        storageSpaceMove.setSourceStorageSpaceId(storageSpaceMoveGoodsSaveVo.getStorageSpaceId());
        storageSpaceMove.setTargetStorageSpaceId(storageSpaceMoveGoodsSaveVo.getTargetStorageSpaceId());
        storageSpaceMove.setQuantity(storageSpaceMoveGoodsSaveVo.getQuantity());
        storageSpaceMove.setGoodsId(storageSpaceMoveGoodsSaveVo.getGoodsId());
        storageSpaceMove.setSkuId(storageSpaceMoveGoodsSaveVo.getSkuId());
        this.save(storageSpaceMove);
        return storageSpaceMove;
    }

    /**
     * 构建商品批次搜索参数
     *
     * @param goodsBatch
     * @param storageSpaceMoveGoodsSaveVo
     * @return
     */
    private GoodsBatchSimpleSearchVo buildGoodsBatchSimpleSearchVo(GoodsBatch goodsBatch, StorageSpaceMoveGoodsSaveVo storageSpaceMoveGoodsSaveVo) {
        GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo = new GoodsBatchSimpleSearchVo();
        goodsBatchSimpleSearchVo.setBatch(goodsBatch.getBatch());
        goodsBatchSimpleSearchVo.setGoodsId(goodsBatch.getGoodsId());
        goodsBatchSimpleSearchVo.setStorageSpaceId(storageSpaceMoveGoodsSaveVo.getTargetStorageSpaceId());
        goodsBatchSimpleSearchVo.setPurchasePrice(goodsBatch.getPurchasePrice());
        goodsBatchSimpleSearchVo.setSupplierId(goodsBatch.getSupplierId());
        return goodsBatchSimpleSearchVo;
    }

    @Override
    public StorageSpaceMoveNumVo generateMoveOrderNum() {
        StorageSpaceMoveNumVo storageSpaceMoveNumVo = new StorageSpaceMoveNumVo();
        storageSpaceMoveNumVo.setMoveNum(DateSerialGenerator.genSerialCode(DateSerialGenerator.STORAGE_SPACE_MOVE_PREFIX));
        return storageSpaceMoveNumVo;
    }

    /**
     * 保存货位移动出入库日志
     *
     * @param storageSpaceMove
     */
    private void saveOutInStockLog(StorageSpaceMove storageSpaceMove) {
        OutInStockLog outLog = new OutInStockLog();
        OutInStockLog inLog = new OutInStockLog();
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(storageSpaceMove.getBatchId());
        Double moveAmount = BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), storageSpaceMove.getQuantity());
        Double clearingPrevAmount = BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice());
        outLog.setShopId(storageSpaceMove.getShopId());
        outLog.setGoodsId(storageSpaceMove.getGoodsId());
        outLog.setSkuId(storageSpaceMove.getSkuId());
        outLog.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
        outLog.setGoodsBatchId(storageSpaceMove.getBatchId());
        outLog.setStorageSpaceId(storageSpaceMove.getSourceStorageSpaceId());
        outLog.setQuantity(0 - storageSpaceMove.getQuantity());
        outLog.setAmount(0 - moveAmount);
        outLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        outLog.setClearingPrevAmount(clearingPrevAmount);
        outLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.STORAGE_SPACE_MOVE.toCode());
        outLog.setLogSourceObjectId(storageSpaceMove.getId());
        outLog.setObjectOrderNum(storageSpaceMove.getMoveOrderNum());

        inLog.setShopId(storageSpaceMove.getShopId());
        inLog.setGoodsId(storageSpaceMove.getGoodsId());
        inLog.setSkuId(storageSpaceMove.getSkuId());
        inLog.setTypeCode(OutInStockTypeCodeEnum.IN_STOCK.toCode());
        inLog.setGoodsBatchId(storageSpaceMove.getBatchId());
        inLog.setStorageSpaceId(storageSpaceMove.getSourceStorageSpaceId());
        inLog.setQuantity(storageSpaceMove.getQuantity());
        inLog.setAmount(moveAmount);
        inLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        inLog.setClearingPrevAmount(clearingPrevAmount);
        inLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.STORAGE_SPACE_MOVE.toCode());
        inLog.setLogSourceObjectId(storageSpaceMove.getId());
        inLog.setObjectOrderNum(storageSpaceMove.getMoveOrderNum());

        ServiceManager.outInStockLogService.save(outLog);
        ServiceManager.outInStockLogService.save(inLog);
    }

    private StorageSpaceMoveGoodsVo buildStorageSpaceMoveGoodsVo(StorageSpaceMove storageSpaceMove) {
        StorageSpaceMoveGoodsVo storageSpaceMoveGoodsVo = new StorageSpaceMoveGoodsVo();
        Goods goods = ServiceManager.goodsService.findOne(storageSpaceMove.getGoodsId());
        storageSpaceMoveGoodsVo.setGoodsCode(goods.getGoodsCode());
        storageSpaceMoveGoodsVo.setGoodsNm(goods.getGoodsNm());
        storageSpaceMoveGoodsVo.setGoodsCommonNm(goods.getCommonNm());
        storageSpaceMoveGoodsVo.setSpec(goods.getSpec());
        storageSpaceMoveGoodsVo.setUnit(goods.getUnit());
        storageSpaceMoveGoodsVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(storageSpaceMove.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                storageSpaceMoveGoodsVo.setDosageForm(sysDictItem.getDictItemNm());
                storageSpaceMoveGoodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                storageSpaceMoveGoodsVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                storageSpaceMoveGoodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                storageSpaceMoveGoodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                storageSpaceMoveGoodsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                storageSpaceMoveGoodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        StorageSpace sourceStorageSpace = ServiceManager.storageSpaceService.findOne(storageSpaceMove.getSourceStorageSpaceId());
        if (sourceStorageSpace == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"货位"}));
        }
        storageSpaceMoveGoodsVo.setSourceStorageSpaceNm(sourceStorageSpace.getStorageSpaceNm());
        StorageSpace targetStorageSpace = ServiceManager.storageSpaceService.findOne(storageSpaceMove.getTargetStorageSpaceId());
        if (targetStorageSpace == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"货位"}));
        }
        storageSpaceMoveGoodsVo.setTargetStorageSpaceNm(targetStorageSpace.getStorageSpaceNm());
        storageSpaceMoveGoodsVo.setQuantity(storageSpaceMove.getQuantity());
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(storageSpaceMove.getBatchId());
        storageSpaceMoveGoodsVo.setBatch(goodsBatch.getBatch());
        storageSpaceMoveGoodsVo.setCurrentStock(goodsBatch.getCurrentStock());
        storageSpaceMoveGoodsVo.setProduceDateString(goodsBatch.getProduceDateString());
        storageSpaceMoveGoodsVo.setValidDateString(goodsBatch.getValidDateString());
        return storageSpaceMoveGoodsVo;
    }

    /**
     * 构建StorageSpaceMovePageVo对象
     *
     * @param storageSpaceMove
     * @return
     */
    private StorageSpaceMovePageVo buildStorageSpaceMovePageVo(StorageSpaceMove storageSpaceMove) {
        StorageSpaceMovePageVo storageSpaceMovePageVo = CommonUtil.copyProperties(storageSpaceMove, new StorageSpaceMovePageVo());
        storageSpaceMovePageVo.setMoveTimeString(storageSpaceMove.getMoveTimeString());
        return storageSpaceMovePageVo;
    }

}