package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.TemperatureMoistureMonitorRecord;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordPageVo;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordSaveVo;
import com.imall.iportal.core.shop.vo.TemperatureMoistureMonitorRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface TemperatureMoistureMonitorRecordService {
    /**
     * 保存温度湿度监控记录
     * @param temperatureMoistureMonitorRecordSaveVo    温度湿度监控记录
     * @return
     */
    TemperatureMoistureMonitorRecord save(@NotNull @Valid TemperatureMoistureMonitorRecordSaveVo temperatureMoistureMonitorRecordSaveVo);

    /**
     * 分页查询温度湿度监控记录
     * @param pageable
     * @param temperatureMoistureMonitorRecordSearchParam   搜索条件实体
     * @return  分页对象
     */
    Page<TemperatureMoistureMonitorRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid TemperatureMoistureMonitorRecordSearchParam temperatureMoistureMonitorRecordSearchParam);
}
