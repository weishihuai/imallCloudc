package com.imall.iportal.core.main.vo;


import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.iportal.core.main.entity.FileMng;

/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class FileMngVo extends FileMng {

    /**
     * 文件名
     */
    private String fileNm;

    public String getFileNm() {
        return fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    public String getFileUrl() {
        return FileSystemEngine.getFileSystem().getUrl(this.getFileId());
    }
}
