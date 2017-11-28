package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/5.
 * 商品货位 - 保存Vo对象
 */
public class StorageSpaceSaveVo {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 商品货位名称
     */
    @NotBlank
    @Length(max = 32)
    private String storageSpaceNm;
    /**
     * 货位类型
     */
    @NotBlank
    private String storageSpaceType;
    /**
     * 货位启用状态
     */
    @NotBlank
    private String isEnable;

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

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
