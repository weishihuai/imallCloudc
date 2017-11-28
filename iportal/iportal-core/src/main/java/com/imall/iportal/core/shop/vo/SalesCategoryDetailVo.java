package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.SalesCategory;

/**
 * Created by frt on 2017/7/6.
 */
public class SalesCategoryDetailVo extends SalesCategory {
    /**
     * 系统 文件 库 ID
     */
    private Long sysFileLibId;

    /**
     * 图片 文件 ID
     */
    private String pictFileId;

    /**
     * 图片完整路径
     */
    private String pictUrl;

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

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }
}
