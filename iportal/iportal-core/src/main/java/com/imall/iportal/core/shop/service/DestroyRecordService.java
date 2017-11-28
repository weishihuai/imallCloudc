package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DestroyRecord;
import com.imall.iportal.core.shop.vo.DestroyRecordPageVo;
import com.imall.iportal.core.shop.vo.DestroyRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DestroyRecordService {
    /**
     * 保存销毁记录
     *
     * @param destroyRecord 销毁记录
     * @return
     */
    DestroyRecord save(@NotNull DestroyRecord destroyRecord);

    /**
     * 分页查询销毁记录
     *
     * @param pageable
     * @param destroyRecordSearchParam 搜索条件实体
     * @return 分页对象
     */
    Page<DestroyRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid DestroyRecordSearchParam destroyRecordSearchParam);
}
