package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.MeasureRecord;
import com.imall.iportal.core.shop.vo.MeasureRecordDetailVo;
import com.imall.iportal.core.shop.vo.MeasureRecordPageVo;
import com.imall.iportal.core.shop.vo.MeasureRecordSearchParam;
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
public interface MeasureRecordService {

    /**
     * 保存
     *
     * @param measureRecord 检测记录
     * @return
     */
    MeasureRecord save(@NotNull MeasureRecord measureRecord);

    /**
     * 计量器具 检查记录列表
     *
     * @param pageable                 分页对象
     * @param measureRecordSearchParam 搜索参数
     * @return
     */
    Page<MeasureRecordPageVo> query(Pageable pageable, @Valid @NotNull MeasureRecordSearchParam measureRecordSearchParam);

    /**
     * 查找计量器具 检测记录详情
     *
     * @param id 计量器具 检测记录主键ID
     * @return
     */
    MeasureRecordDetailVo findById(@NotNull Long shopId, @NotNull Long id);

}
