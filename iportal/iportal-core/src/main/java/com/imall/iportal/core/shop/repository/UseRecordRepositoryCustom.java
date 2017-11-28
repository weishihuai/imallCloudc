
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.UseRecordPageVo;
import com.imall.iportal.core.shop.vo.UseRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface UseRecordRepositoryCustom{

    /**
     * 分页查询设备设施使用记录
     * @param pageable
     * @param useRecordSearchParam
     * @return
     */
    Page<UseRecordPageVo> query(Pageable pageable, UseRecordSearchParam useRecordSearchParam);

}

