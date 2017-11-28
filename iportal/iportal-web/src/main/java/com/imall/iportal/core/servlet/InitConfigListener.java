package com.imall.iportal.core.servlet;

import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitConfigListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        WebContextFactory.setContext(sce.getServletContext());
        String env = System.getProperty("spring.profiles.active");
        if(env == null) {
            System.setProperty("spring.profiles.active", "development");
            System.out.println("spring.profiles.active=development");
            return;
        }
        System.out.println("spring.profiles.active=" + env);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}