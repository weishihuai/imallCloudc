package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.PurchaseOrderItem;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.repository.PurchaseOrderItemRepository;
import com.imall.iportal.core.shop.service.PurchaseOrderItemService;
import com.imall.iportal.core.shop.vo.PurchaseOrderGoodsVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderSearchParams;
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
public class PurchaseOrderItemServiceImpl extends AbstractBaseService<PurchaseOrderItem, Long> implements PurchaseOrderItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private PurchaseOrderItemRepository getPurchaseOrderItemRepository() {
        return (PurchaseOrderItemRepository) baseRepository;
    }

    @Override
    public List<PurchaseOrderItem> findByPurchaseOrderId(Long purchaseOrderId) {
        return getPurchaseOrderItemRepository().findByPurchaseOrderId(purchaseOrderId);
    }

    @Override
    public PurchaseOrderItem findByPurchaseOrderIdAndGoodsId(Long purchaseOrderId, Long goodsId) {
        List<PurchaseOrderItem> list = getPurchaseOrderItemRepository().findByPurchaseOrderIdAndGoodsId(purchaseOrderId, goodsId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Boolean checkIsAllReceive(Long purchaseOrderId) {
        List<PurchaseOrderItem> list = getPurchaseOrderItemRepository().findByPurchaseOrderIdAndIsReceive(purchaseOrderId, BoolCodeEnum.NO.toCode());
        return list.isEmpty();
    }

    @Override
    public List<PurchaseOrderItem> findByPurchaseOrderIdAndIsReceive(Long purchaseOrderId, String isReceive) {
        return getPurchaseOrderItemRepository().findByPurchaseOrderIdAndIsReceive(purchaseOrderId, BoolCodeEnum.NO.toCode());
    }

    @Override
    public Page<PurchaseOrderItemPageVo> query(Pageable pageable, PurchaseOrderSearchParams searchParams) {
        Page<PurchaseOrderItemPageVo> voPage = getPurchaseOrderItemRepository().query(pageable, searchParams);
        for (PurchaseOrderItemPageVo vo : voPage.getContent()){
            Supplier supplier = ServiceManager.supplierService.findOne(vo.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(vo.getGoodsId());
            CommonUtil.copyProperties(goodsVo, vo);
        }
        return voPage;
    }
}