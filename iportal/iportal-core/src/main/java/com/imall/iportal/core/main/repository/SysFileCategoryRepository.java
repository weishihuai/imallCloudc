
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysFileCategory;

import java.util.List;

/**
 * T_PT_SYS_FILE_CATEGORY 【文件分类】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysFileCategoryRepository extends  IBaseRepository<SysFileCategory, Long> {

    List<SysFileCategory> findByParentId(long parentId);

    List<SysFileCategory> findByParentIdAndOrgId(long parentId, long orgId);
}

