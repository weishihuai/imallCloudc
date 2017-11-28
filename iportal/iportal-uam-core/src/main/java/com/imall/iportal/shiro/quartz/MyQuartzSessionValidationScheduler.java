package com.imall.iportal.shiro.quartz;

import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * Created by ygw on 2017/6/5.
 */
public class MyQuartzSessionValidationScheduler extends QuartzSessionValidationScheduler {

    public MyQuartzSessionValidationScheduler() {
        super();
    }

    public MyQuartzSessionValidationScheduler(ValidatingSessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    public void setScheduler(Scheduler scheduler) {
        super.setScheduler(scheduler);
       //报错：org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'securityManager' defined in file [D:\imall-cloudc-1.0-web\iportal\iportal-web\src\main\webapp\WEB-INF\classes\com\imall\iportal\shiro\META-INF\beans.xml]: Cannot resolve reference to bean 'shiroSessionManager' while setting bean property 'sessionManager'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'shiroSessionManager' defined in file [D:\imall-cloudc-1.0-web\iportal\iportal-web\src\main\webapp\WEB-INF\classes\com\imall\iportal\shiro\META-INF\beans.xml]: Cannot resolve reference to bean 'sessionValidationScheduler' while setting bean property 'sessionValidationScheduler'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sessionValidationScheduler' defined in file [D:\imall-cloudc-1.0-web\iportal\iportal-web\src\main\webapp\WEB-INF\classes\com\imall\iportal\shiro\META-INF\beans.xml]: Initialization of bean failed; nested exception is org.springframework.beans.ConversionNotSupportedException: Failed to convert property value of type 'org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler' to required type 'org.quartz.Scheduler' for property 'scheduler'; nested exception is java.lang.IllegalStateException: Cannot convert value of type 'org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler' to required type 'org.quartz.Scheduler' for property 'scheduler': no matching editors or conversion strategy found
    }

    public void destroy() throws Exception {
        Scheduler scheduler = getScheduler();
        //解决问题： quartzScheduler_Worker-1] but has failed to stop it. This is very likely to create a memory leak
        try {
            if(scheduler.isStarted()){
                scheduler.shutdown();
                Thread.sleep(1000);
            }
        } catch (SchedulerException e) {
            System.out.println("MyQuartzSessionValidationScheduler destroy error = = = = = = 1");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("MyQuartzSessionValidationScheduler destroy error = = = = = = 2");
            e.printStackTrace();
        }
    }
}
