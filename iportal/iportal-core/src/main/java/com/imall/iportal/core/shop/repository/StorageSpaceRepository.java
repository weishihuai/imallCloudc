
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.StorageSpace;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StorageSpaceRepository extends IBaseRepository<StorageSpace, Long>, com.imall.iportal.core.shop.repository.StorageSpaceRepositoryCustom {
    /**
     * 检查商品货位是否有重复
     *
     * @param storageSpaceNm 货位名称
     * @param shopId         门店ID
     * @return
     */
    StorageSpace findByStorageSpaceNmAndShopId(String storageSpaceNm, Long shopId);



    /**
     * 根据门店ID查询已启用状态的货位信息
     *
     * @param shopId 门店ID
     * @return
     */
    List<StorageSpace> findByShopIdAndIsEnable(Long shopId, String isEnable);

    /**
     * 根据门店ID查询所有的非虚拟货位信息
     *
     * @param shopId 门店ID
     * @return
     */
    List<StorageSpace> findByShopIdAndIsVirtualWarehouseAndStorageSpaceTypeAndIsEnable(Long shopId, String isVirtualWarehouse, String storageSpaceType, String isEnable);

}

