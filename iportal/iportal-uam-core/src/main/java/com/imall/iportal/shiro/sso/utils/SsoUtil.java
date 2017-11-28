package com.imall.iportal.shiro.sso.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by ygw on 2015/12/2.
 */
public class SsoUtil {


    public static String getCookieValueByName(ServletRequest servletRequest, String cookieName){
        HttpServletRequest httpRequest = WebUtils.toHttp(servletRequest);
        Cookie[] cookies =  httpRequest.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    public static String md5Hash(SortedMap<String, String> paramMap, String salt){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, String> entry:paramMap.entrySet()){
            if(StringUtils.hasLength(entry.getValue())){
                builder.append(entry.getKey());
                builder.append("=");
                builder.append(entry.getValue());
                builder.append("&");
            }
        }
        String str = builder.toString();
        if(StringUtils.hasLength(str)){
            str = builder.deleteCharAt(str.length()-1).toString();
        }
       return new Md5Hash(str, salt).toString();
    }
}
