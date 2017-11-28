
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.StaffHealthDoc;
import com.imall.iportal.core.shop.vo.StaffHealthDocSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StaffHealthDocRepositoryCustom{


    Page<StaffHealthDoc> query(Pageable pageable, StaffHealthDocSearchParam staffHealthDocSearchParam) throws ParseException;
}

