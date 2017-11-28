package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.GoodsApproveStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.entity.Sku;
import com.imall.iportal.core.shop.repository.SkuRepository;
import com.imall.iportal.core.shop.service.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SkuServiceImpl extends AbstractBaseService<Sku, Long> implements SkuService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SkuRepository getSkuRepository() {
        return (SkuRepository) baseRepository;
    }

    @Override
    public Sku findByGoodsId(Long goodsId) {
        return getSkuRepository().findByGoodsId(goodsId);
    }


    @Transactional
    @Override
    public Boolean updateByLockSkuStock(Long id, Long version, Long quantity) {
        int row = getSkuRepository().updateByLockStock(id, version, quantity);
        if(row > 0){
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean updateByUnlockSkuStock(Long id, Long version, Long quantity) {
        int row = getSkuRepository().updateByUnlockStock(id, version, quantity);
        if(row > 0){
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean updateByUselockStock(Long id, Long version, Long quantity) {
        int row = getSkuRepository().updateByUselockStock(id, version, quantity);
        if(row > 0){
            return true;
        }
        return false;
    }

    @Override
    public Long getStockQuantity(Long id) {
        Sku sku = findOne(id);
        Long currCanUseAbleStock = ServiceManager.goodsBatchService.findCurrentStockByGoodsId(sku.getGoodsId());
        return currCanUseAbleStock - sku.getSecurityStock() - sku.getLockStockQuantity();
    }

    @Override
    public void checkGoodsState(Long skuId) {

        Sku sku = findOne(skuId);

        //商品
        Goods goods = ServiceManager.goodsService.findOne(sku.getGoodsId());
        if(BoolCodeEnum.YES == BoolCodeEnum.fromCode(goods.getIsDelete())
                || GoodsApproveStateCodeEnum.PASS_APPROVE != GoodsApproveStateCodeEnum.fromCode(goods.getApproveStateCode())
                || BoolCodeEnum.NO == BoolCodeEnum.fromCode(goods.getIsEnable())) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品[" + goods.getGoodsNm() + "]已下架");
        }
    }

}