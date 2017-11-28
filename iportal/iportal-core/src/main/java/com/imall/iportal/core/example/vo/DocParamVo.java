package com.imall.iportal.core.example.vo;

/**
 * Created by yang on 2015-11-19.
 */
public class DocParamVo {

    private String title;
    private String summary;
    private String contStr;
    private String isAvailable;

    public DocParamVo() {
    }

    public DocParamVo(String title, String summary, String contStr, String isAvailable) {
        this.title = title;
        this.summary = summary;
        this.contStr = contStr;
        this.isAvailable = isAvailable;
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

    public String getContStr() {
        return contStr;
    }

    public void setContStr(String contStr) {
        this.contStr = contStr;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
}
