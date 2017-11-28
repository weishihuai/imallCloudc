
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DisqualificationDrugProcessRecord;
import com.imall.iportal.core.shop.vo.DisqualificationDrugProcessRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DisqualificationDrugProcessRecordRepositoryCustom {

    /**
     * 分页查询不合格药品处理记录
     *
     * @param pageable                                     分页对象
     * @param disqualificationDrugProcessRecordSearchParam 搜索条件
     * @return
     */
    Page<DisqualificationDrugProcessRecord> query(Pageable pageable, DisqualificationDrugProcessRecordSearchParam disqualificationDrugProcessRecordSearchParam);

}

