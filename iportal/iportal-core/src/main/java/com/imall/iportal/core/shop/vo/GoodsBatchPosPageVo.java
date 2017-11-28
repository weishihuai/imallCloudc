package com.imall.iportal.core.shop.vo;

/**
 * Created by HWJ on 2017/7/21
 */
public class GoodsBatchPosPageVo {

    /**
     * 批次ID
     */
    private Long id;

    /**
     * 批次
     */
    private String batch;

    /**
     * 有效期
     */
    private String validDateString;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 货位Id
     */
    private Long storageSpaceId;

    /**
     * 货位名称
     */
    private String storageSpaceNm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getValidDateString() {
        return validDateString;
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }
}
