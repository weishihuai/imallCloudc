package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.vo.SysJobVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Donie on 2015/12/28.
 */
public interface SysJobRepositoryCustom {

    Page<SysJobVo> query(Pageable pageable,Long orgId);
}
