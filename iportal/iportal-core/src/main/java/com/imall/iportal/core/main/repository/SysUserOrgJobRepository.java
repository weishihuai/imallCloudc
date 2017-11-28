
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T_PT_SYS_USER_ORG_JOB【组织岗位】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysUserOrgJobRepository extends  IBaseRepository<SysUserOrgJob, Long> , SysUserOrgJobRepositoryCustom{

    List<SysUserOrgJob> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("delete from SysUserOrgJob p where p.userId=?1 ")
    void deleteByUserId(Long userId);

    List<SysUserOrgJob> findByOrgId(Long orgId);

    @Query("select s from SysUserOrgJob s where s.orgId = :orgId and s.ismain = 'Y'")
    List<SysUserOrgJob> findByOrgIdAndIsmain(@Param("orgId") Long orgId);

    @Query("select count(s)>0 from SysUserOrgJob s where s.orgId = ?1")
    Boolean existsUserByOrgId(Long orgId);

    @Query("select count(s)>0 from SysUserOrgJob s where s.userId = ?1")
    Boolean existsUserByUserId(Long userId);

    @Query("select s from SysUserOrgJob s where s.userId = :userId and s.ismain = 'Y'")
    SysUserOrgJob findByUserIdAndIsmain(@Param("userId") Long userId);

    SysUserOrgJob findByUserIdAndOrgIdAndJobId(Long userId, Long orgId, Long jobId);

    @Query("select count(s)>0 from SysUserOrgJob s where s.userId = ?1 and s.orgId = ?2 and  s.jobId = ?3")
    Boolean existsByUserIdAndOrgIdAndJobId(Long userId, Long orgId, Long jobId);
}

