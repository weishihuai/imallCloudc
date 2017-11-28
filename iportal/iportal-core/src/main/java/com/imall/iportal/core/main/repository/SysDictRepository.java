
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysDict;

import java.util.List;

/**
 * T_PT_SYS_DICT【数据字典】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysDictRepository extends  IBaseRepository<SysDict, Long>,SysDictRepositoryCustom {

   List<SysDict> findByDictType(String dictType);
}

