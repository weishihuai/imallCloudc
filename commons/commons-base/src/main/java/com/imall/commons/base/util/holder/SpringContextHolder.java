package com.imall.commons.base.util.holder;

import com.imall.commons.base.util.Node;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SpringContextHolder implements ApplicationContextAware {

    private static Log log = LogFactory.getLog(SpringContextHolder.class);
    private static ApplicationContext applicationContext;

    @SuppressWarnings("all")
    public void setApplicationContext(ApplicationContext context) {
        if (this.applicationContext != null) {
            log.error("ApplicationContextHolder already holded 'applicationContext'.");
        }
        this.applicationContext = context;
        log.debug("holded applicationContext,displayName:" + applicationContext.getDisplayName());
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
        }
        return applicationContext;
    }

    private static ConcurrentMap<String,Object> beans = new ConcurrentHashMap<>();

    public static Object getBean(String beanName) {
        if(beanName==null){
            return null;
        }
        if(beans.get(beanName)!=null){
            return beans.get(beanName);
        } else {
            Object instance = getApplicationContext().getBean(beanName);
            beans.put(beanName,instance);
            return instance;
        }
    }

    public static Object getBean(Class requiredType) {
        if(requiredType==null){
            return null;
        }
        String beanName = requiredType.getSimpleName();
        beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
        return getBean(beanName);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> requiredType){
    	return getApplicationContext().getBeansOfType(requiredType);
    }
    
    public static void cleanHolder() {
        applicationContext = null;
    }


}
