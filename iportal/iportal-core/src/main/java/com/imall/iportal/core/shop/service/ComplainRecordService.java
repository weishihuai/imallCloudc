package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.ComplainRecord;
import com.imall.iportal.core.shop.vo.ComplainRecordSearchParam;
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
public interface ComplainRecordService{

    ComplainRecord saveComplainRecord(ComplainRecord complainRecord);

    /**
     * 列表查询
     * @param pageable
     * @param searchParam 查询条件
     */
    Page<ComplainRecord> query(Pageable pageable, @Valid @NotNull ComplainRecordSearchParam searchParam);
}
