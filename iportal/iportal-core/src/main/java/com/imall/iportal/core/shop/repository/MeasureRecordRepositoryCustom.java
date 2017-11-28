
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.MeasureRecord;
import com.imall.iportal.core.shop.vo.MeasureRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface MeasureRecordRepositoryCustom {

    /**
     * 计量器具 检测记录 列表
     *
     * @param pageable                 分页对象
     * @param measureRecordSearchParam 搜索参数
     * @return
     */
    Page<MeasureRecord> query(Pageable pageable, MeasureRecordSearchParam measureRecordSearchParam);


}

