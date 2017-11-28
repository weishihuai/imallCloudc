package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ygw on 2015/12/31.
 */
public interface SysExceptionCodeRepositoryCustom {

    Page<SysExceptionCode> query(Pageable pageable, String code, String exceptionMsg);
}
