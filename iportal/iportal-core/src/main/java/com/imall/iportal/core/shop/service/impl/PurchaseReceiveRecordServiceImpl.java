package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.GeneratePurchaseNumUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.PurchaseOrderStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.PurchaseReceiveRecordRepository;
import com.imall.iportal.core.shop.service.PurchaseReceiveRecordService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.collections.CollectionUtils;
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
public class PurchaseReceiveRecordServiceImpl extends AbstractBaseService<PurchaseReceiveRecord, Long> implements PurchaseReceiveRecordService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private PurchaseReceiveRecordRepository getPurchaseReceiveRecordRepository() {
        return (PurchaseReceiveRecordRepository) baseRepository;
    }

    @Override
    public PurchaseReceivePrintTemplateVo getPurchaseReceivePrintTemplateVoByIdAndShopId(Long id, Long shopId) {
        PurchaseReceiveRecord receiveRecord = getPurchaseReceiveRecordRepository().findByIdAndShopId(id, shopId);
        PurchaseReceivePrintTemplateVo templateVo = new PurchaseReceivePrintTemplateVo();
        if (receiveRecord == null) {
            return templateVo;
        }
        CommonUtil.copyProperties(receiveRecord, templateVo);
        templateVo.setDayOfWeek(DateTimeUtils.getDayOfWeekStr(receiveRecord.getCreateDate()));
        Supplier supplier = ServiceManager.supplierService.findOne(receiveRecord.getSupplierId());
        templateVo.setSupplierNm(supplier.getSupplierNm());
        List<PurchaseReceiveRecordItem> recordItemList = ServiceManager.purchaseReceiveRecordItemService.findByPurchaseReceiveRecordId(id);
        List<PurchaseReceivePrintTemplateVo.PurchaseReceiveItemPrintVo> itemPrintVos = new ArrayList<>();
        for (PurchaseReceiveRecordItem recordItem : recordItemList) {
            itemPrintVos.add(this.buildItemPrintVo(recordItem));
        }
        templateVo.setItemPrintVos(itemPrintVos);
        return templateVo;
    }

    private PurchaseReceivePrintTemplateVo.PurchaseReceiveItemPrintVo buildItemPrintVo(PurchaseReceiveRecordItem recordItem) {
        PurchaseReceivePrintTemplateVo.PurchaseReceiveItemPrintVo printVo = new PurchaseReceivePrintTemplateVo.PurchaseReceiveItemPrintVo();
        printVo.setPurchaseQuantity(recordItem.getPurchaseQuantity());
        printVo.setReceiveQuantity(recordItem.getReceiveQuantity());
        Long goodsId = recordItem.getGoodsId();
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        printVo.setGoodsCode(goods.getGoodsCode());
        printVo.setCommonNm(goods.getCommonNm());
        printVo.setSpec(goods.getSpec());
        printVo.setUnit(goods.getUnit());
        printVo.setProduceManufacturer(goods.getProduceManufacturer());
        String formCode = ServiceManager.goodsService.getDosageFormByGoodsIdAndGoodsTypeCode(goodsId, goods.getGoodsTypeCode());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(formCode)){
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(formCode);
            printVo.setDosageForm(sysDictItem == null ? "" : sysDictItem.getDictItemNm());
        }
        return printVo;
    }

    @Override
    @Transactional
    public Long saveReceiveRecord(PurchaseReceiveRecordSaveVo saveVo) throws ParseException {
        Long shopId = saveVo.getShopId();
        Long purchaseOrderId = saveVo.getPurchaseOrderId();
        PurchaseOrder purchaseOrder = ServiceManager.purchaseOrderService.findPurchaseOrder(purchaseOrderId, shopId);
        if (purchaseOrder == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"采购订单"}));
        }
        Long supplierId = purchaseOrder.getSupplierId();
        //保存收货记录
        PurchaseReceiveRecord purchaseReceiveRecord = new PurchaseReceiveRecord();
        purchaseReceiveRecord.setShopId(shopId);
        purchaseReceiveRecord.setSupplierId(supplierId);
        purchaseReceiveRecord.setPurchaseOrderId(purchaseOrderId);
        purchaseReceiveRecord.setReceiver(saveVo.getReceiver());
        purchaseReceiveRecord.setIsAllAcceptance(BoolCodeEnum.NO.toCode());
        if (StringUtils.isBlank(saveVo.getReceiveTimeString())) {
            purchaseReceiveRecord.setReceiveTime(new Date());
        } else {
            purchaseReceiveRecord.setReceiveTimeString(saveVo.getReceiveTimeString());
        }
        purchaseReceiveRecord.setRemark(saveVo.getRemark());
        //生成收货编号
        Date now = new Date();
        Long count = getPurchaseReceiveRecordRepository().countByCreateDate(DateTimeUtils.getDayStartTime(now), DateTimeUtils.getDayEndTime(now), shopId);
        purchaseReceiveRecord.setReceiveOrderNum(GeneratePurchaseNumUtil.generateNum(GeneratePurchaseNumUtil.RECEIVE_ORDER_PREFIX, shopId, count + 1));
        Long purchaseReceiveRecordId = this.save(purchaseReceiveRecord).getId();

        //保存收货记录项
        for (PurchaseReceiveRecordItemSaveVo itemSaveVo : saveVo.getItemList()) {
            Long goodsId = itemSaveVo.getGoodsId();
            PurchaseOrderItem purchaseOrderItem = ServiceManager.purchaseOrderItemService.findByPurchaseOrderIdAndGoodsId(purchaseOrderId, goodsId);
            if (purchaseOrderItem == null) {
                throw new BusinessException(ResGlobal.COMMON_ERROR, "采购订单没有采购商品【id=" + goodsId + "】");
            }
            if (itemSaveVo.getReceiveQuantity() > purchaseOrderItem.getPurchaseQuantity()) {
                throw new BusinessException(ResGlobal.COMMON_ERROR, "收货数量大于采购数量");
            }
            PurchaseReceiveRecordItem recordItem = new PurchaseReceiveRecordItem();
            recordItem.setPurchaseReceiveRecordId(purchaseReceiveRecordId);
            recordItem.setPurchaseOrderId(purchaseOrderId);
            recordItem.setShopId(shopId);
            recordItem.setSupplierId(supplierId);
            recordItem.setGoodsId(goodsId);
            recordItem.setPurchaseUnitPrice(purchaseOrderItem.getPurchaseUnitPrice());
            recordItem.setPurchaseQuantity(purchaseOrderItem.getPurchaseQuantity());
            recordItem.setReceiveQuantity(itemSaveVo.getReceiveQuantity());
            recordItem.setIsAcceptance(BoolCodeEnum.NO.toCode());
            recordItem.setRejectionReason(itemSaveVo.getRejectionReason());
            ServiceManager.purchaseReceiveRecordItemService.save(recordItem);
            //更新采购订单项为已收货
            purchaseOrderItem.setIsReceive(BoolCodeEnum.YES.toCode());
            ServiceManager.purchaseOrderItemService.save(purchaseOrderItem);
        }
        //如果采购订单项已全部收货，更新采购订单状态为待验收
        if (ServiceManager.purchaseOrderItemService.checkIsAllReceive(purchaseOrderId)) {
            purchaseOrder.setPurchaseOrderState(PurchaseOrderStateCodeEnum.WAIT_ACCEPTANCE.toCode());
            ServiceManager.purchaseOrderService.save(purchaseOrder);
        }
        return purchaseReceiveRecordId;
    }

    @Override
    public List<Long> getIdListByPurchaseOrderId(Long purchaseOrderId) {
        return getPurchaseReceiveRecordRepository().getIdListByPurchaseOrderId(purchaseOrderId);
    }

    @Override
    public Long countByCreateDate(Date start, Date end, Long shopId) {
        return getPurchaseReceiveRecordRepository().countByCreateDate(start, end, shopId);
    }

    @Override
    public PurchaseReceiveRecordVo findEnableAcceptanceReceiveRecord(Long id, Long shopId) {
        PurchaseReceiveRecord record = getPurchaseReceiveRecordRepository().findByIdAndShopId(id, shopId);
        if (record == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"收货单"}));
        }
        PurchaseReceiveRecordVo recordVo = CommonUtil.copyProperties(record, new PurchaseReceiveRecordVo());
        //供应商信息
        Supplier supplier = ServiceManager.supplierService.findOne(record.getSupplierId());
        recordVo.setSupplierNm(supplier.getSupplierNm());
        recordVo.setSupplierCode(supplier.getSupplierCode());
        recordVo.setStorageSpaceList(ServiceManager.storageSpaceService.listForGoodsList(shopId));
        List<PurchaseReceiveRecordItemVo> itemVoList = new ArrayList<>();
        List<PurchaseReceiveRecordItem> itemList = ServiceManager.purchaseReceiveRecordItemService.findByPurchaseReceiveRecordIdAndIsAcceptance(id, BoolCodeEnum.NO.toCode());
        for (PurchaseReceiveRecordItem item : itemList) {
            itemVoList.add(this.buildPurchaseReceiveRecordItemVo(item));
        }
        recordVo.setItemList(itemVoList);
        return recordVo;
    }

    @Override
    public Page<PurchaseReceiveRecordPageVo> query(Pageable pageable, PurchaseReceiveSearchParams searchParam) {
        Page<PurchaseReceiveRecord> recordPage = getPurchaseReceiveRecordRepository().query(pageable, searchParam);
        List<PurchaseReceiveRecordPageVo> voList = new ArrayList<>();
        for (PurchaseReceiveRecord receiveRecord : recordPage.getContent()) {
            PurchaseReceiveRecordPageVo vo = CommonUtil.copyProperties(receiveRecord, new PurchaseReceiveRecordPageVo());
            Supplier supplier = ServiceManager.supplierService.findOne(receiveRecord.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            PurchaseOrder purchaseOrder = ServiceManager.purchaseOrderService.findOne(receiveRecord.getPurchaseOrderId());
            vo.setPurchaseOrderState(purchaseOrder.getPurchaseOrderState());
            vo.setPurchaseOrderNum(purchaseOrder.getPurchaseOrderNum());
            vo.setRemark(purchaseOrder.getRemark());
            vo.setPurchaseTotalAmount(purchaseOrder.getPurchaseTotalAmount());
            voList.add(vo);
        }
        return new PageImpl<PurchaseReceiveRecordPageVo>(voList, pageable, recordPage.getTotalElements());
    }

    @Override
    public PurchaseReceiveRecord getByIdAndShopId(Long id, Long shopId) {
        return getPurchaseReceiveRecordRepository().findByIdAndShopId(id, shopId);
    }

    @Override
    public PurchaseReceiveRecordVo findByIdAndShopId(Long id, Long shopId) {
        PurchaseReceiveRecord record = getPurchaseReceiveRecordRepository().findByIdAndShopId(id, shopId);
        if (record == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"收货单"}));
        }
        PurchaseReceiveRecordVo recordVo = CommonUtil.copyProperties(record, new PurchaseReceiveRecordVo());
        //供应商信息
        Supplier supplier = ServiceManager.supplierService.findOne(record.getSupplierId());
        recordVo.setSupplierNm(supplier.getSupplierNm());
        recordVo.setSupplierCode(supplier.getSupplierCode());
        recordVo.setStorageSpaceList(ServiceManager.storageSpaceService.listForGoodsList(shopId));
        List<PurchaseReceiveRecordItemVo> itemVoList = new ArrayList<>();
        List<PurchaseReceiveRecordItem> itemList = ServiceManager.purchaseReceiveRecordItemService.findByPurchaseReceiveRecordId(id);
        for (PurchaseReceiveRecordItem item : itemList) {
            itemVoList.add(this.buildPurchaseReceiveRecordItemVo(item));
        }
        recordVo.setItemList(itemVoList);
        return recordVo;
    }

    private PurchaseReceiveRecordItemVo buildPurchaseReceiveRecordItemVo(PurchaseReceiveRecordItem item){
        Long goodsId = item.getGoodsId();
        PurchaseReceiveRecordItemVo itemVo = CommonUtil.copyProperties(item, new PurchaseReceiveRecordItemVo());
        PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(goodsId);
        CommonUtil.copyProperties(goodsVo, itemVo);
        Sku sku = ServiceManager.skuService.findByGoodsId(goodsId);
        itemVo.setRetailPrice(sku.getRetailPrice());
        List<GoodsBatchSimpleVo> voList = ServiceManager.goodsBatchService.findGoodsBatchByGoodsId(goodsId);
        Map<String, GoodsBatchSimpleVo> map = new HashMap<>();
        for (GoodsBatchSimpleVo vo : voList){
            map.put(vo.getBatch(), vo);
        }
        List<GoodsBatchSimpleVo> simpleVoList = new ArrayList<>();
        for (String key : map.keySet()){
            simpleVoList.add(map.get(key));
        }
        itemVo.setGoodsBatchList(simpleVoList);
        return itemVo;
    }
}