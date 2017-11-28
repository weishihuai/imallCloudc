package com.imall.iportal.core.main.service;

import com.imall.iportal.core.main.entity.ValidateCodeLog;
import com.imall.iportal.dicts.ValidateCodeTypeEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/6/28.
 */
@Validated
public interface ValidateCodeLogService{

    /**
     * 找出有效的验证记录
     * @param validateCode
     * @param account
     * @param typeCode
     * @return
     */
    ValidateCodeLog findValidLog(@NotBlank String validateCode, @NotBlank String account, @NotNull ValidateCodeTypeEnum typeCode);

    ValidateCodeLog save(@NotNull ValidateCodeLog log);
}
