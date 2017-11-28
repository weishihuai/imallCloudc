package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/18.
 * 微信用户 昵称更新VO
 */
public class WeChatUserNickNameUpdateVo {
    /**
     * 主键ID
     */
    @NotNull
    private Long id;
    /**
     * 昵称
     */
    @NotBlank
    @Length(max = 32)
    private String nickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
