package com.imall.iportal.core.main.service;

import com.imall.iportal.dicts.ValidateCodeTypeEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SmsQueueService {

    /**
     * 供调度取出短信队列记录，发送短信
     */
    void sendSms();

    /**
     * 前台发送短信验证码统一接口
     * @param receiverMobile 接收号码
     * @param type 验证码类型
     * @param objectId 会员ID
     * @param reqIp 请求IP
     */
    void saveValidateCodeSmsQueue(@NotBlank String receiverMobile, @NotNull ValidateCodeTypeEnum type, @NotNull Long objectId, String reqIp);
}
