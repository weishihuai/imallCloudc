package com.imall.iportal.core.main.log;


import com.imall.commons.base.util.JsonBinder;
import com.imall.iportal.core.main.entity.SysLogData;
import com.imall.iportal.core.main.log.annotation.LogColumnField;
import com.imall.iportal.core.main.log.annotation.LogTableName;
import com.imall.iportal.core.main.log.dicts.LogColumnTypeEnum;
import com.imall.iportal.core.main.vo.SysLogVo;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * User: ygw
 * Date: 17-8-29
 */
public class LoggerUtils {

    private static ThreadLocal<SysLogVo> ldStore = new ThreadLocal<SysLogVo>();

    public static void setSysLogVo(SysLogVo logVo){
        ldStore.set(logVo);
    }

    public static SysLogVo getSysLogVo() {
        return ldStore.get();
    }

    public static boolean isInit(){
        SysLogVo logVo = ldStore.get();
        if (logVo!= null && logVo.getLogDataList()!=null) {
            return true;
        }
        return false;
    }

    private static Map<String,String> logTableNameMap = new LinkedHashMap<String, String>();

    public static String getTableNameByTableKey(String tableKey){
        Class clazz = null;
        try {
            clazz = Class.forName(tableKey);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(clazz!=null) {
            if (logTableNameMap.get(tableKey) == null) {
                LogTableName tableNameAnnotation = (LogTableName) clazz.getAnnotation(LogTableName.class);
                if (tableNameAnnotation != null) {
                    logTableNameMap.put(tableKey, tableNameAnnotation.value());
                }
            }
            return logTableNameMap.get(tableKey);
        }
        return "";
    }

    private static Map<String,Map<String, LogDataVoTemp>> logTableMap = new LinkedHashMap<String, Map<String, LogDataVoTemp>>();

    public static List<LogDataVo> getBySysLogData(SysLogData sysLogData){
        List<LogDataVo> resultList = new ArrayList<LogDataVo>();
        Class clazz = null;
        if (sysLogData == null) {
            return resultList;
        }
        try {
            clazz = Class.forName(sysLogData.getTableKey());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(clazz!=null){
            if(logTableMap.get(sysLogData.getTableKey())==null){
                LogTableName tableNameAnnotation = (LogTableName)clazz.getAnnotation(LogTableName.class);
                if(tableNameAnnotation==null){
                    return resultList;
                }
                String tableName = tableNameAnnotation.value();
                Map<String, LogDataVoTemp> logDataMap = new HashMap<String, LogDataVoTemp>();
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(LogColumnField.class)) {
                        LogDataVoTemp logDataVoTemp = new LogDataVoTemp();
                        logDataVoTemp.setTableKey(sysLogData.getTableKey());
                        logDataVoTemp.setTableName(tableName);
                        logDataVoTemp.setColumnKey(field.getName());
                        LogColumnField logField = field.getAnnotation(LogColumnField.class);
                        logDataVoTemp.setColumnName(logField.value());
                        logDataVoTemp.setColumnType(logField.type().toCode());
                        field.setAccessible(true); //设置private字段可以访问
                        logDataVoTemp.setField(field);
                        logDataMap.put(logDataVoTemp.getColumnKey(), logDataVoTemp);
                    }
                }
                logTableMap.put(sysLogData.getTableKey(), logDataMap);
            }

            Map<String, LogDataVoTemp> logDataMap = logTableMap.get(sysLogData.getTableKey());
            if(logDataMap!=null){
                Object beforeObj = JsonBinder.buildNormalBinder().toBeanObject(sysLogData.getBeforeOperationDataStr(), clazz);
                Object afterObj = JsonBinder.buildNormalBinder().toBeanObject(sysLogData.getAfterOperationDataStr(), clazz);
                for (Map.Entry<String, LogDataVoTemp> entry:logDataMap.entrySet()) {
                    LogDataVoTemp logDataVoTemp = entry.getValue();
                    LogDataVo logDataVo = new LogDataVo();
                    logDataVo.setTableKey(logDataVoTemp.getTableKey() + " logDataId=" + sysLogData.getId());
                    logDataVo.setTableName(logDataVoTemp.getTableName());
                    logDataVo.setColumnKey(logDataVoTemp.getColumnKey());
                    logDataVo.setColumnName(logDataVoTemp.getColumnName());
                    logDataVo.setColumnType(logDataVoTemp.getColumnType());
                    logDataVo.setLogDataId(sysLogData.getId());
                    logDataVo.setLogInnerCode(sysLogData.getLogInnerCode());
                    logDataVo.setOperationTime(sysLogData.getOperationTime());

                    try {
                        if (beforeObj != null){
                         Object beforeValue = logDataVoTemp.getField().get(beforeObj);
                         if(beforeValue!=null){
                            String newValue = null;
                            if(beforeValue instanceof byte[]){
                                try {
                                    newValue = new String((byte[])beforeValue, "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                newValue = beforeValue.toString();
                            }
                            logDataVo.setColumnBeforeOperationValue(newValue);
                        }
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (afterObj != null) {
                            Object afterValue = logDataVoTemp.getField().get(afterObj);
                            if (afterValue != null) {
                                String newValue = null;
                                if (afterValue instanceof byte[]) {
                                    try {
                                        newValue = new String((byte[]) afterValue, "UTF-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    newValue = afterValue.toString();
                                }
                                logDataVo.setColumnAfterOperationValue(newValue);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    if(LogColumnTypeEnum.PRIMARY_KEY== LogColumnTypeEnum.fromCode(logDataVo.getColumnType())){
                        //主键加到最前面
                        resultList.add(0, logDataVo);
                    }
                    else {
                        resultList.add(logDataVo);
                    }
                }
            }
        }

        Collections.sort(resultList, new Comparator<LogDataVo>() {
            @Override
            public int compare(LogDataVo o1, LogDataVo o2) {
                return o1.getLogDataId().compareTo(o2.getLogDataId());
            }
        });
        return resultList;
    }

}
