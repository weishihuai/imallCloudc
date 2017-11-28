package com.imall.iportal.core.platform.service;


import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.platform.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface ShopService{

    Shop findOne(@NotNull Long id);
    /**
     * 列表
     *
     * @param pageable
     * @param shopSearchParam
     * @return
     */
    Page<ShopPageVo> queryShop(Pageable pageable,  ShopSearchParam shopSearchParam) throws ParseException;

    /**
     * 更新
     *
     * @param shopUpdateVo
     * @return
     */
    Long update(@NotNull @Valid ShopUpdateVo shopUpdateVo) throws ParseException;


    /**
     * 保存
     *
     * @param shopSaveVo
     * @return
     */
    Long save(@NotNull @Valid ShopSaveVo shopSaveVo) throws ParseException;


    /**
     * 详情
     *
     * @param id
     * @return
     */
    ShopDetailVo findById(@NotNull Long id,@NotNull Long orgId);

    /**
     * 禁用/启用
     *
     * @param id
     * @param state 门店是否启用
     * @return
     */

    Long updateShopState(@NotNull Long id, @NotBlank String state);


    Shop findByOrgId(@NotNull Long orgId);
}
