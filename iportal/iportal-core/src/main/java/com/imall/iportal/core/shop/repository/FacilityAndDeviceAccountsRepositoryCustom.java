
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsPageVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FacilityAndDeviceAccountsRepositoryCustom{

    /**
     * 分页查询设施设备台账信息
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<FacilityAndDeviceAccountsPageVo> query(Pageable pageable, FacilityAndDeviceAccountsSearchParam searchParam);

}

