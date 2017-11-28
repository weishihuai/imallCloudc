package com.imall.commons.base.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于日期类使用原则: 
 *
 * 所有的日期运算均使用java.util.Date类进行比较和增减运算，禁止直接使用字符串！
 * 需要字符串时，先转换为日期类型运算完毕再转换为字符串。避免产生太多的静态方法！
 *
 * @author Jim Wu
 *
 */
public class DateTimeUtils extends DateUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String COMPACT_DATE_PATTERN = "yyyyMMdd";
    private static final String COMPACT_TIME_PATTERN = "HHmmss";
    private static final String MM_TIME_PATTERN = "HH:mm";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String MIL_PATTERN = "HH:mm:ss:SSS";
    private static final String TIME_PATTERN_WEEK = "yyyy-MM-dd E";
    private static final String DATE_PATTERN_CN = "yyyy年MM月dd日";

    /* ------------------------------  类型转换方法 --------------------------- */

    public static final String convertDateToWeekString(Date aDate) {
        return aDate == null ? null : (new SimpleDateFormat(TIME_PATTERN_WEEK)).format(aDate);
    }

    public static final String convertDateToString(Date aDate,String pattern) {
        return aDate == null ? null : (new SimpleDateFormat(pattern)).format(aDate);
    }

    public static final String convertDateToString(Date aDate) {
        return aDate == null ? null : (new SimpleDateFormat(DATE_PATTERN)).format(aDate);
    }

    public static final String convertDateToCnString(Date aDate) {
        return aDate == null ? null : (new SimpleDateFormat(DATE_PATTERN_CN)).format(aDate);
    }

    public static Date convertStringToDate(String strDate) throws ParseException {
        return (new SimpleDateFormat(DATE_PATTERN)).parse(strDate);
    }

    public static final String convertDateToCompactString(Date aDate) {
        return aDate == null ? null : (new SimpleDateFormat(COMPACT_DATE_PATTERN)).format(aDate);
    }

    public static Date convertCompactStringToDate(String strDate) throws ParseException {
        return (new SimpleDateFormat(COMPACT_DATE_PATTERN)).parse(strDate);
    }

    public static final String convertTimeToCompactString(Date aDate) {
        return aDate == null ? null : (new SimpleDateFormat(COMPACT_DATE_PATTERN+COMPACT_TIME_PATTERN)).format(aDate);
    }

    public static Date convertCompactStringToTime(String strDate) throws ParseException {
        return (new SimpleDateFormat(COMPACT_DATE_PATTERN+COMPACT_TIME_PATTERN)).parse(strDate);
    }

    public static java.sql.Date convertStringToSqlDate(String strDate) throws ParseException {
        return new java.sql.Date((new SimpleDateFormat(DATE_PATTERN)).parse(strDate).getTime());
    }

    public static final String convertMmTimeToString(Date aTime) {
        return aTime == null ? null : (new SimpleDateFormat(MM_TIME_PATTERN)).format(aTime);
    }

    public static Date convertStringToMmTime(String strMmTime) throws ParseException {
        return new Date((new SimpleDateFormat(MM_TIME_PATTERN)).parse(strMmTime).getTime());
    }

    public static final String convertDateTimeToString(Date aDateTime) {
        return aDateTime == null ? null : (new SimpleDateFormat(DATE_PATTERN + " " + TIME_PATTERN)).format(aDateTime);
    }

    public static final String convertDateTimeToMilString(Date aDateTime) {
        return aDateTime == null ? null : (new SimpleDateFormat(DATE_PATTERN + " " + MIL_PATTERN)).format(aDateTime);
    }

    public static final String convertDateTimeToMmString(Date aDateTime) {
        return aDateTime == null ? null : (new SimpleDateFormat(DATE_PATTERN + " " + MM_TIME_PATTERN)).format(aDateTime);
    }

    public static final Date convertMmStringToDateTime(String strMmDateTime) throws ParseException {
        return StringUtils.isBlank(strMmDateTime) ? null : new Date((new SimpleDateFormat(DATE_PATTERN + " " + MM_TIME_PATTERN)).parse(strMmDateTime).getTime());
    }

    public static Date convertStringToDateTime(String strDateTime) throws ParseException {
        return StringUtils.isBlank(strDateTime) ? null : new Date((new SimpleDateFormat(DATE_PATTERN + " " + TIME_PATTERN)).parse(strDateTime).getTime());
    }

    public static Date convertStringToDateTime(String datePattern,String strDateTime)  {
        try {
            return new Date((new SimpleDateFormat(datePattern)).parse(strDateTime).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* -----------------------------  以下是日期运算方法 --------------------------- */

    public static Date getMonthEndTime(Calendar calendar){
        Calendar tmpCalendar = (Calendar)calendar.clone();
        tmpCalendar.set(Calendar.DAY_OF_MONTH, 1);
        tmpCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tmpCalendar.set(Calendar.MINUTE, 0);
        tmpCalendar.set(Calendar.SECOND, 0);
        tmpCalendar.set(Calendar.MILLISECOND, 0);

        tmpCalendar.add(Calendar.MONTH, 1);
        tmpCalendar.add(Calendar.MILLISECOND, -1);
        return tmpCalendar.getTime();
    }

    public static Date getMonthEndTime(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return getMonthEndTime(calendar);
    }

    public static Date getMonthEndTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getMonthEndTime(calendar);
    }

    public static Date getMonthStartTime(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return getMonthStartTime(calendar);
    }

    public static Date getMonthStartTime(Calendar calendar){
        Calendar tmpCalendar = (Calendar)calendar.clone();
        tmpCalendar.set(Calendar.DAY_OF_MONTH, 1);
        tmpCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tmpCalendar.set(Calendar.MINUTE, 0);
        tmpCalendar.set(Calendar.SECOND, 0);
        tmpCalendar.set(Calendar.MILLISECOND, 0);
        return tmpCalendar.getTime();
    }

    public static Date getMonthStartTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getMonthStartTime(calendar);
    }

    public static Date getDayEndTime(Calendar calendar){
        Calendar tmpCalendar = (Calendar)calendar.clone();
        tmpCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tmpCalendar.set(Calendar.MINUTE, 0);
        tmpCalendar.set(Calendar.SECOND, 0);
        tmpCalendar.set(Calendar.MILLISECOND, 0);

        tmpCalendar.add(Calendar.DATE, 1);
        tmpCalendar.add(Calendar.MILLISECOND, -1);
        return tmpCalendar.getTime();
    }

    public static Date getDayEndTime(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return getDayEndTime(calendar);
    }

    public static Date getDayEndTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getDayEndTime(calendar);
    }

    public static Date getDayEndTime(String date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateTimeUtils.convertStringToDateTime(DATE_PATTERN, date));
        return getDayEndTime(calendar);
    }

    public static Date getDayStartTime(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return getDayStartTime(calendar);
    }

    public static Date getDayStartTime(Calendar calendar){
        Calendar tmpCalendar = (Calendar)calendar.clone();
        tmpCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tmpCalendar.set(Calendar.MINUTE, 0);
        tmpCalendar.set(Calendar.SECOND, 0);
        tmpCalendar.set(Calendar.MILLISECOND, 0);
        return tmpCalendar.getTime();
    }

    public static Date getDayStartTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getDayStartTime(calendar);
    }

    public static Date getDayStartTime(String date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateTimeUtils.convertStringToDateTime(DATE_PATTERN, date));
        return getDayStartTime(calendar);
    }

    public static Date getWeekStartTime(Calendar calendar){
        Calendar tmpCalendar = (Calendar)calendar.clone();
        tmpCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tmpCalendar.set(Calendar.MINUTE, 0);
        tmpCalendar.set(Calendar.SECOND, 0);
        tmpCalendar.set(Calendar.MILLISECOND, 0);
        tmpCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        tmpCalendar.set(Calendar.DAY_OF_WEEK, tmpCalendar.getFirstDayOfWeek());
        return tmpCalendar.getTime();
    }
    
    public static Date getWeekStartTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getWeekStartTime(calendar);
    }

    public static Date getWeekEndTime(Calendar calendar){
        Calendar tmpCalendar = (Calendar)calendar.clone();
        tmpCalendar.set(Calendar.HOUR_OF_DAY, 0);
        tmpCalendar.set(Calendar.MINUTE, 0);
        tmpCalendar.set(Calendar.SECOND, 0);
        tmpCalendar.set(Calendar.MILLISECOND, 0);
        tmpCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        tmpCalendar.set(Calendar.DAY_OF_WEEK, tmpCalendar.getFirstDayOfWeek());
        tmpCalendar.add(Calendar.DATE, 7);
        tmpCalendar.set(Calendar.MILLISECOND, -1);
        return tmpCalendar.getTime();
    }
    
    public static Date getWeekEndTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getWeekEndTime(calendar);
    }

    public static String getDayOfWeekStr(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String str = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                str = "星期日";break;
            case 2:
                str = "星期一";break;
            case 3:
                str = "星期二";break;
            case 4:
                str = "星期三";break;
            case 5:
                str = "星期四";break;
            case 6:
                str = "星期五";break;
            case 7:
                str = "星期六";break;
            default:
                break;
        }
        return str;
    }

    public static String getDayOfWeekStrExt(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String str = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                str = "周日";break;
            case 2:
                str = "周一";break;
            case 3:
                str = "周二";break;
            case 4:
                str = "周三";break;
            case 5:
                str = "周四";break;
            case 6:
                str = "周五";break;
            case 7:
                str = "周六";break;
            default:
                break;
        }
        return str;
    }

    public static int getHourOfDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinuteOfDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    //---------------------------------------------------------
    
    public static Date getCurentWeekStartTime() {
        return getWeekStartTime(new Date());
    }

    public static Date getCurentWeekEndTime() {
        return getWeekEndTime(new Date());
    }

    public static Date getCurentMonthStartTime() {
        return getMonthStartTime(new Date());
    }

    public static Date getCurentMonthEndTime() {
        return getMonthEndTime(new Date());
    }


    public static String getTimeString(Long millisecond){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        return  (new SimpleDateFormat(DATE_PATTERN + " " + TIME_PATTERN)).format(calendar.getTime());
    }
}
