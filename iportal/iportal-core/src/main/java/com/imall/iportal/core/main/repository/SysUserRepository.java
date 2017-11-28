
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * T_SYS_USER【用户】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysUserRepository extends  IBaseRepository<SysUser, Long>, SysUserRepositoryCustom {

    @Query("select count(s)>0 from SysUser s where s.orgId = ?1")
    Boolean existsUserByOrgId(Long orgId);

    SysUser findByUserName(String userName);

    List<SysUser> findByShopId(Long shopId);

    List<SysUser> findByShopIdAndIsEnable(Long shopId, String isEnable);

    SysUser findByUserNameOrEmailOrMobile(String userName, String email, String mobile);

    SysUser findByShopIdAndIsDefaultAdminAndOrgId(Long shopId, String isDefaultAdmin,Long orgId);




}

