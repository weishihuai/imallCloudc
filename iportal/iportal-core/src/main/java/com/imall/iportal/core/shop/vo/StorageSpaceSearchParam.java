package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/5.
 * 商品货位列表搜索参数
 */
public class StorageSpaceSearchParam {
    /**
     * 商品货位名称
     */
    private String storageSpaceNm;
    /**
     * 商品货位类型
     */
    private String storageSpaceType;
    /**
     * 是否启用状态
     */
    private String enableStateCode;
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public String getStorageSpaceType() {
        return storageSpaceType;
    }

    public void setStorageSpaceType(String storageSpaceType) {
        this.storageSpaceType = storageSpaceType;
    }

    public String getEnableStateCode() {
        return enableStateCode;
    }

    public void setEnableStateCode(String enableStateCode) {
        this.enableStateCode = enableStateCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
