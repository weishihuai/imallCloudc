
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysDeveloperAuth;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * T_PT_SYS_DEVELOPER_AUTH【开发者授权】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysDeveloperAuthRepository extends  IBaseRepository<SysDeveloperAuth, Long> , SysDeveloperAuthRepositoryCustom{


    @Query("select da from SysDeveloperAuth da where da.userId = ?1 and da.isAvailable='Y'")
    List<SysDeveloperAuth> findByUserIdAndIsAvailable(Long userId);

}

