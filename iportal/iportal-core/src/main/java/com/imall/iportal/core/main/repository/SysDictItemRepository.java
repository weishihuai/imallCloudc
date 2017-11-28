
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.vo.SysDictItemSimpleVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * T_PT_SYS_DICT_ITEM【数据字典项】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysDictItemRepository extends  IBaseRepository<SysDictItem, Long>,SysDictItemRepositoryCustom {

    @Query("select dictItemNm  from SysDictItem s where s.dataDictId = ?1")
    List<String> findDictItemNmByDataDictId(Long dataDictId);

    List<SysDictItem> findByDataDictId(Long dataDictId);

    SysDictItem findByDictItemCode(String dictItemCode);

    List<SysDictItem> findByIsAvailableAndDataDictId(String isAvailable, Long dataDictId);
}

