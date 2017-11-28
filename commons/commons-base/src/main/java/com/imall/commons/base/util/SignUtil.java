package com.imall.commons.base.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by ygw on 2015/12/10.
 */
public final class SignUtil {

    private SignUtil(){}


    public static String createNonceStr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    public static String md5Hash(SortedMap<String, String> paramMap, String salt){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, String> entry:paramMap.entrySet()){
            if(StringUtils.isNoneBlank(entry.getValue())){
                builder.append(entry.getKey());
                builder.append("=");
                builder.append(entry.getValue());
                builder.append("&");
            }
        }
        String str = builder.toString();
        if(StringUtils.isNoneBlank(str)){
            str = builder.deleteCharAt(str.length()-1).toString();
        }
        String sign = null;
        try {
            sign = HexUtil.toHexString(md5Hash(str.getBytes("UTF-8"), salt.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    private static byte[] md5Hash(byte[] bytes, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("md5");
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }
        return digest.digest(bytes);
    }

   /* public static void main(String[] args) {
        String a = md5Hash("12345", "123", "123123");
        String b = md5Hash("12345", "", "123123");
        assert a==b;
    }*/
}
