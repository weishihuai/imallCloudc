package com.imall.commons.base.util;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by ygw on 2015/12/10.
 */
public final class IMallSignUtil {

    public static String md5Hash(String appKey, String nonceStr, String appSecret){
        SortedMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("app_key", appKey);
        paramMap.put("nonce_str", nonceStr);
        return SignUtil.md5Hash(paramMap, appSecret);
    }
}
