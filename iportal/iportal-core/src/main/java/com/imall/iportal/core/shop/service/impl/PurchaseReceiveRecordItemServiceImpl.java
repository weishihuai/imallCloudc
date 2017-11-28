package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.PurchaseOrder;
import com.imall.iportal.core.shop.entity.PurchaseReceiveRecordItem;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.repository.PurchaseReceiveRecordItemRepository;
import com.imall.iportal.core.shop.service.PurchaseReceiveRecordItemService;
import com.imall.iportal.core.shop.vo.PurchaseOrderGoodsVo;
import com.imall.iportal.core.shop.vo.PurchaseReceiveRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseReceiveSearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class PurchaseReceiveRecordItemServiceImpl extends AbstractBaseService<PurchaseReceiveRecordItem, Long> implements PurchaseReceiveRecordItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private PurchaseReceiveRecordItemRepository getPurchaseReceiveRecordItemRepository() {
        return (PurchaseReceiveRecordItemRepository) baseRepository;
    }

    @Override
    public List<PurchaseReceiveRecordItem> findByPurchaseReceiveRecordId(Long purchaseReceiveRecordId) {
        return getPurchaseReceiveRecordItemRepository().findByPurchaseReceiveRecordId(purchaseReceiveRecordId);
    }

    @Override
    public List<PurchaseReceiveRecordItem> findByPurchaseReceiveRecordIdAndIsAcceptance(Long purchaseReceiveRecordId, String isAcceptance) {
        return getPurchaseReceiveRecordItemRepository().findByPurchaseReceiveRecordIdAndIsAcceptance(purchaseReceiveRecordId, isAcceptance);
    }

    @Override
    public List<PurchaseReceiveRecordItem> findByPurchaseOrderIdAndIsAcceptance(Long purchaseOrderId, String isAcceptance) {
        return getPurchaseReceiveRecordItemRepository().findByPurchaseOrderIdAndIsAcceptance(purchaseOrderId, BoolCodeEnum.NO.toCode());
    }

    @Override
    public Page<PurchaseReceiveRecordItemPageVo> query(Pageable pageable, PurchaseReceiveSearchParams searchParams) {
        Page<PurchaseReceiveRecordItemPageVo> voPage = getPurchaseReceiveRecordItemRepository().query(pageable, searchParams);
        for (PurchaseReceiveRecordItemPageVo vo : voPage.getContent()){
            Supplier supplier = ServiceManager.supplierService.findOne(vo.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(vo.getGoodsId());
            CommonUtil.copyProperties(goodsVo, vo);
            PurchaseOrder purchaseOrder = ServiceManager.purchaseOrderService.findOne(vo.getPurchaseOrderId());
            vo.setPurchaseOrderNum(purchaseOrder.getPurchaseOrderNum());
            vo.setPurchaseOrderState(purchaseOrder.getPurchaseOrderState());
        }
        return voPage;
    }
}