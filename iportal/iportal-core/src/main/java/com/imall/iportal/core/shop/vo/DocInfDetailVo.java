package com.imall.iportal.core.shop.vo;

/**
 * Created by frt on 2017/8/10.
 */
public class DocInfDetailVo {
    private Long id;

    /**
     * 序号
     */
    private Double seqnum;

    /**
     * 表格 名称
     */
    private String tableNm;

    /**
     * 下载 地址
     */
    private String downloadAddr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSeqnum() {
        return seqnum;
    }

    public void setSeqnum(Double seqnum) {
        this.seqnum = seqnum;
    }

    public String getTableNm() {
        return tableNm;
    }

    public void setTableNm(String tableNm) {
        this.tableNm = tableNm;
    }

    public String getDownloadAddr() {
        return downloadAddr;
    }

    public void setDownloadAddr(String downloadAddr) {
        this.downloadAddr = downloadAddr;
    }
}
