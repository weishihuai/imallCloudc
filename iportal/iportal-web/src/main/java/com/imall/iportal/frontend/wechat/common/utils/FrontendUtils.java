package com.imall.iportal.frontend.wechat.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxh on 2017/8/25.
 */
public class FrontendUtils {
    private FrontendUtils(){}

    //获取真的的IP
    public static String getRealIp(HttpServletRequest request) {
        //获取真正的IP地址
        String forwardedStr = request.getHeader("x-forwarded-for");
        String realIp = "";
        if (StringUtils.isNotBlank(forwardedStr)) {
            String[] ips = forwardedStr.split(",");
            for (String ip : ips) {
                if (StringUtils.isNotBlank(ip) && !ip.equals("unknown")) {
                    realIp = ip;
                }
            }
        }
        return StringUtils.isBlank(realIp) ? request.getRemoteAddr() : realIp;
    }
}
