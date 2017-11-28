package com.imall.iportal.core.main.vo;


public class SysFileLibVo {
    private Long id;
    private String sysFileId;
    private String fileNm;
    private String fileTypeCode;
    private Integer fileSize;
    private String fileDate;
    private Long fileCategoryId;
    private String sysFileUrl;
    private String smallFileUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmallFileUrl() {
        return smallFileUrl;
    }

    public void setSmallFileUrl(String smallFileUrl) {
        this.smallFileUrl = smallFileUrl;
    }

    public String getSysFileId() {
        return sysFileId;
    }

    public void setSysFileId(String sysFileId) {
        this.sysFileId = sysFileId;
    }

    public String getFileNm() {
        return fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public Long getFileCategoryId() {
        return fileCategoryId;
    }

    public void setFileCategoryId(Long fileCategoryId) {
        this.fileCategoryId = fileCategoryId;
    }

    public String getSysFileUrl() {
        return sysFileUrl;
    }

    public void setSysFileUrl(String sysFileUrl) {
        this.sysFileUrl = sysFileUrl;
    }
}
