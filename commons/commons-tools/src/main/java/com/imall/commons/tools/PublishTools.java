package com.imall.commons.tools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ygw on 2017/3/22.
 */
public class PublishTools {


    public static void main(String[] args) throws IOException {
        String[] serviceJars = {
                "/opt/site/iportal/index-service/index-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/iportal/iportal-service/iportal-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/iportal/iportal-uam-service/iportal-uam-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/iportal/quartz-service/quartz-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/article-service/article-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/custss-service/custss-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/example-service/example-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/goods-service/goods-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/marketing-service/marketing-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/member-service/member-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/merchants-service/merchants-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/message-service/message-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/product-service/product-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/shop-service/shop-service-b2b-3.0-0.0.1-SNAPSHOT.jar",
                "/opt/site/apps/syssettings-service/syssettings-service-b2b-3.0-0.0.1-SNAPSHOT.jar"

        };
        //目录
        String[] dirs = {
                "/opt/site/iportal/index-service/lib",
                "/opt/site/iportal/iportal-service/lib",
                "/opt/site/iportal/iportal-uam-service/lib",
                "/opt/site/iportal/quartz-service/lib",
                "/opt/site/apps/article-service/lib",
                "/opt/site/apps/custss-service/lib",
                "/opt/site/apps/example-service/lib",
                "/opt/site/apps/goods-service/lib",
                "/opt/site/apps/marketing-service/lib",
                "/opt/site/apps/member-service/lib",
                "/opt/site/apps/merchants-service/lib",
                "/opt/site/apps/message-service/lib",
                "/opt/site/apps/product-service/lib",
                "/opt/site/apps/shop-service/lib",
                "/opt/site/apps/syssettings-service/lib",
                "/opt/site/tomcat7/webapps/article/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/custss/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/example/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/goods/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/index/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/iportal/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/marketing/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/member/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/merchants/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/message/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/product/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/quartz/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/shop/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/syssettings/WEB-INF/lib",
                "/opt/site/tomcat7/webapps/uamserver/WEB-INF/lib",

        };
        ArrayList<String> lines = new ArrayList<>();
        lines.add("#!/bin/bash");
        for (String dir : dirs) {
            File[] jars = (new File(dir)).listFiles();
            for (File jar : jars) {
                StringBuilder buff = new StringBuilder();
                buff.append("cp /opt/site/jars/");
                buff.append(jar.getName());
                buff.append(" ");
                buff.append(dir);
                lines.add(buff.toString());
                System.out.println(buff.toString());
            }
        }
        for (String serviceJar : serviceJars) {
            File serviceJarFile = new File(serviceJar);
            StringBuilder buff = new StringBuilder();
            buff.append("cp /opt/site/jars/");
            buff.append(serviceJarFile.getName());
            buff.append(" ");
            buff.append(serviceJar.replace(serviceJarFile.getName(), ""));
            lines.add(buff.toString());
            System.out.println(buff.toString());
        }

        //生成“分发文件”
        FileUtils.writeLines(new File("jars-send.sh"), lines);

        //生成“移动”文件
        lines = new ArrayList<>();
        lines.add("#!/bin/bash");
        for (String dir : dirs) {
            StringBuilder buff = new StringBuilder();
            buff.append("cp -R ");
            buff.append(dir);
            buff.append("/*");
            buff.append(" ");
            buff.append("/opt/site/jars/");
            lines.add(buff.toString());
            System.out.println(buff.toString());
        }
        for (String serviceJar : serviceJars) {
            StringBuilder buff = new StringBuilder();
            buff.append("cp ");
            buff.append(serviceJar);
            buff.append(" ");
            buff.append("/opt/site/jars/");
            lines.add(buff.toString());
            System.out.println(buff.toString());
        }

        FileUtils.writeLines(new File("jars-add.sh"), lines);

        //生成“删除文件”
        lines = new ArrayList<>();
        lines.add("#!/bin/bash");
        for (String dir : dirs) {
            StringBuilder buff = new StringBuilder();
            buff.append("rm -rf ");
            buff.append(dir);
            buff.append("/*");
            lines.add(buff.toString());
            System.out.println(buff.toString());
        }

        for (String serviceJar : serviceJars) {
            StringBuilder buff = new StringBuilder();
            buff.append("rm -rf ");
            buff.append(serviceJar);
            lines.add(buff.toString());
            System.out.println(buff.toString());
        }
        FileUtils.writeLines(new File("jars-delete.sh"), lines);
        return;
    }
}
