package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.GeneratePurchaseNumUtil;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.PurchaseAcceptanceRecordRepository;
import com.imall.iportal.core.shop.service.PurchaseAcceptanceRecordService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class PurchaseAcceptanceRecordServiceImpl extends AbstractBaseService<PurchaseAcceptanceRecord, Long> implements PurchaseAcceptanceRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private PurchaseAcceptanceRecordRepository getPurchaseAcceptanceRecordRepository() {
        return (PurchaseAcceptanceRecordRepository) baseRepository;
    }

    @Override
    public PurchaseAcceptancePrintTemplateVo getPurchaseAcceptancePrintTemplateVoByIdAndShopId(Long id, Long shopId) {
        PurchaseAcceptanceRecord acceptanceRecord = getPurchaseAcceptanceRecordRepository().findByIdAndShopId(id, shopId);
        PurchaseAcceptancePrintTemplateVo templateVo = new PurchaseAcceptancePrintTemplateVo();
        if (acceptanceRecord == null) {
            return templateVo;
        }
        CommonUtil.copyProperties(acceptanceRecord, templateVo);
        templateVo.setDayOfWeek(DateTimeUtils.getDayOfWeekStr(acceptanceRecord.getCreateDate()));
        Supplier supplier = ServiceManager.supplierService.findOne(acceptanceRecord.getSupplierId());
        templateVo.setSupplierNm(supplier.getSupplierNm());
        List<PurchaseAcceptanceRecordItem> itemList = ServiceManager.purchaseAcceptanceRecordItemService.findByPurchaseAcceptanceRecordId(id);
        List<PurchaseAcceptancePrintTemplateVo.PurchaseAcceptanceItemPrintVo> itemPrintVos = new ArrayList<>();
        for (PurchaseAcceptanceRecordItem recordItem : itemList) {
            itemPrintVos.add(this.buildPrintVo(recordItem));
        }
        templateVo.setItemPrintVos(itemPrintVos);
        return templateVo;
    }

    @Override
    @Transactional
    public Long saveAcceptanceRecord(PurchaseAcceptanceRecordSaveVo saveVo) throws ParseException {
        Long receiveRecordId = saveVo.getPurchaseReceiveRecordId();
        Long shopId = saveVo.getShopId();
        PurchaseReceiveRecord receiveRecord = ServiceManager.purchaseReceiveRecordService.getByIdAndShopId(receiveRecordId, shopId);
        if (receiveRecord == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"收货单"}));
        }
        //保存验收记录
        Long supplierId = receiveRecord.getSupplierId();
        Long purchaseOrderId = receiveRecord.getPurchaseOrderId();
        PurchaseAcceptanceRecord acceptanceRecord = new PurchaseAcceptanceRecord();
        acceptanceRecord.setApproveManId(saveVo.getApproveManId());
        acceptanceRecord.setShopId(shopId);
        acceptanceRecord.setAcceptanceMan(saveVo.getAcceptanceMan());
        if (StringUtils.isBlank(saveVo.getAcceptanceTimeString())){
            acceptanceRecord.setAcceptanceTime(new Date());
        }else {
            acceptanceRecord.setAcceptanceTimeString(saveVo.getAcceptanceTimeString());
        }
        acceptanceRecord.setDocMaker(saveVo.getDocMaker());
        acceptanceRecord.setPurchaseReceiveRecordId(saveVo.getPurchaseReceiveRecordId());
        acceptanceRecord.setRemark(saveVo.getRemark());
        acceptanceRecord.setPurchaseOrderId(purchaseOrderId);
        acceptanceRecord.setIsPayed(BoolCodeEnum.NO.toCode());
        acceptanceRecord.setSupplierId(supplierId);
        if (StringUtils.isBlank(saveVo.getAcceptanceTimeString())){
            acceptanceRecord.setAcceptanceTime(new Date());
        }
        acceptanceRecord.setAcceptanceTotalAmount(0D);
        //生成验收单号
        Date now = new Date();
        Long count = getPurchaseAcceptanceRecordRepository().countByCreateDate(DateTimeUtils.getDayStartTime(now), DateTimeUtils.getDayEndTime(now), shopId);
        acceptanceRecord.setAcceptanceOrderNum(GeneratePurchaseNumUtil.generateNum(GeneratePurchaseNumUtil.ACCEPTANCE_ORDER_PREFIX, shopId, count + 1));
        Long acceptanceRecordId = this.save(acceptanceRecord).getId();
        //保存验收项，由于验收的时候，同个商品可以分多个批次，所以需要检查商品的总到货数量,以免超过彩果收货时的数量
        Map<Long, Long> goodsArrivalMap = new HashMap<>();
        Map<Long, Long> sampleMap = new HashMap<>();
        Map<Long, Long> qualifiedMap = new HashMap<>();

        Double acceptanceTotalAmount = 0D;
        for (PurchaseAcceptanceRecordItemSaveVo itemSaveVo : saveVo.getItemList()) {
            PurchaseAcceptanceRecordItem acceptanceRecordItem = this.saveAcceptanceRecordItem(goodsArrivalMap, sampleMap, qualifiedMap, itemSaveVo, acceptanceRecordId);
            acceptanceTotalAmount = BigDecimalUtil.add(acceptanceTotalAmount, BigDecimalUtil.multiply(acceptanceRecordItem.getGoodsArrivalQuantity(), acceptanceRecordItem.getPurchaseUnitPrice()));
        }
        //验收总金额
        acceptanceRecord.setAcceptanceTotalAmount(acceptanceTotalAmount);
        this.save(acceptanceRecord);
        //更新收货相关信息
        this.updateReceiveMsg(receiveRecord);
        return acceptanceRecordId;
    }

    //更新收货记录信息
    private void updateReceiveMsg(PurchaseReceiveRecord receiveRecord){
        //检查是否已全部验收，更新采购订单状态
        List<PurchaseReceiveRecordItem> recordItemList = ServiceManager.purchaseReceiveRecordItemService.findByPurchaseOrderIdAndIsAcceptance(receiveRecord.getPurchaseOrderId(), BoolCodeEnum.NO.toCode()) ;
        if (recordItemList.isEmpty()) {
            PurchaseOrder purchaseOrder = ServiceManager.purchaseOrderService.findOne(receiveRecord.getPurchaseOrderId());
            purchaseOrder.setPurchaseOrderState(PurchaseOrderStateCodeEnum.CLEAR.toCode());
            purchaseOrder.setClearingTime(new Date());
            ServiceManager.purchaseOrderService.save(purchaseOrder);
            //更新采购收货记录字段isAllAcceptance为Y
            receiveRecord.setIsAllAcceptance(BoolCodeEnum.YES.toCode());
            ServiceManager.purchaseReceiveRecordService.save(receiveRecord);
        }else {
            recordItemList = ServiceManager.purchaseReceiveRecordItemService.findByPurchaseReceiveRecordIdAndIsAcceptance(receiveRecord.getId(), BoolCodeEnum.NO.toCode());
            if (recordItemList.isEmpty()){
                receiveRecord.setIsAllAcceptance(BoolCodeEnum.YES.toCode());
                ServiceManager.purchaseReceiveRecordService.save(receiveRecord);
            }
        }
    }

    @Override
    public Long countByCreateDate(Date start, Date end, Long shopId) {
        return getPurchaseAcceptanceRecordRepository().countByCreateDate(start, end, shopId);
    }

    private PurchaseAcceptanceRecordItem saveAcceptanceRecordItem(Map<Long, Long> goodsArrivalMap, Map<Long, Long> sampleMap, Map<Long, Long> qualifiedMap, PurchaseAcceptanceRecordItemSaveVo itemSaveVo, Long acceptanceRecordId) {
        Long goodsId = itemSaveVo.getGoodsId();
        PurchaseReceiveRecordItem receiveRecordItem = ServiceManager.purchaseReceiveRecordItemService.findOne(itemSaveVo.getPurchaseReceiveRecordItemId());
        if (receiveRecordItem == null) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "采购收货记录项");
        }
        goodsArrivalMap.put(goodsId, goodsArrivalMap.containsKey(goodsId) ? goodsArrivalMap.get(goodsId) + itemSaveVo.getGoodsArrivalQuantity() : itemSaveVo.getGoodsArrivalQuantity());
        sampleMap.put(goodsId, sampleMap.containsKey(goodsId) ? sampleMap.get(goodsId) + itemSaveVo.getSampleQuantity() : itemSaveVo.getSampleQuantity());
        qualifiedMap.put(goodsId, qualifiedMap.containsKey(goodsId) ? qualifiedMap.get(goodsId) + itemSaveVo.getQualifiedQuantity() : itemSaveVo.getQualifiedQuantity());
        //更新收货记录项：是否已验收字段
        receiveRecordItem.setIsAcceptance(BoolCodeEnum.YES.toCode());
        ServiceManager.purchaseReceiveRecordItemService.save(receiveRecordItem);
        //保存验收项
        PurchaseAcceptanceRecordItem acceptanceRecordItem = CommonUtil.copyProperties(itemSaveVo, new PurchaseAcceptanceRecordItem());
        Double purchaseUnitPrice = receiveRecordItem.getPurchaseUnitPrice();//采购单价
        Long totalGoodsArrivalQuantity = goodsArrivalMap.get(goodsId);//到货数量
        Long totalSampleQuantity = sampleMap.get(goodsId);//抽样数量
        Long totalQualifiedQuantity = qualifiedMap.get(goodsId);//合格数量
        if (totalGoodsArrivalQuantity > receiveRecordItem.getReceiveQuantity()){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品到货数量不能大于采购收货时的数量");
        }
        if (totalQualifiedQuantity > totalGoodsArrivalQuantity){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "合格数量不能大于到货数量");
        }
        if (totalSampleQuantity > totalGoodsArrivalQuantity){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "抽样数量不能大于到货数量");
        }
        acceptanceRecordItem.setInStorageQuantity(itemSaveVo.getGoodsArrivalQuantity());//入库数量 = 到货数量
        acceptanceRecordItem.setRejectionQuantity(itemSaveVo.getGoodsArrivalQuantity() - itemSaveVo.getQualifiedQuantity());//拒收数量 = 到货数量 - 合格数量
        acceptanceRecordItem.setAcceptanceQualifiedAmount(BigDecimalUtil.multiply(itemSaveVo.getQualifiedQuantity(), purchaseUnitPrice));//验收合格金额 = 验收合格数量*采购单价
        acceptanceRecordItem.setTotalAmount(BigDecimalUtil.multiply(itemSaveVo.getGoodsArrivalQuantity(), purchaseUnitPrice));//总金额
        acceptanceRecordItem.setShopId(receiveRecordItem.getShopId());
        acceptanceRecordItem.setSupplierId(receiveRecordItem.getSupplierId());
        acceptanceRecordItem.setGoodsId(goodsId);
        acceptanceRecordItem.setPurchaseAcceptanceRecordId(acceptanceRecordId);
        acceptanceRecordItem.setPurchaseUnitPrice(purchaseUnitPrice);
        acceptanceRecordItem.setReturnableQuantity(itemSaveVo.getQualifiedQuantity());//可退数量=验收合格数量
        //商品信息
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        acceptanceRecordItem.setGoodsCode(goods.getGoodsCode());
        acceptanceRecordItem.setGoodsNm(goods.getGoodsNm());
        acceptanceRecordItem.setProduceManufacturer(goods.getProduceManufacturer());
        //检查批号
        Long goodsBatchId = this.checkGoodsBatch(acceptanceRecordItem, itemSaveVo.getGoodsBatchId());
        acceptanceRecordItem.setGoodsBatchId(goodsBatchId);
        ServiceManager.purchaseAcceptanceRecordItemService.save(acceptanceRecordItem);
        return acceptanceRecordItem;
    }

    //检查修改商品批号并保存验收数量进库存
    @Override
    @Transactional
    public Long checkGoodsBatch(PurchaseAcceptanceRecordItem recordItem, Long goodsBatchId) {
        GoodsBatch goodsBatch;
        Long clearingPrevQuantity = 0L;
        Double clearingPrevAmount = 0D;
        if (goodsBatchId == null) {
            goodsBatch = this.buildGoodsBatch(recordItem);
            ServiceManager.goodsBatchService.save(goodsBatch);
        } else {
            goodsBatch = ServiceManager.goodsBatchService.findOne(goodsBatchId);
            clearingPrevQuantity = goodsBatch.getCurrentStock();
            clearingPrevAmount = BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice());
            //如果批次相同，货位不用，就再生成一条记录
            GoodsBatchSimpleSearchVo simpleSearchVo = new GoodsBatchSimpleSearchVo();
            simpleSearchVo.setBatch(recordItem.getGoodsBatch());
            simpleSearchVo.setGoodsId(recordItem.getGoodsId());
            simpleSearchVo.setPurchasePrice(recordItem.getPurchaseUnitPrice());
            simpleSearchVo.setStorageSpaceId(recordItem.getStorageSpaceId());
            simpleSearchVo.setSupplierId(recordItem.getSupplierId());
            goodsBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(simpleSearchVo, GoodsBatchStateCodeEnum.NORMAL.toCode());
            if (goodsBatch == null){
                goodsBatch = this.buildGoodsBatch(recordItem);
            } else {
                goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() + recordItem.getQualifiedQuantity());
            }
            ServiceManager.goodsBatchService.save(goodsBatch);
        }

        //保存出入库记录
        OutInStockLog log = new OutInStockLog();
        log.setShopId(goodsBatch.getShopId());
        log.setGoodsId(goodsBatch.getGoodsId());
        log.setSkuId(goodsBatch.getSkuId());
        log.setTypeCode(OutInStockTypeCodeEnum.IN_STOCK.toCode());
        log.setGoodsBatchId(goodsBatch.getId());
        log.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        log.setQuantity(recordItem.getInStorageQuantity());
        log.setAmount(recordItem.getAcceptanceQualifiedAmount());
        log.setClearingPrevQuantity(clearingPrevQuantity);
        log.setClearingPrevAmount(clearingPrevAmount);
        log.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.PURCHASE_IN_STOCK.toCode());
        PurchaseAcceptanceRecord acceptanceRecord = ServiceManager.purchaseAcceptanceRecordService.findOne(recordItem.getPurchaseAcceptanceRecordId());
        log.setLogSourceObjectId(acceptanceRecord.getId());     //验收单ID
        log.setObjectOrderNum(acceptanceRecord.getAcceptanceOrderNum());    //验收单号
        ServiceManager.outInStockLogService.save(log);
        //如果不合格数量大于0，则生成一条不合格药品处理记录
        if (recordItem.getRejectionQuantity() > 0){
            this.saveDisqualificationDrugProcessRecord(recordItem, goodsBatch);
        }
        return goodsBatch.getId();
    }

    private GoodsBatch buildGoodsBatch(PurchaseAcceptanceRecordItem recordItem){
        GoodsBatch goodsBatch = new GoodsBatch();
        goodsBatch.setBatch(recordItem.getGoodsBatch());
        goodsBatch.setShopId(recordItem.getShopId());
        goodsBatch.setGoodsId(recordItem.getGoodsId());
        goodsBatch.setStorageSpaceId(recordItem.getStorageSpaceId());
        goodsBatch.setProduceDate(recordItem.getProductionDate());
        goodsBatch.setValidDate(recordItem.getValidity());
        Goods goods = ServiceManager.goodsService.findOne(recordItem.getGoodsId());
        goodsBatch.setGoodsNm(goods.getGoodsNm());
        goodsBatch.setGoodsCommonNm(goods.getCommonNm());
        goodsBatch.setGoodsCode(goods.getGoodsCode());
        goodsBatch.setPinyin(goods.getPinyin());
        goodsBatch.setBatchState(GoodsBatchStateCodeEnum.NORMAL.toCode());
        goodsBatch.setCurrentStock(recordItem.getQualifiedQuantity());
        goodsBatch.setPurchasePrice(recordItem.getPurchaseUnitPrice());
        goodsBatch.setSupplierId(recordItem.getSupplierId());
        goodsBatch.setSkuId(ServiceManager.skuService.findByGoodsId(goods.getId()).getId());
        return goodsBatch;
    }

    /**
     * 保存不合格药品处理记录
     * @param recordItem
     * @param goodsBatch
     */
    private void saveDisqualificationDrugProcessRecord(PurchaseAcceptanceRecordItem recordItem, GoodsBatch goodsBatch){
        PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(recordItem.getGoodsId());
        DisqualificationDrugProcessRecord processRecord = CommonUtil.copyProperties(goodsVo, new DisqualificationDrugProcessRecord());
        processRecord.setGoodsPinyin(goodsVo.getPinyin());
        processRecord.setBatch(goodsBatch.getBatch());
        processRecord.setValidDate(goodsBatch.getValidDate());
        processRecord.setDocumentType("采购退货单");
        processRecord.setProcessMeasure("退货");
        processRecord.setQuantity(recordItem.getRejectionQuantity());
        processRecord.setRecordDate(new Date());
        processRecord.setShopId(recordItem.getShopId());
        ServiceManager.disqualificationDrugProcessRecordService.save(processRecord);
    }

    @Override
    public Page<FastReceivePageVo> queryFastReceive(Pageable pageable, PurchaseAcceptanceRecordSearchParams searchParams) {
        Page<PurchaseAcceptanceRecord> recordPage = getPurchaseAcceptanceRecordRepository().query(pageable, searchParams);
        List<FastReceivePageVo> voList = new ArrayList<>();
        for (PurchaseAcceptanceRecord record : recordPage.getContent()){
            FastReceivePageVo vo = CommonUtil.copyProperties(record, new FastReceivePageVo());
            vo.setInStorageTimeString(DateTimeUtils.convertDateToString(record.getCreateDate()));
            Supplier supplier = ServiceManager.supplierService.findOne(record.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            vo.setPurchaseAcceptanceRecordId(record.getId());
            voList.add(vo);
        }
        return new PageImpl<FastReceivePageVo>(voList, pageable, recordPage.getTotalElements());
    }

    @Override
    public FastReceiveVo getDetail(Long id, Long shopId) {
        PurchaseAcceptanceRecord acceptanceRecord = getPurchaseAcceptanceRecordRepository().findByIdAndShopId(id, shopId);
        if (acceptanceRecord == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"验收单"}));
        }
        FastReceiveVo receiveVo = CommonUtil.copyProperties(acceptanceRecord, new FastReceiveVo());
        //采购订单信息
        PurchaseOrder purchaseOrder = ServiceManager.purchaseOrderService.findOne(acceptanceRecord.getPurchaseOrderId());
        receiveVo.setPurchaseMan(purchaseOrder.getPurchaseMan());
        receiveVo.setOrderCreateTimeString(DateTimeUtils.convertDateTimeToMmString(purchaseOrder.getClearingTime()));
        //供应商信息
        Supplier supplier = ServiceManager.supplierService.findOne(acceptanceRecord.getSupplierId());
        receiveVo.setSupplierNm(supplier.getSupplierNm());
        receiveVo.setSupplierCode(supplier.getSupplierCode());
        ///收货信息
        PurchaseReceiveRecord receiveRecord = ServiceManager.purchaseReceiveRecordService.findOne(acceptanceRecord.getPurchaseReceiveRecordId());
        receiveVo.setReceiver(receiveRecord.getReceiver());
        receiveVo.setReceiveTimeString(receiveRecord.getReceiveTimeString());
        //收货验收项
        List<FastReceiveItemVo> itemList = new ArrayList<>();
        List<PurchaseAcceptanceRecordItem> list = ServiceManager.purchaseAcceptanceRecordItemService.findByPurchaseAcceptanceRecordId(id);
        for (PurchaseAcceptanceRecordItem item : list){
            FastReceiveItemVo itemVo = CommonUtil.copyProperties(item, new FastReceiveItemVo());
            PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(item.getGoodsId());
            CommonUtil.copyProperties(goodsVo, itemVo);
            StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(item.getStorageSpaceId());
            itemVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
            itemList.add(itemVo);
        }
        receiveVo.setItemList(itemList);
        return receiveVo;
    }

    @Override
    public Page<PurchaseAcceptanceRecordPageVo> query(Pageable pageable, PurchaseAcceptanceRecordSearchParams searchParams) {
        Page<PurchaseAcceptanceRecord> recordPage = getPurchaseAcceptanceRecordRepository().query(pageable, searchParams);
        List<PurchaseAcceptanceRecordPageVo> voList = new ArrayList<>();
        for (PurchaseAcceptanceRecord record : recordPage.getContent()){
            PurchaseAcceptanceRecordPageVo vo = CommonUtil.copyProperties(record, new PurchaseAcceptanceRecordPageVo());
            Supplier supplier = ServiceManager.supplierService.findOne(record.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            PurchaseOrder purchaseOrder = ServiceManager.purchaseOrderService.findOne(record.getPurchaseOrderId());
            PurchaseReceiveRecord receiveRecord = ServiceManager.purchaseReceiveRecordService.findOne(record.getPurchaseReceiveRecordId());
            vo.setPurchaseOrderState(purchaseOrder.getPurchaseOrderState());
            vo.setInStorageTimeString(DateTimeUtils.convertDateToString(receiveRecord.getReceiveTime()));
            voList.add(vo);
        }
        return new PageImpl<PurchaseAcceptanceRecordPageVo>(voList, pageable, recordPage.getTotalElements());
    }

    private PurchaseAcceptancePrintTemplateVo.PurchaseAcceptanceItemPrintVo buildPrintVo(PurchaseAcceptanceRecordItem recordItem) {
        PurchaseAcceptancePrintTemplateVo.PurchaseAcceptanceItemPrintVo printVo = CommonUtil.copyProperties(recordItem, new PurchaseAcceptancePrintTemplateVo.PurchaseAcceptanceItemPrintVo());
        Long goodsId = recordItem.getGoodsId();
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        printVo.setGoodsCode(goods.getGoodsCode());
        printVo.setCommonNm(goods.getCommonNm());
        printVo.setSpec(goods.getSpec());
        printVo.setUnit(goods.getUnit());
        printVo.setProduceManufacturer(goods.getProduceManufacturer());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(recordItem.getStorageSpaceId());
        printVo.setGoodsAllocation(storageSpace == null ? "" : storageSpace.getStorageSpaceNm());
        return printVo;
    }
}