package com.imall.commons.base.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by ygw on 2016/12/7.
 * 解决项目打成jar包后，加载不了logback.xml配置文件的问题
 */
public class LogbackLoaderUtil {

    /**
     * 根据指定的logback.xml配置文件初始化LoggerFactory
     * @param logbackXmlFile
     */
    public static void load(String logbackXmlFile){
        File logFile = new File(logbackXmlFile);
        if(logFile.exists() && logFile.isFile()){
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            try {
                configurator.doConfigure(logbackXmlFile); // "C:\\mmmc\\iceserver\\iportalserver\\logback.xml"
            } catch (JoranException e) {
                e.printStackTrace();
            }
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
            System.out.println("logbackXmlFile===================" + logbackXmlFile);
        }
    }
}
