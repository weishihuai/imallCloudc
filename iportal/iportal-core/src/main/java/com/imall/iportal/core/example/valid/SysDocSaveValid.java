package com.imall.iportal.core.example.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ygw on 2016/5/24.
 */
public class SysDocSaveValid implements Cloneable, java.io.Serializable{

    public static final long serialVersionUID = -15263788L;

    @NotBlank
    @Length(max = 128)
    private String title;

    @NotBlank
    @Length(max = 512)
    private String summary;

    @NotBlank
    @Length(max = 128)
    private String link;

    @NotBlank
    @Length(max = 32)
    private String releaseVersion;

    @NotBlank
    private String contStr;

    @NotNull
    private Long orderby;

    @NotBlank
    @Length(max = 1)
    private String isAvailable;
    private String createBy;
    private Date createDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private Long version;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
