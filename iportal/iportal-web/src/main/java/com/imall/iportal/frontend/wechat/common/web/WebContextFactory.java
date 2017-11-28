package com.imall.iportal.frontend.wechat.common.web;

import com.imall.commons.base.global.Global;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * IWebContext 工厂实现.
 * User: lxd
 * Date: 2010-12-26
 * Time: 19:11:11
 */
@Component
public class WebContextFactory implements ServletContextAware {

	/**
	 * page目录的相对路径
	 */
	private static String pageBase;
    private static ServletContext servletContext;
    private static String localUploadPath;//本地上传路径
    private static String webRealPath;    //本地web根路径

	private WebContextFactory() {
    }

    private static ThreadLocal<IWebContext> ctxStore = new ThreadLocal<IWebContext>();

    public static void setWebContext(IWebContext ctx) {
        ctxStore.set(ctx);
    }

    public static IWebContext getWebContext() {
        IWebContext ctx =  ctxStore.get();
        if (ctx == null) {
            ctx = new DefaultWebContext();
            setWebContext(ctx);
        }
        return  ctxStore.get();
    }

    @SuppressWarnings("all")
    public void setServletContext(ServletContext servletContext) {
        setContext(servletContext);
    }

    public static synchronized void setContext(ServletContext servletContext) {
        WebContextFactory.servletContext = servletContext;
    }

    public static String getLocalUploadPath() {
        if (localUploadPath != null) {
            return localUploadPath;
        } else {
            localUploadPath = servletContext.getRealPath("/") + File.separatorChar + Global.UPLOAD_DIR + File.separatorChar;
            return localUploadPath;
        }
    }

    @Value("${app.springmvc.view.prefix}")
    public void setPageBase(String pageBase){
    	this.pageBase = pageBase;
    }
    
    public static String getPageBase(){
    	return pageBase;
    }
    
    /**
     * TODO 当solr server初始化速度比web快时，可能在setContext前就调用此方法了导致NullPointException
     * @return
     */
    public static String getWebRealPath() {
        if (webRealPath != null) {
            return webRealPath;
        } else {
            webRealPath =servletContext==null?null: servletContext.getRealPath("/");
            return webRealPath;
        }
    }

    public static String getContentPath() {
        return servletContext.getContextPath();
    }

    public static synchronized void setWebRootPath(String webRootPath) {
        WebContextFactory.webRealPath = webRootPath;
    }
}
