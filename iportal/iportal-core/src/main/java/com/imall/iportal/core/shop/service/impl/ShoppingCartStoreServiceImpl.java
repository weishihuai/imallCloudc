package com.imall.iportal.core.shop.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imall.iportal.core.shop.entity.ShoppingCartStore;
import com.imall.iportal.core.shop.repository.ShoppingCartStoreRepository;
import com.imall.iportal.core.shop.service.ShoppingCartStoreService;
import com.imall.commons.base.service.impl.AbstractBaseService;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ShoppingCartStoreServiceImpl extends AbstractBaseService<ShoppingCartStore, Long> implements ShoppingCartStoreService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ShoppingCartStoreRepository getShoppingCartStoreRepository() {
		return (ShoppingCartStoreRepository) baseRepository;
	}

	@Override
	public void clearCart(String cartUniqueKey) {
		getShoppingCartStoreRepository().delete(cartUniqueKey);
	}

	@Transactional
	@Override
	public void addCartItem(ShoppingCartStore store) {
		ShoppingCartStore dbStore = null;
		if(store.getGoodsBatchId()!=null){
			dbStore = getShoppingCartStoreRepository().findByCartUniqueKeyAndGoodsBatchId(store.getCartUniqueKey(), store.getGoodsBatchId());
		}
		else {
			dbStore = getShoppingCartStoreRepository().findByCartUniqueKeyAndSkuId(store.getCartUniqueKey(), store.getSkuId());
		}
		if(dbStore==null){
			save(store);
		}
		else {
			dbStore.setQuantity(store.getQuantity());
			dbStore.setSalesManId(store.getSalesManId());
			save(dbStore);
		}
	}

	@Transactional
	@Override
	public void batchAddCartItems(List<ShoppingCartStore> storeList) {
		for(ShoppingCartStore store: storeList){
			addCartItem(store);
		}
	}

	@Transactional
	@Override
	public void removeCartItemByBatchId(String cartUniqueKey, Long goodsBatchId) {
		getShoppingCartStoreRepository().deleteByBatchId(cartUniqueKey, goodsBatchId);
	}

	@Transactional
	@Override
	public void batchRemoveCartItemByBatchId(String cartUniqueKey, List<Long> goodsBatchIds) {
		for(Long goodsBatchId: goodsBatchIds){
			getShoppingCartStoreRepository().deleteByBatchId(cartUniqueKey, goodsBatchId);
		}
	}

	@Override
	public void removeCartItemBySkuId(String cartUniqueKey, Long skuId) {
		getShoppingCartStoreRepository().deleteBySkuId(cartUniqueKey, skuId);
	}

	@Override
	public void batchRemoveCartItemBySkuId(String cartUniqueKey, List<Long> skuIds) {
		for(Long skuId: skuIds){
			getShoppingCartStoreRepository().deleteBySkuId(cartUniqueKey, skuId);
		}
	}

	@Override
	public List<ShoppingCartStore> findByCartUniqueKey(String cartUniqueKey) {
		return getShoppingCartStoreRepository().findByCartUniqueKey(cartUniqueKey);
	}
}