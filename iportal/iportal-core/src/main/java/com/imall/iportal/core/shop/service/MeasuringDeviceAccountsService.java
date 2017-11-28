package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.MeasuringDeviceAccounts;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface MeasuringDeviceAccountsService {

    MeasuringDeviceAccounts findOne(@NotNull Long id);

    /**
     * 计量器具列表
     *
     * @param pageable                           分页对象
     * @param measuringDeviceAccountsSearchParam 搜索参数
     * @return
     */
    Page<MeasuringDeviceAccountsPageVo> query(@NotNull Pageable pageable, MeasuringDeviceAccountsSearchParam measuringDeviceAccountsSearchParam) throws ParseException;

    /**
     * 保存计量器具信息
     *
     * @param measuringDeviceAccountsSaveVo 保存Vo对象
     * @return
     */
    Long save(@NotNull @Valid MeasuringDeviceAccountsSaveVo measuringDeviceAccountsSaveVo) throws ParseException;

    /**
     * 修改计量器具信息
     *
     * @param measuringDeviceAccountsUpdateVo 更新Vo
     * @return
     */
    Long update(@NotNull @Valid MeasuringDeviceAccountsUpdateVo measuringDeviceAccountsUpdateVo) throws ParseException;

    /**
     * 计量器具检测
     *
     * @param id                             计量器具主键ID
     * @param measuringDeviceAccountsCheckVo 检测Vo
     * @return
     */
    Long measuringDeviceCheck(@NotNull Long id, @NotNull @Valid MeasuringDeviceAccountsCheckVo measuringDeviceAccountsCheckVo) throws ParseException;

}
