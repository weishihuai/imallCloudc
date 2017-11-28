
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.vo.SysFileLibSearchParamVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SysFileLibRepositoryCustom {

    Page<SysFileLib> query(Pageable pageable, SysFileLibSearchParamVo paramVo, Long orgId);
}

