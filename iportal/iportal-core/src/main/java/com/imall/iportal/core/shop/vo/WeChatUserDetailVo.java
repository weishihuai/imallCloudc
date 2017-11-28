package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.weshop.entity.WeChatUser;

/**
 * Created by wsh on 2017/8/19.
 */
public class WeChatUserDetailVo extends WeChatUser{
    /**
     * 图片路径
     */
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
