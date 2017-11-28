package com.imall.iportal.core.main.service;

import com.imall.iportal.core.main.entity.SmsQueue;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Created by yys on 2017/2/16.
 */
@Validated
public interface SmsService {

    /**
     * 发送短信
     * @param queue
     */
    void sendSms(@NotNull SmsQueue queue) throws IOException;
}
