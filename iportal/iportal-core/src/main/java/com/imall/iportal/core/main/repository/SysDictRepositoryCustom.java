package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysDict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Donie on 2015/12/28.
 */
public interface SysDictRepositoryCustom {
    Page<SysDict> query(Pageable pageable, String dictNm, String dictType, String isAvailable);
}
