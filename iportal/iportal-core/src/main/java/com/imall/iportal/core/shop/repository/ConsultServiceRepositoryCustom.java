
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.ConsultService;
import com.imall.iportal.core.shop.vo.ConsultServiceSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ConsultServiceRepositoryCustom{

    /**
     * 咨询登记列表
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<ConsultService> query(Pageable pageable, ConsultServiceSearchParam searchParam);

}

