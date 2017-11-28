package com.imall.iportal.core.main.service.impl;

import com.imall.commons.base.util.JsonBinder;
import com.imall.commons.dicts.GlobalExt;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.JsonObjectEngine;
import com.imall.iportal.core.main.entity.SmsQueue;
import com.imall.iportal.core.main.service.SmsService;
import com.imall.iportal.core.main.utils.SmsUtils;
import com.imall.iportal.core.main.vo.SmsAccount;
import com.imall.iportal.dicts.JsonObjectTypeCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by yys on 2017/2/16.
 */
@Service
@Transactional(readOnly = true)
public class SmsServiceImpl implements SmsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendSms(SmsQueue queue) throws IOException {
        JsonObjectEngine jsonObjectEngine = ServiceManager.jsonObjectEngineService.getDataAsObject(JsonObjectTypeCodeEnum.SMS_ACCOUNT, GlobalExt.SMS_ACCOUNT_DEFAULT_OBJECT_ID);
        SmsAccount smsAccount = JsonBinder.toBeanObject(jsonObjectEngine.getJsonObjectValueStr(), SmsAccount.class);
        SmsUtils.sendSms(smsAccount, queue);
    }

}
