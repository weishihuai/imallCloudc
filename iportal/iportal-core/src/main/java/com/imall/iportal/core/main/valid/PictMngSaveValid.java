package com.imall.iportal.core.main.valid;

import java.util.Date;

/**
 * Created by ygw on 2016/10/20.
 */
public class PictMngSaveValid implements Cloneable, java.io.Serializable{

    public static final long serialVersionUID = -15263788L;

    private String objectTypeCode;
    private Long objectId;
    private Long sysFileLibId;
    private String pictFileId;
    private Long displayPosition;
    private String createBy;
    private Date createDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private Long version;

    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getSysFileLibId() {
        return sysFileLibId;
    }

    public void setSysFileLibId(Long sysFileLibId) {
        this.sysFileLibId = sysFileLibId;
    }

    public String getPictFileId() {
        return pictFileId;
    }

    public void setPictFileId(String pictFileId) {
        this.pictFileId = pictFileId;
    }

    public Long getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(Long displayPosition) {
        this.displayPosition = displayPosition;
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
