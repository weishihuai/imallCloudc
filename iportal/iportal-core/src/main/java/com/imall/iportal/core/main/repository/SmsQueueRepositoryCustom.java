
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SmsQueue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SmsQueueRepositoryCustom{

    Page<SmsQueue> query(Pageable pageable, String sendStatCode, Long sysOrgId);
}

