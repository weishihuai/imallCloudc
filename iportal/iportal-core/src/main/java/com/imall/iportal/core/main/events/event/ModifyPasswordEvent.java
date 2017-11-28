package com.imall.iportal.core.main.events.event;

import com.imall.iportal.core.main.entity.SysUser;
import org.springframework.context.ApplicationEvent;

/**
 * Created by ygw on 2017/4/19.
 */
public class ModifyPasswordEvent extends ApplicationEvent {

    public ModifyPasswordEvent(SysUser sysUser) {
        super(sysUser);
    }
}
