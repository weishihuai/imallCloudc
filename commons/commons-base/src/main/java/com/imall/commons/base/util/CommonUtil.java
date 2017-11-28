package com.imall.commons.base.util;


import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import javax.persistence.Column;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;

public class CommonUtil {

    private static final String DateField = "Date";
    private static final String IDField = "id";
    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public static PageRequest getPageable(String sortField, String sortOrder, int offset, int limit) {
        int page = (int) (offset / limit);
        Direction direction = Direction.ASC;
        if (Direction.DESC.toString().equals(sortOrder)) {
            direction = Direction.DESC;
        }
        PageRequest pageable = new PageRequest(page, (int) limit, direction, sortField);
        return pageable;
    }

    public static PageRequest getPageable(int offset, int limit) {
        int page = (int) (offset / limit);
        PageRequest pageable = new PageRequest(page, (int) limit);
        return pageable;
    }

    public static Sort getSort(String sortField, String sortOrder) {
        Sort sort = null;
        if (StringUtils.isNotBlank(sortField)) {
            Direction direction = Direction.ASC;
            if (Direction.DESC.toString().equalsIgnoreCase(sortOrder)) {
                direction = Direction.DESC;
            }
            sort = new Sort(direction, sortField);
        }
        return sort;
    }

    //id,name: asc;key: desc;
    public static Sort convertSort(String ordersStr,Class<?> clazz){
        if(StringUtils.isBlank(ordersStr)){
            return null;
        }
        List<Sort.Order> orderList = new ArrayList<Sort.Order>();
        String[] orders = ordersStr.split(";");
        for(String orderStr:orders){
            String[] ss = orderStr.split(":");
            Direction direction = Direction.ASC;
            if (Direction.DESC.toString().equalsIgnoreCase(ss[1].trim())) {
                direction = Direction.DESC;
            }

           String column=CommonUtil.getColumn(clazz,ss[0].trim());
            if(StringUtils.isNotBlank(column)) {
                orderList.add(new Sort.Order(direction, column));
            }
        }
        if(orderList.isEmpty()){
            return null;
        }
        return new Sort(orderList);
    }



    public static String getColumn(Class<?> clazz,String field){
        String column = null;
        if(BaseEntity.ID.equalsIgnoreCase(field)){
            return BaseEntity.ID;
        }
        if(BaseEntity.CREATE_BY.equalsIgnoreCase(field)){
            return "CREATE_BY";
        }
        if(BaseEntity.CREATE_DATE.equalsIgnoreCase(field)){
            return "CREATE_DATE";
        }
        if(BaseEntity.LAST_MODIFIED_BY.equalsIgnoreCase(field)){
            return "LAST_MODIFIED_BY";
        }
        if(BaseEntity.LAST_MODIFIED_DATE.equalsIgnoreCase(field)){
            return "LAST_MODIFIED_DATE";
        }
        try {
            if(clazz.getDeclaredField(field)!=null){
                column = clazz.getDeclaredField(field).getAnnotation(Column.class).name();
            }
        } catch (Exception e) {
            try {
                column = clazz.getSuperclass().getDeclaredField(field).getAnnotation(Column.class).name();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return column;
    }


    public static String insertFileNameSuffixToUrl(String url, String suffix){
        int index = url.lastIndexOf('.');
        if(index > 0){
            StringBuilder sb = new StringBuilder(url);
            sb.insert(index, suffix);
            return sb.toString();
        }
        return url;
    }

    public static String filterStr(Object str){
        return str == null?null:(String) str;
    }

    public static String CreateNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    public static String ArrayToXml(Map<String, String> arr) {
        String xml = "<xml>";

        Iterator<Map.Entry<String, String>> iter = arr.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            String key = entry.getKey();
            String val = entry.getValue();
            if (IsNumeric(val)) {
                xml += "<" + key + ">" + val + "</" + key + ">";

            } else
                xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
        }

        xml += "</xml>";
        return xml;
    }

    public static boolean IsNumeric(String str) {
        if (str.matches("\\d *")) {
            return true;
        } else {
            return false;
        }
    }

    //不能使用spring的BeanUtils.copyProperties，因为copy Entity的时候，copy不了父类的某些属性，如BaseEntity的id,因为id是泛型ID的类型。
    public static <T> T copyProperties(Object source, T target){
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return target;
    }

    //不能使用spring的BeanUtils.copyProperties，因为copy Entity的时候，copy不了父类的某些属性，如BaseEntity的id,因为id是泛型ID的类型。
    public static <T> T copyProperties(Object source, T target, T checkSourceNullDefaultValue){
        if(source==null){
            return checkSourceNullDefaultValue;
        }
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return target;
    }

    public static <T> T copyFromDbMap(Map<String, Object> map, T target){
        Map<String, Object> newMap = new HashMap<>();
        for(Map.Entry<String, Object> entry: map.entrySet()){
            //下划线分隔的字符串，转换为驼峰式字符串,例如：USER_NAME to userName
            StringBuilder keyBuilder = new StringBuilder();
            String[] keys = entry.getKey().split("_");
            keyBuilder.append(keys[0].toLowerCase()); //第一个单词小写
            for(int i=1; i<keys.length; i++){
                String key = keys[i];
                String temp = key.substring(0, 1).toUpperCase() + key.substring(1, keys[i].length()).toLowerCase(); //首字母大写，其它字母小写
                keyBuilder.append(temp);
            }
            Object value = entry.getValue()!=null && entry.getValue() instanceof BigInteger ? ((BigInteger) entry.getValue()).longValue() : entry.getValue();
            newMap.put(keyBuilder.toString(),  value);
        }

        try {
            BeanUtils.copyProperties(target, newMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return target;
    }

    //对于byte[] 类型，由于vo的字段类型是bolb，而实体entity的字段类型是byte[], BeanUtils copy的时候会报错，先把byte[]转blob
    public static <T> T copyFromDbMap(Map<String, Object> map, T target, Session session){
        Map<String, Object> newMap = new HashMap<>();
        for(Map.Entry<String, Object> entry: map.entrySet()){
            //下划线分隔的字符串，转换为驼峰式字符串,例如：USER_NAME to userName
            StringBuilder keyBuilder = new StringBuilder();
            String[] keys = entry.getKey().split("_");
            keyBuilder.append(keys[0].toLowerCase()); //第一个单词小写
            for(int i=1; i<keys.length; i++){
                String key = keys[i];
                String temp = key.substring(0, 1).toUpperCase() + key.substring(1, keys[i].length()).toLowerCase(); //首字母大写，其它字母小写
                keyBuilder.append(temp);
            }
            Object value = null;
            if (entry.getValue() != null) {
                if (entry.getValue() instanceof BigInteger) {
                    value = ((BigInteger) entry.getValue()).longValue();
                } else if (entry.getValue() instanceof byte[]) {
                    value = session.getLobHelper().createBlob((byte[]) entry.getValue());
                } else {
                    value = entry.getValue();
                }
            }
            newMap.put(keyBuilder.toString(),  value);
        }

        try {
            BeanUtils.copyProperties(target, newMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     *
     * @param num  格式化的参数
     * @param digits  格式化成多少位数
     * @return
     */
    public static String formatNumber(int num,int digits) {
        //得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(digits);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(digits);
        return nf.format(num);
    }

    public static void formatQueryParameter(Query query, Map<String, Object> paramMap) {
        if (paramMap == null || paramMap.isEmpty()) {
            return;
        }
        for (String param : paramMap.keySet()) {
            query.setParameter(param, paramMap.get(param));
        }
    }
}
