package com.imall.iportal.core.shop.commons.util;

import com.imall.commons.base.util.DateTimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ygw on 2017/3/1.
 */
public final class ShoppingUtil {


    private static final String DATE_PATTERN="yyMMddHHmmssSSS";
    private static int seed =1;

    public static synchronized String genSerialCode() {
        if(seed<1000){ //防止在高并发的情况下出现多个相同的订单号
            seed++;
        }else {
            seed = 1;
        }
        return (new SimpleDateFormat(DATE_PATTERN)).format(DateTimeUtils.addMilliseconds(new Date(),seed));
    }

    //会员
    public static String genCartKey(String cartType, Long shopId, Long memberId){
        return cartType + "_"  + shopId + "_" + memberId;
    }

    //非会员
    public static String genCartKey(String cartType, Long shopId){
        return cartType + "_"  + shopId + "_" + genSerialCode();
    }




}
