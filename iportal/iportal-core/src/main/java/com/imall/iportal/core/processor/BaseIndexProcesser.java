package com.imall.iportal.core.processor;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 索引进程基类
 * User: lxd
 * Date: 11-1-21
 * Time: 下午4:06
 */
public abstract class BaseIndexProcesser implements IProcesser{

    private Logger logger = Logger.getLogger(this.getClass().getName());
	private boolean running;

	@Scheduled(cron="${app.search.solr.server.indexUpdateFrequency:0/2 * * * * ?}")
    public void process(){
    	if(!ServiceManager.isInited() || running /*|| !SysInitBeanHelper.isSolrInited()*/){
    		return;
    	}
    	synchronized(this){
	        try {
				ServiceManager.esIndexQueueService.executeQueue(getObjectTypeCode());
	        } catch (Exception e) {
				logger.error(e.getMessage(),e);
			} finally {
				running = false;
			}
    	}
    }

    public abstract IndexTypeCodeEnum getObjectTypeCode();

}
