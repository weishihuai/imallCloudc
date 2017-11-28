package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DisqualificationDrugProcessRecord;
import com.imall.iportal.core.shop.vo.DisqualificationDrugProcessRecordPageVo;
import com.imall.iportal.core.shop.vo.DisqualificationDrugProcessRecordSearchParam;
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
public interface DisqualificationDrugProcessRecordService {
    /**
     * 分页查询不合格药品处理记录
     *
     * @param pageable                                     分页对象
     * @param disqualificationDrugProcessRecordSearchParam 搜索条件
     * @return
     */
    Page<DisqualificationDrugProcessRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid DisqualificationDrugProcessRecordSearchParam disqualificationDrugProcessRecordSearchParam);

    DisqualificationDrugProcessRecord save(DisqualificationDrugProcessRecord record);
}
