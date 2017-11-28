package com.imall.iportal.core.weshop.web.vo;

/**
 * Created by ygw on 2017/8/23.
 */
public class DeliveryTimeVo {

    private String today;
    private String tomorrow;
    private String[] todayHours;
    private String[] tomorrowHours;
    private boolean inBusinessTime = true;//是否在营业时间

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(String tomorrow) {
        this.tomorrow = tomorrow;
    }

    public String[] getTodayHours() {
        return todayHours;
    }

    public void setTodayHours(String[] todayHours) {
        this.todayHours = todayHours;
    }

    public String[] getTomorrowHours() {
        return tomorrowHours;
    }

    public void setTomorrowHours(String[] tomorrowHours) {
        this.tomorrowHours = tomorrowHours;
    }

    public boolean isInBusinessTime() {
        return inBusinessTime;
    }

    public void setInBusinessTime(boolean inBusinessTime) {
        this.inBusinessTime = inBusinessTime;
    }
}
