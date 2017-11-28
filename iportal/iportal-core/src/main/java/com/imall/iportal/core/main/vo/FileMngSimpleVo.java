package com.imall.iportal.core.main.vo;

/**
 * Created by lxh on 2017/8/29.
 */
public class FileMngSimpleVo {
    private String objectTypeCode;

    private Long objectId;

    private String fileTypeCode;

    private Long sysFileLibId;

    private String fileId;

    private String smallFileUrl;

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

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public Long getSysFileLibId() {
        return sysFileLibId;
    }

    public void setSysFileLibId(Long sysFileLibId) {
        this.sysFileLibId = sysFileLibId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getSmallFileUrl() {
        return smallFileUrl;
    }

    public void setSmallFileUrl(String smallFileUrl) {
        this.smallFileUrl = smallFileUrl;
    }
}
