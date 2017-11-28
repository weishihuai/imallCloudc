package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/6.
 */
public class SalesCategorySaveVo {
    /**
     * 分类 名称
     */
    @NotBlank
    @Length(max = 64)
    private String categoryName;

    /**
     * 是否 前台 展示
     */
    @NotBlank
    @Length(max = 1)
    private String isFrontendShow;

    /**
     * 显示 排序
     */
    @NotNull
    private Long dispalyPosition;

    /**
     *是否 启用
     */
    @NotBlank
    @Length(max = 1)
    private String isEnable;

    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 系统 文件 库 ID
     */
    private Long sysFileLibId;

    /**
     * 图片 文件 ID
     */
    private String pictFileId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIsFrontendShow() {
        return isFrontendShow;
    }

    public void setIsFrontendShow(String isFrontendShow) {
        this.isFrontendShow = isFrontendShow;
    }

    public Long getDispalyPosition() {
        return dispalyPosition;
    }

    public void setDispalyPosition(Long dispalyPosition) {
        this.dispalyPosition = dispalyPosition;
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

    public Long getSysFileLibId() {
        return sysFileLibId;
    }

    public void setSysFileLibId(Long sysFileLibId) {
        this.sysFileLibId = sysFileLibId;
    }

    public String getPictFileId() {
        return pictFileId;
    }

    public void setPictFileId(String pictFileId) {
        this.pictFileId = pictFileId;
    }
}
