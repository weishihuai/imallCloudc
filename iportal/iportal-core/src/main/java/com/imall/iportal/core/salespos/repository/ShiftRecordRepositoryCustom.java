
package com.imall.iportal.core.salespos.repository;

import com.imall.iportal.core.salespos.entity.ShiftRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * 交班 记录(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShiftRecordRepositoryCustom{

    /**
     *
     * @param pageable
     * @param shopId
     * @param posManName 收款员
     * @param formCreateDate 销售时间开始
     * @param toCreateDate 销售时间结束
     * @return
     */
    Page<ShiftRecord> query(Pageable pageable, Long shopId, String posManName, Date formCreateDate, Date toCreateDate);

}

