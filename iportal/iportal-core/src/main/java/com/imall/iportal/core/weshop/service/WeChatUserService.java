package com.imall.iportal.core.weshop.service;


import com.imall.iportal.core.shop.vo.WeChatUserNickNameUpdateVo;
import com.imall.iportal.core.weshop.entity.WeChatUser;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface WeChatUserService {

    WeChatUser findByOpenId(@NotBlank String openId);

    WeChatUser save(@NotNull WeChatUser weChatUser);

    WeChatUser findOne(@NotNull Long id);

    /**
     * 更新微信用户昵称
     *
     * @param weChatUserNickNameUpdateVo
     */
    void updateNickName(@NotNull @Valid WeChatUserNickNameUpdateVo weChatUserNickNameUpdateVo);

    /**
     * 根据电话号码查找
     * @param mobile
     * @return
     */
    WeChatUser findByMobile(@NotBlank String mobile);

    /**
     * 绑定手机号
     * @param id
     * @param mobile
     */
    void updateMobile(@NotNull Long id, @NotBlank String mobile);
}
