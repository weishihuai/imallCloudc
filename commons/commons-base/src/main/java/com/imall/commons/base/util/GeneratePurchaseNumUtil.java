package com.imall.commons.base.util;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by lxh on 2017/7/13.
 * 采购模块，生成采购单号、采购验收单号等
 */
public class GeneratePurchaseNumUtil {
    private GeneratePurchaseNumUtil() {
    }
    public static final String PURCHASE_ORDER_PREFIX = "CGDD"; //采购订单单号前缀
    public static final String RECEIVE_ORDER_PREFIX = "CGSH"; //采购收货订单单号前缀
    public static final String ACCEPTANCE_ORDER_PREFIX = "CGYS"; //采购验收订单单号前缀
    public static final String RETURNED_PURCHASE_ORDER_PREFIX = "GJTC"; //购进退出订单单号前缀


    public static String generateNum(String prefix, Long shopId, Long count) {
        DecimalFormat format = new DecimalFormat("00000");
        return new StringBuffer().append(prefix)
                .append(shopId)
                .append(DateTimeUtils.convertDateToString(new Date(), "yyyyMMdd"))
                .append(format.format(count)).toString();
    }

}
