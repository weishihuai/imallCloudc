
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.MeasuringDeviceAccounts;
import com.imall.iportal.core.shop.vo.MeasuringDeviceAccountsSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface MeasuringDeviceAccountsRepositoryCustom {

    /**
     * 分页查询计量器具信息
     *
     * @param pageable                           分页对象
     * @param measuringDeviceAccountsSearchParam 搜索参数
     * @return
     */
    Page<MeasuringDeviceAccounts> query(Pageable pageable, MeasuringDeviceAccountsSearchParam measuringDeviceAccountsSearchParam);


}

