package com.imall.iportal.core.main.service.impl;

import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.iportal.core.main.entity.ValidateCodeLog;
import com.imall.iportal.core.main.repository.ValidateCodeLogRepository;
import com.imall.iportal.core.main.service.ValidateCodeLogService;
import com.imall.iportal.dicts.ValidateCodeTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/6/28.
 */
@Service
@Transactional(readOnly = true)
public class ValidateCodeLogServiceImpl extends AbstractBaseService<ValidateCodeLog, Long> implements ValidateCodeLogService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private ValidateCodeLogRepository getValidateCodeLogRepository() {
        return (ValidateCodeLogRepository) baseRepository;
    }

    @Override
    public ValidateCodeLog findValidLog(String validateCode, String account, ValidateCodeTypeEnum typeCode) {
        List<ValidateCodeLog> logs = getValidateCodeLogRepository().findValidLog(validateCode, account, typeCode.toCode(), new Date());
        return logs.isEmpty() ? null : logs.get(0);
    }
}
