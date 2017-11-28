
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.FileMng;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface FileMngRepository extends  IBaseRepository<FileMng, Long>,FileMngRepositoryCustom {

    @Modifying
    @Query("delete from FileMng p where p.objectTypeCode=?1 and p.objectId = ?2")
    void delete(String objectTypeCode, Long objectId);

    @Query("select p from FileMng p where p.objectTypeCode=?1 and p.objectId = ?2")
    List<FileMng> getList(String objectTypeCode, Long objectId);

    @Modifying
    @Query("delete from FileMng p where p.objectTypeCode=?1 and p.sysFileLibId = ?2")
    void deleteBySysFileLibId(String objectTypeCode, Long sysFileLibId);
}

