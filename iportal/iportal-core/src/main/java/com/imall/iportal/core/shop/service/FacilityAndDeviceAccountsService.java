package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsPageVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSaveVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSearchParam;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsUpdateVo;
import org.hibernate.validator.constraints.NotEmpty;
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
public interface FacilityAndDeviceAccountsService{
    /**
     * 保存
     * @param saveVo
     */
    void save(@NotNull @Valid FacilityAndDeviceAccountsSaveVo saveVo);

    /**
     * 分页查询设施设备台账信息
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<FacilityAndDeviceAccountsPageVo> query(@NotNull Pageable pageable, @NotNull @Valid FacilityAndDeviceAccountsSearchParam searchParam);

    /**
     * 更新
     * @param updateVo
     */
    void update(@NotNull @Valid FacilityAndDeviceAccountsUpdateVo updateVo);

    /**
     * 检查设备号是否存在
     * @param shopId        门店 ID
     * @param id            记录 ID
     * @param deviceNum     设备号
     * @return              存在：true，不存在：false
     */
    Boolean checkDeviceNum(@NotNull Long shopId, Long id, @NotEmpty String deviceNum);

    /**
     * 详情
     * @param shopId
     * @param id
     * @return
     */
    FacilityAndDeviceAccounts findOne(@NotNull Long shopId, @NotNull Long id);
}
