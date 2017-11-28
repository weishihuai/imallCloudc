package com.imall.iportal.core.main.service.impl;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SmsQueue;
import com.imall.iportal.core.main.entity.SysParamInf;
import com.imall.iportal.core.main.entity.ValidateCodeLog;
import com.imall.iportal.core.main.repository.SmsQueueRepository;
import com.imall.iportal.core.main.service.SmsQueueService;
import com.imall.iportal.dicts.SendStatCodeEnum;
import com.imall.iportal.dicts.SmsTypeCodeEnum;
import com.imall.iportal.dicts.ValidateCodeTypeEnum;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SmsQueueServiceImpl extends AbstractBaseService<SmsQueue, Long> implements SmsQueueService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SmsQueueRepository getSmsQueueRepository() {
        return (SmsQueueRepository) baseRepository;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void sendSms() {
        //取出未发送的200短信队列进行发送
        Page<SmsQueue> queuePage = this.getSmsQueueRepository().query(new PageRequest(0, 200), SendStatCodeEnum.UN_SEND.toCode(), null);
        for (SmsQueue smsQueue : queuePage.getContent()) {
            try {
                ServiceManager.smsService.sendSms(smsQueue);
                smsQueue.setSendStatCode(SendStatCodeEnum.SUCCESS.toCode());
                smsQueue.setSendTime(new Date());
                this.update(smsQueue);
            } catch (Exception e) {
                smsQueue.setErrorLog(e.getMessage());
                smsQueue.setErrorTime(new Date());
                smsQueue.setSendStatCode(SendStatCodeEnum.FAIL.toCode());
                this.update(smsQueue);
            }
        }
    }


    @Override
    @Transactional
    public void saveValidateCodeSmsQueue(String receiverMobile, ValidateCodeTypeEnum type, Long objectId, String reqIp) {
        //验证码限制检查
        this.checkLimit(receiverMobile, reqIp);
        String webName = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WEB_NAME).getParamValueStr();
        String validateCode = RandomStringUtils.randomNumeric(6);
        Integer invalidMin = 10;//过期时间 10分钟
        StringBuilder stringBuilder = new StringBuilder()
                .append("【").append(webName).append("】")
                .append("您的验证码为：").append(validateCode).append(",")
                .append("在").append(invalidMin).append("分钟内完成验证，请勿泄露。");
        SmsQueue smsQueue = new SmsQueue();
        smsQueue.setSysOrgId(Global.ADMIN_DEFAULT_ORG_ID);
        smsQueue.setCont(stringBuilder.toString());
        smsQueue.setObjectId(objectId);
        smsQueue.setReceiverMobile(receiverMobile);
        smsQueue.setSendStatCode(SendStatCodeEnum.UN_SEND.toCode());
        smsQueue.setSmsType(SmsTypeCodeEnum.VALIDATE_CODE.toCode());
        smsQueue.setReqIp(reqIp);
        Long position = getSmsQueueRepository().getMaxPosition();
        smsQueue.setPosition(position == null ? 1L : position + 1);
        this.save(smsQueue);
        //保存发送验证码记录，用于验证
        this.saveValidateCodeLog(smsQueue, validateCode, type, invalidMin);
    }

    private void checkLimit(String mobile, String reqIp){
        //手机限制
        SysParamInf sysParamInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.MOBIL_RECEIVE_VALID_SMS_LIMIT_NUM);
        Date now = new Date();
        Date start = DateTimeUtils.getDayStartTime(now);
        Date end = DateTimeUtils.getDayEndTime(now);
        if (sysParamInf != null && StringUtils.isNumeric(sysParamInf.getParamValueStr())){
            Integer limitNum = Integer.valueOf(sysParamInf.getParamValueStr());
            Integer daySendNum = getSmsQueueRepository().countDaySendNumByMobile(mobile, start, end);
            if (daySendNum >= limitNum){
                throw new BusinessException(ResGlobal.COMMON_ERROR, "您今天发送验证码的次数已超出系统限制");
            }
        }
        //ip限制
        sysParamInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.IP_RECEIVE_VALID_SMS_LIMIT_NUM);
        if (sysParamInf != null && StringUtils.isNumeric(sysParamInf.getParamValueStr())){
            Integer limitNum = Integer.valueOf(sysParamInf.getParamValueStr());
            Integer daySendNum = getSmsQueueRepository().countDaySendNumByReqIp(reqIp, start, end);
            if (daySendNum >= limitNum){
                throw new BusinessException(ResGlobal.COMMON_ERROR, "您今天发送验证码的次数已超出系统限制");
            }
        }
        //发送频率限制
        sysParamInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.SEND_SMS_FREQUENCY);
        if (sysParamInf != null && StringUtils.isNumeric(sysParamInf.getParamValueStr())){
            Integer limitFrequency = Integer.valueOf(sysParamInf.getParamValueStr());
            Date lastCreateTime = getSmsQueueRepository().getLastCreateDateByMobile(mobile);
            if (lastCreateTime != null && DateTimeUtils.addSeconds(lastCreateTime, limitFrequency).after(now)){
                throw new BusinessException(ResGlobal.COMMON_ERROR, limitFrequency + "秒内仅能获得一次验证码，请稍候重试。");
            }
        }
    }

    /**
     * 保存发送验证码记录，用于验证
     * @param smsQueue
     * @param validateCode
     * @param invalidMin
     */
    private void saveValidateCodeLog(SmsQueue smsQueue, String validateCode, ValidateCodeTypeEnum type, Integer invalidMin){
        ValidateCodeLog log = new ValidateCodeLog();
        log.setSysOrgId(Global.ADMIN_DEFAULT_ORG_ID);
        log.setTypeCode(type.toCode());
        log.setInvalidTime(DateTimeUtils.addMinutes(smsQueue.getCreateDate(), invalidMin));
        log.setAccount(smsQueue.getReceiverMobile());
        log.setValidateCode(validateCode);
        log.setObjectId(smsQueue.getObjectId());
        ServiceManager.validateCodeLogService.save(log);
    }
}