package com.imall.iportal.core.platform.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by caidapao on 2017/8/10.
 */
public class GoodsCategorySaveVo {
    /**
     * 父id
     */
    @NotNull
    private Long parentId;

    /**
     * 分类名称
     */
    @Length(max=32)
    @NotBlank
    private String categoryName;

    /**
     * 排序
     */
    @NotNull
    private Long displayPosition;

    public Long getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(Long displayPosition) {
        this.displayPosition = displayPosition;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
