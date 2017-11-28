package com.imall.iportal.core.shop.service;

import com.imall.iportal.core.shop.entity.ConsultService;
import com.imall.iportal.core.shop.vo.ConsultServiceDetailVo;
import com.imall.iportal.core.shop.vo.ConsultServiceSaveVo;
import com.imall.iportal.core.shop.vo.ConsultServiceSearchParam;
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
public interface ConsultServiceService{

    /**
     * 保存
     * @param saveVo 咨询信息和咨询商品ID
     * @return
     */
    Long save(@Valid @NotNull ConsultServiceSaveVo saveVo);

    /**
     * 咨询登记列表
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<ConsultService> query(Pageable pageable, @Valid @NotNull ConsultServiceSearchParam searchParam);

    /**
     * 查找咨询详情
     * @param id 咨询服务主键ID
     * @return
     */
    ConsultServiceDetailVo findById(@NotNull Long shopId, @NotNull Long id);
}
