package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.main.entity.FileMng;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/8/18.
 * 微信用户 昵称更新VO
 */
public class WeChatUserImageUpdateVo {
    /**
     * 主键ID
     */
    @NotNull
    private Long id;
    /**
     * 头像图片
     */
    private List<FileMng> pictFileList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FileMng> getPictFileList() {
        return pictFileList;
    }

    public void setPictFileList(List<FileMng> pictFileList) {
        this.pictFileList = pictFileList;
    }
}
