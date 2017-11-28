package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysResourceUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ygw on 2015/12/31.
 */
public interface SysResourceUrlRepositoryCustom {

    Page<SysResourceUrl> query(Pageable pageable, Long resourceId , String url);
}
