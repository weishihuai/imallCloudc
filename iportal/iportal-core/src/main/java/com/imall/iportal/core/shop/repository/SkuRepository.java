
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.Sku;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SkuRepository extends IBaseRepository<Sku, Long>, SkuRepositoryCustom {

    /**
     * 根据商品ID查询相应的Sku信息
     *
     * @param goodsId 商品ID
     * @return
     */
    @Query("select s from Sku s where s.goodsId =?1")
    Sku findByGoodsId(Long goodsId);

    @Modifying
    @Query("update Sku set lockStockQuantity=(lockStockQuantity + :quantity) where id = :id and version = :version and (currentStock - securityStock - lockStockQuantity) >= :quantity")
    Integer updateByLockStock(@Param("id") Long id, @Param("version") Long version, @Param("quantity") Long quantity);

    @Modifying
    @Query("update Sku set lockStockQuantity=(lockStockQuantity - :quantity) where id = :id and version = :version and lockStockQuantity >= :quantity")
    Integer updateByUnlockStock(@Param("id") Long id, @Param("version") Long version, @Param("quantity") Long quantity);

    @Modifying
    @Query("update Sku set lockStockQuantity=(lockStockQuantity - :quantity) where id = :id and version = :version and lockStockQuantity >= :quantity")
    Integer updateByUselockStock(@Param("id") Long id, @Param("version") Long version, @Param("quantity") Long quantity);

}

