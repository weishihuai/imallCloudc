package com.imall.iportal.core.shop.service.impl;

import com.imall.commons.base.util.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 按时间生成唯一的订单编码
 * User: lxd
 * Date: 11-4-19
 * Time: 下午7:12
 */
@Component
public class DateSerialGenerator{
    private static final String DATE_PATTERN="yyMMddHHmmssSSS";
    private static final String DAY_PATTERN="yyMMdd";
    private static int seed =1;
    private static int daySeed =1;

    public static final String ORDER_PREFIX = "D"; //订单单号前缀
    public static final String SELL_RETURNED_PURCHASE_ORDER_PREFIX = "RD"; //销售退货订单单号前缀
    public static final String SUPPLIER_PREFIX = "S"; //供应商编码前缀
    public static final String SHOP_PREFIX = "SH"; //门店编码前缀
    public static final String EMPLOYEE_PREFIX = "E"; //上岗证号编码前缀
    public static final String STOCK_CHECK_PREFIX = "SC"; //库存盘点单号前缀
    public static final String STORAGE_SPACE_MOVE_PREFIX = "SSM"; //货位移动单号前缀
    public static final String DRUG_CHECK_PREFIX = "DC"; //检查单据号码前缀
    public static final String DRUG_CURING_PREFIX = "DCU"; //养护单据号码前缀
    public static final String OTHER_IN_STOCK_PREFIX = "OIS";   //其他入库单号前缀
    public static final String OTHER_OUT_STOCK_PREFIX = "OOS";   //其他出库单号前缀
    public static final String DRUG_SHOP_SALE_NOTICE_PREFIX = "DS";   //药品停售单单号前缀
    public static final String DRUG_RELEASE_NOTICE_PREFIX = "DR";   //药品接听单单单号前缀

    /**
     *
     * @param prefix 前缀
     * @return
     */
    public synchronized static String genSerialCode(String prefix) {
        if(seed<1000){ //防止在高并发的情况下出现多个相同的订单号
            seed++;
        }else {
            seed = 1;
        }
        return prefix + (new SimpleDateFormat(DATE_PATTERN)).format(DateTimeUtils.addMilliseconds(new Date(),seed));
    }

    /**
     *
     * @param prefix 前缀
     * @return
     */
    public synchronized static String genDaySerialCode(String prefix) {
        if(daySeed<1000){ //防止在高并发的情况下出现多个相同的订单号
            daySeed++;
        }else {
            daySeed = 1;
        }
        DecimalFormat format = new DecimalFormat("00000");
        return prefix + (new SimpleDateFormat(DAY_PATTERN)).format(new Date()) + format.format(daySeed);
    }
}
