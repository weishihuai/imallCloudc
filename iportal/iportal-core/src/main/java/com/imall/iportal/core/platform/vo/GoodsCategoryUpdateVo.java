package com.imall.iportal.core.platform.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by caidapao on 2017/8/10.
 */
public class GoodsCategoryUpdateVo {

    /**
     * id
     */
    @NotNull
    private Long id;

    /**
     * 分类名称
     */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
