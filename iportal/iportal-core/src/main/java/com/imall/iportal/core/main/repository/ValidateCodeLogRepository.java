package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.ValidateCodeLog;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/6/28.
 */
public interface ValidateCodeLogRepository extends IBaseRepository<ValidateCodeLog, Long>, ValidateCodeLogRepositoryCustom{

    @Query("select cl from ValidateCodeLog cl where cl.validateCode=?1 and cl.account=?2 and cl.typeCode=?3 and cl.invalidTime > ?4")
    List<ValidateCodeLog> findValidLog(String validateCode, String account, String typeCode, Date invalidTime);
}
