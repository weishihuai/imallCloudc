package com.imall.iportal.core.elasticsearch.entity;

/**
 * Created by ZJC on 2016/8/31.
 */
public class SysDocEntity {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String SUMMARY = "summary";
    public static final String LINK = "link";
    public static final String RELEASE_VERSION = "releaseVersion";
    public static final String CONT_STR = "contStr";
    public static final String ORDERBY = "orderby";

    private Long id;
    private String title;
    private String summary;
    private String link;
    private String releaseVersion;
    private String isAvailable;
    private String contStr;
    private Long orderby;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getContStr() {
        return contStr;
    }

    public void setContStr(String contStr) {
        this.contStr = contStr;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
}
