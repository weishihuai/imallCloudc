package com.imall.iportal.core.processor;

import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeChatRefreshGetAccessTokenProcessor implements IProcesser {
    private static Logger logger = Logger.getLogger(WeChatRefreshGetAccessTokenProcessor.class);
    private boolean refresh = false;
    private String isOpen;

    public boolean isRefresh() {
        return refresh;
    }

    @Value("${app.processor.weiXinRefreshGetAccessToken.isOpen:Y}")
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    @Scheduled(cron = "${app.processor.weiXinRefreshGetAccessToken.frequency:0/1 * * * * ?}")
    public void process() {
        ServiceManager.weChatApiService.doWeChatAccessTokenTimePlus();
        if (BoolCodeEnum.NO == BoolCodeEnum.fromCode(isOpen) || !ServiceManager.isInited()) {
            return;
        }
        try {
            if (ServiceManager.weChatApiService.getWeChatGetAccessTokenResult() == null || StringUtils.isBlank(ServiceManager.weChatApiService.getWeChatGetAccessTokenResult().getAccess_token()) ||
                    (ServiceManager.weChatApiService.getWeChatAccessTokenTime() + 300) > ServiceManager.weChatApiService.getWeChatGetAccessTokenResult().getExpires_in()) {
                refresh = true;
                ServiceManager.weChatApiService.refreshGetAccessToken();
            }
        } catch (Throwable tr) {
            logger.error(tr.getMessage(), tr);
        } finally {
            refresh = false;
        }
    }

}
