package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/7/5.
 * 商品货位简单vo  用于商品管理列表搜索
 */
public class StorageSpaceSimpleVo {

    /**
     * 货位ID
     */
    private Long id;

    /**
     * 货位名称
     */
    private String storageSpaceNm;
    /**
     * 货位 类型
     */
    private String storageSpaceType;

    public String getStorageSpaceType() {
        return storageSpaceType;
    }

    public void setStorageSpaceType(String storageSpaceType) {
        this.storageSpaceType = storageSpaceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }
}
