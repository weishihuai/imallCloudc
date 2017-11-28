
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.DrugLock;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugLockRepository extends IBaseRepository<DrugLock, Long>, DrugLockRepositoryCustom {

    /**
     * 根据门店ID以及ID查询药品锁定信息
     *
     * @param shopId 门店ID
     * @param id     主键ID
     * @return
     */
    @Query("select d from DrugLock d where d.shopId =?1 and d.id =?2")
    DrugLock findDrugLockByShopIdAndId(Long shopId, Long id);

}

