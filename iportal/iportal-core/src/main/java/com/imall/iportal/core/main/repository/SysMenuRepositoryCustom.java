package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.vo.SysMenuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Donie on 2015/12/29.
 */
public interface SysMenuRepositoryCustom {
    Page<SysMenuVo> query(Pageable pageable, Long parentId, String menuName);
}
