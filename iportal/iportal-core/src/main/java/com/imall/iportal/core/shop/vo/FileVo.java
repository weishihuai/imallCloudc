package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/8/12.
 */
public class FileVo {
    /**
     * 系统 文件 库 ID
     */
    @NotNull
    private java.lang.Long sysFileLibId;

    /**
     * 文件 ID
     */
    @NotBlank
    private java.lang.String fileId;

    /**
     * 文件 类型 代码
     */
    private java.lang.String fileTypeCode;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 文件名称
     */
    private String fileNm;

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

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileNm() {
        return fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }
}
