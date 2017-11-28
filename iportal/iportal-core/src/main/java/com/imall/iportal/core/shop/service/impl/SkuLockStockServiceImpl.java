package com.imall.iportal.core.shop.service.impl;



import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.dicts.LockStateCodeEnum;
import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.entity.Sku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.SkuLockStock;
import com.imall.iportal.core.shop.repository.SkuLockStockRepository;
import com.imall.iportal.core.shop.service.SkuLockStockService;
import com.imall.commons.base.service.impl.AbstractBaseService;

import java.util.Date;
import java.util.List;

/**
 * SKU_锁_库存(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SkuLockStockServiceImpl extends AbstractBaseService<SkuLockStock, Long> implements SkuLockStockService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private SkuLockStockRepository getSkuLockStockRepository() {
		return (SkuLockStockRepository) baseRepository;
	}


	@Transactional
	@Override
	public void lockedStock(Long shopId, Long memberId, String orderNum, OrderSourceCodeEnum orderSourceCode, List<SkuLockStock> skuLockStocks) throws BusinessException {
		for(SkuLockStock skuLockStock: skuLockStocks){
			//重试3次
			int count = 0;
			final int max = 3;
			while (count<max){
				Sku sku = ServiceManager.skuService.findOne(skuLockStock.getSkuId());
				Boolean success = ServiceManager.skuService.updateByLockSkuStock(sku.getId(), sku.getVersion(), skuLockStock.getQuantity());
				if(success){
					break; //如果成功，跳出while
				}
				if(!success && count>=max-1){
					logger.error("skuId=" + sku.getId() + " 库存不足，锁库存失败");
					Goods goods = ServiceManager.goodsService.findOne(sku.getGoodsId());
					throw new BusinessException(ResGlobal.COMMON_ERROR, goods.getGoodsNm() + "，库存不足，锁库存失败");
				}
				count++;
				try {
					Thread.sleep(100L); //
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			skuLockStock.setShopId(shopId);
			skuLockStock.setMemberId(memberId);
			skuLockStock.setOrderNum(orderNum);
			skuLockStock.setOrderSourceCode(orderSourceCode.toCode());
			skuLockStock.setLockStateCode(LockStateCodeEnum.LOCKED.toCode());
			skuLockStock.setLockTime(new Date());
			save(skuLockStock);
		}
	}

	/**
	 * 解锁 还库存
	 * @param orderNum
	 * @return
	 */
	@Transactional
	@Override
	public void unLockStock(String orderNum) {
		List<SkuLockStock> lockStockList = getSkuLockStockRepository().findByOrderNum(orderNum);
		for(SkuLockStock skuLockStock: lockStockList){
			//解锁
			skuLockStock.setLockStateCode(LockStateCodeEnum.UNLOCK.toCode());
			save(skuLockStock);
			//还库存
			//重试3次
			int count = 0;
			final int max = 3;
			while (count<max){
				Sku sku = ServiceManager.skuService.findOne(skuLockStock.getSkuId());
				Boolean success = ServiceManager.skuService.updateByUnlockSkuStock(sku.getId(), sku.getVersion(), skuLockStock.getQuantity());
				if(success){
					break; //如果成功，跳出while
				}
				if(!success && count>=max-1){
					logger.error("skuId=" + sku.getId() + " 解锁还库存失败......");
					Goods goods = ServiceManager.goodsService.findOne(sku.getGoodsId());
					throw new BusinessException(ResGlobal.COMMON_ERROR, goods.getGoodsNm() + "，解锁还库存失败......");
				}
				count++;
				try {
					Thread.sleep(100l); //
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Transactional
	@Override
	public void useLock(String orderNum) {
		List<SkuLockStock> lockStockList = getSkuLockStockRepository().findByOrderNum(orderNum);
		for(SkuLockStock skuLockStock: lockStockList){
			//解锁
			skuLockStock.setLockStateCode(LockStateCodeEnum.USE_LOCK.toCode());
			save(skuLockStock);
			//还库存
			//重试3次
			int count = 0;
			final int max = 3;
			while (count<max){
				Sku sku = ServiceManager.skuService.findOne(skuLockStock.getSkuId());
				Boolean success = ServiceManager.skuService.updateByUselockStock(sku.getId(), sku.getVersion(), skuLockStock.getQuantity());
				if(success){
					break; //如果成功，跳出while
				}
				if(!success && count>=max-1){
					logger.error("skuId=" + sku.getId() + " 用锁失败......");
					Goods goods = ServiceManager.goodsService.findOne(sku.getGoodsId());
					throw new BusinessException(ResGlobal.COMMON_ERROR, goods.getGoodsNm() + "，用锁失败......");
				}
				count++;
				try {
					Thread.sleep(100l); //
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}