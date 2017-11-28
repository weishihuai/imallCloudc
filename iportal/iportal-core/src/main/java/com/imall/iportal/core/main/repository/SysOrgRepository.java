
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysOrg;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * T_PT_SYS_ORG【组织】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysOrgRepository extends  IBaseRepository<SysOrg, Long>,SysOrgRepositoryCustom {

    List<SysOrg> findByParentId(Long parentId);

    Long countByParentId(Long parentId);

    @Query("select o.id from SysOrg o where o.nodeCode like ?1%")
    List<Long> findIdsByParentNodeCode(String parentNodeCode);
}

