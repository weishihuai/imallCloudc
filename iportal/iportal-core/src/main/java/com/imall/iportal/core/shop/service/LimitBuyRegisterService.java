package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface LimitBuyRegisterService{
    /**
     * 分页查询限购登记信息
     *
     * @param pageable                    分页对象
     * @param limitBuyRegisterSearchParam 搜索参数
     * @return
     */
    Page<LimitBuyRegisterPageVo> query(@NotNull Pageable pageable,@NotNull @Valid LimitBuyRegisterSearchParam limitBuyRegisterSearchParam);

    /**
     * 保存限购登记信息
     *
     * @param limitBuyRegisterSaveVo
     * @return
     */
    Long save(@NotNull @Valid LimitBuyRegisterSaveVo limitBuyRegisterSaveVo);

    /**
     * 修改限购登记信息
     *
     * @param limitBuyRegisterUpdateVo
     * @return
     */
    Long update(@NotNull @Valid LimitBuyRegisterUpdateVo limitBuyRegisterUpdateVo);

    /**
     * 根据限购登记ID查询限购登记信息
     *
     * @param id 限购登记ID
     * @return
     */
    LimitBuyRegisterDetailVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 根据订单 ID 查询订单项信息
     * @param orderId
     * @return
     */
    List<LimitBuyRegisterItemVo> listItemByOrderId(@NotNull Long shopId, @NotNull Long orderId);
}
