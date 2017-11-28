
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DestroyRecord;
import com.imall.iportal.core.shop.vo.DestroyRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DestroyRecordRepositoryCustom {

    Page<DestroyRecord> query(Pageable pageable, DestroyRecordSearchParam destroyRecordSearchParam);

}

