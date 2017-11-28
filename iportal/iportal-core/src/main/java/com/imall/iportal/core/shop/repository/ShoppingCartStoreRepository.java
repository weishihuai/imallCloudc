
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShoppingCartStoreRepository extends  IBaseRepository<ShoppingCartStore, Long>,ShoppingCartStoreRepositoryCustom {


    @Modifying
    @Query("delete from ShoppingCartStore s where s.cartUniqueKey=?1 and s.goodsBatchId = ?2")
    void deleteByBatchId(String cartUniqueKey, Long goodsBatchId);

    @Modifying
    @Query("delete from ShoppingCartStore s where s.cartUniqueKey=?1 and s.skuId = ?2")
    void deleteBySkuId(String cartUniqueKey, Long skuId);

    @Modifying
    @Query("delete from ShoppingCartStore s where s.cartUniqueKey=?1")
    void delete(String cartUniqueKey);

    ShoppingCartStore findByCartUniqueKeyAndGoodsBatchId(String cartUniqueKey , Long goodsBatchId);

    ShoppingCartStore findByCartUniqueKeyAndSkuId(String cartUniqueKey , Long skuId);

    List<ShoppingCartStore> findByCartUniqueKey(String cartUniqueKey);

}

