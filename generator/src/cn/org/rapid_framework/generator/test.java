package cn.org.rapid_framework.generator;

/**
 * Created by lxd on 2015/1/12.
 */
public class test {
    public static void main(String[] args) {
        String views = "V_CM_CRUISE,V_CM_CRUISE_ROOM,V_CM_DESTINATION,V_CM_GIFT_USAGE,V_CM_HOTEL,V_CM_HOTEL_ROOM,V_CM_INSURANCE,V_CM_INSURANCE_RULE,V_CM_INSURANCE_RULE_MAPPING,V_CM_PRODUCT_DEST,V_CM_PRODUCT_DIRECTORY,V_CM_PRODUCT_GROUP_TOUR,V_CM_PRODUCT_TAG,V_CM_PRODUCT_TRAVEL_PLAN,V_CM_SALE_CHANNEL,V_CM_SCENIC,V_CM_SCENIC_USAGE,V_CM_SCHD_CRUISE_ROOM_PLAN,V_CM_SCHD_GIFT_USAGE,V_CM_SCHD_GROUP_EXPAND,V_CM_SCHD_INSURANCE_USAGE,V_CM_SCHD_OPT_SERVICE_USAGE,V_CM_SCHD_SALE_CHANNEL,V_CM_SCHD_SINGLE_EXPAND,V_CM_SCHD_STD_PRICE,V_CM_SCHD_TAG_USAGE,V_CM_SCHD_VISA_USAGE,V_CM_SCHEDULE,V_CM_TAG_USAGE,V_CM_TICKETS,V_CM_TRAVEL,V_CM_VISA,V_CM_VISA_MATERIAL,V_CM_VISA_RULE,V_CM_VISA_USAGE";
//        String sql = "Insert into B2C.TB_CM_VIEW_DATA_SYNC_TIME (ID,VIEW_NAME,LAST_SYNC_DATE,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,VERSION,IS_DELETED) values ('@id','@view',to_timestamp('01-1月 -77 09.03.53.337000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'DEFAULT_DATA                    ',to_timestamp('12-1月 -15 09.05.22.889000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'DEFAULT_DATA                    ',to_timestamp('12-1月 -15 09.05.30.870000000 上午','DD-MON-RR HH.MI.SSXFF AM'),1,'0');";
//        String id  = "000000000000000000000000000000";
        int i = 1;
        for (String v:views.split(",")){
//            System.out.println(sql.replaceAll("@id",id+(i<10?"0"+i:i)).replaceAll("@view",v));
//            i ++;
            System.out.println(v+"(\""+v+"\"),");
        }
    }
}
