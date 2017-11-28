package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.vo.SysOrgVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Donie on 2015/12/30.
 */
public interface SysOrgRepositoryCustom {
    Page<SysOrgVo> query(Pageable pageable, Long parentId, String orgName, String orgCode, String phone);
}
