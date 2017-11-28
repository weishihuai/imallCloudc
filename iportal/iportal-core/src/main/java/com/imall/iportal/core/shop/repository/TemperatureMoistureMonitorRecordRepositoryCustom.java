
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordPageVo;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface TemperatureMoistureMonitorRecordRepositoryCustom{

    Page<TemperatureMoistureMonitorRecordPageVo> query(Pageable pageable, TemperatureMoistureMonitorRecordSearchParam temperatureMoistureMonitorRecordSearchParam);

}

