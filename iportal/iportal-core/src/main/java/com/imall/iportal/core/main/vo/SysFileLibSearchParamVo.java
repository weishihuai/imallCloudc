package com.imall.iportal.core.main.vo;

/**
 * Created by ZJC on 2016/1/4.
 */
public class SysFileLibSearchParamVo {
    private int pageNumber;
    private int pageSize;
    private long fileCategoryId;
    private String fileTypeCode;
    private String searchContent;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getFileCategoryId() {
        return fileCategoryId;
    }

    public void setFileCategoryId(long fileCategoryId) {
        this.fileCategoryId = fileCategoryId;
    }

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
