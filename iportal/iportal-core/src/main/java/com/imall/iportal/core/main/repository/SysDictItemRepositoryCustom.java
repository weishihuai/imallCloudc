package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysDictItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Donie on 2015/12/28.
 */
public interface  SysDictItemRepositoryCustom {
     Page<SysDictItem> query(Pageable pageable, String dictItemNm, String dictItemCode, Long dataDictId);
}
