
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.vo.PrescriptionRegisterPageVo;
import com.imall.iportal.core.shop.vo.PrescriptionRegisterSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface PrescriptionRegisterRepositoryCustom{

    /**
     * 分页查询处方登记
     * @param pageable
     * @param prescriptionRegisterSearchParam
     * @return
     */
    Page<PrescriptionRegisterPageVo> query(Pageable pageable, PrescriptionRegisterSearchParam prescriptionRegisterSearchParam);

}

