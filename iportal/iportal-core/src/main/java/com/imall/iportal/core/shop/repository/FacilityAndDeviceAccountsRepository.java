
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import org.springframework.data.jpa.repository.Query;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FacilityAndDeviceAccountsRepository extends  IBaseRepository<FacilityAndDeviceAccounts, Long>,FacilityAndDeviceAccountsRepositoryCustom {

    @Query("SELECT F FROM FacilityAndDeviceAccounts F WHERE F.shopId = ?1 AND F.id = ?2")
    FacilityAndDeviceAccounts findOne(Long shopId, Long id);

    @Query("SELECT F FROM FacilityAndDeviceAccounts F WHERE F.shopId = ?1 AND F.deviceNum = ?2")
    FacilityAndDeviceAccounts findByDeviceNum(Long shopId, String deviceNum);
}

