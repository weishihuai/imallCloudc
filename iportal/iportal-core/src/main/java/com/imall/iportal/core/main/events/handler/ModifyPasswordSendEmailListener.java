package com.imall.iportal.core.main.events.handler;

import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.events.event.ModifyPasswordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by ygw on 2017/4/19.
 * 修改密码发Email事件
 */
@Component
public class ModifyPasswordSendEmailListener implements SmartApplicationListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //supportsEventType：用于指定支持的事件类型，只有支持的才调用onApplicationEvent；
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ModifyPasswordEvent.class == eventType ;
    }

    //supportsSourceType：支持的目标类型，只有支持的才调用onApplicationEvent；
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return SysUser.class == sourceType;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        SysUser sysUser = (SysUser) applicationEvent.getSource();
        //发email
        logger.info("修改密码发Email事件...by " + sysUser.getUserName());
    }

    //即顺序，越小优先级越高
    @Override
    public int getOrder() {
        return 10;
    }
}
