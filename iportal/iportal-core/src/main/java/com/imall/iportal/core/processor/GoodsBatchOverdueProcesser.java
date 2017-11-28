package com.imall.iportal.core.processor;

import com.imall.iportal.core.main.commons.ServiceManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 商品批次过期处理进程基类
 * User: lxd
 * Date: 11-1-21
 * Time: 下午4:06
 */
@Component
public class GoodsBatchOverdueProcesser implements IProcesser{

    private Logger logger = Logger.getLogger(this.getClass().getName());
	private boolean running;

	@Scheduled(cron="${app.goodsBatch.server.overdueUpdateFrequency:0/2 * * * * ?}")
    public void process(){
    	if(!ServiceManager.isInited() || running /*|| !SysInitBeanHelper.isSolrInited()*/){
    		return;
    	}
    	synchronized(this){
	        try {
				ServiceManager.goodsBatchService.updateOverdueBatch();
	        } catch (Exception e) {
				logger.error(e.getMessage(),e);
			} finally {
				running = false;
			}
    	}
    }

}
