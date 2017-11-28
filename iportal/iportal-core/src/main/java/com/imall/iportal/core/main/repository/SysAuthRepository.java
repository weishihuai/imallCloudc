
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysAuth;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * T_PT_SYS_AUTH【授权】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysAuthRepository extends  IBaseRepository<SysAuth, Long> , SysAuthRepositoryCustom{

    @Query("select s.roleId from SysAuth s where s.jobId = ?1 order by s.id asc")
    List<Long> findRoleIdsByJobId(Long jobId);

    @Query("select s from SysAuth s where s.jobId = ?1 and s.roleId=?2")
    List<SysAuth> findByJobIdAndRoleId(Long jobId,Long roleId);

    List<SysAuth> findByJobId(Long jobId);

}

