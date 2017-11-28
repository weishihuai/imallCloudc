package com.imall.iportal.core.main.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.vo.SysLogVo;
import org.springframework.validation.annotation.Validated;

/**
 * 系统 日志(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysLogService{

    /**
     *
     * @param logVo
     * @return
     * @throws BusinessException
     */
    Long saveLog(SysLogVo logVo) throws BusinessException;

}
