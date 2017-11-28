
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.MaintainingRecordPageVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface MaintainingRecordRepositoryCustom{

    /**
     * 分页查询设备设施维护记录
     * @param pageable
     * @param maintainingRecordSearchParam
     * @return
     */
    Page<MaintainingRecordPageVo> query(Pageable pageable, MaintainingRecordSearchParam maintainingRecordSearchParam);

}

