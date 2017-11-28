package com.imall.iportal.shiro.service;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yang on 2015-11-10.
 */

//@Aspect
//@Service
//TODO yang
public class UrlAuthAop {

    @Autowired
    private ShiroFilerChainManager shiroFilerChainManager;

  /*  @AfterReturning("execution(public * com.imall.iportal.core.service.SysResourceService.save(..))")
    public void afterSave(JoinPoint jp) {
        doingSomething(jp);
    }

    @AfterReturning("execution(public * com.imall.iportal.core.service.SysResourceService.delete(..))")
    public void afterDelete(JoinPoint jp) {
        doingSomething(jp);
    }
*/

    private void doingSomething(JoinPoint jp) {
/*        SysResourceServiceRpcPrx client = IportalClientUtils.getServicePrx(SysResourceServiceRpcPrx.class);*/
       /* shiroFilerChainManager.initFilterChains(client.findAllUrlAuth());*/
    }


}

