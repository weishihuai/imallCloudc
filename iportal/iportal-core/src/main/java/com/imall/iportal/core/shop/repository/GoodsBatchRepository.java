
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.GoodsBatch;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsBatchRepository extends  IBaseRepository<GoodsBatch, Long>,GoodsBatchRepositoryCustom {

    List<GoodsBatch> findByGoodsId(Long goodsId);

    List<GoodsBatch> findByGoodsIdAndShopId(Long goodsId, Long shopId);

    List<GoodsBatch> findByGoodsIdAndShopIdAndBatchState(Long goodsId, Long shopId, String batchState);

    GoodsBatch findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePrice(Long goodsId, String batch, Long storageSpaceId, Long supplierId, Double purchasePrice);

    GoodsBatch findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(Long goodsId, String batch, Long storageSpaceId, Long supplierId, Double purchasePrice,String batchState);

    @Query("select b, count(distinct b.batch) from GoodsBatch b where b.shopId=?1 and b.goodsId=?2 and b.batchState='NORMAL' group by b.batch")
    List<Object> findDistinctBatch(Long shopId, Long goodsId);

    List<GoodsBatch> findByGoodsIdAndBatch(Long goodsId, String batch);

}

