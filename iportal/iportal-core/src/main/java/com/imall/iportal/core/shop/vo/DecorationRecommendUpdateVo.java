package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by lxh on 2017/8/19.
 */
public class DecorationRecommendUpdateVo {
    //主键ID
    @NotNull
    private Long id;
    //排序
    @NotNull
    private Long displayPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(Long displayPosition) {
        this.displayPosition = displayPosition;
    }
}
