package com.imall.iportal.core.main.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.entity.SysLogData;
import com.imall.iportal.core.main.log.LogDataVo;
import com.imall.iportal.core.main.vo.SysLogDataVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.text.ParseException;
import java.util.List;

/**
 * 系统 日志 数据(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysLogDataService{


    /**
     *
     * @param logData
     * @return
     */
    SysLogData save(SysLogData logData);

    /**
     *
     * @param logInnerCode
     * @return
     */
    List<LogDataVo> listByLogInnerCode(String logInnerCode);

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    List<LogDataVo> findById(Long id) throws BusinessException, ParseException;


    Page<SysLogDataVo> query(Pageable pageable, String tableKey, Long objectId, String searchText);

}
