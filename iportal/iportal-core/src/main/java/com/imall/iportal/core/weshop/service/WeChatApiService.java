package com.imall.iportal.core.weshop.service;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.dicts.QRCodeTypeCodeEnum;
import com.imall.iportal.core.weshop.entity.WeChatUser;
import com.imall.iportal.core.weshop.vo.WeChatGetAccessTokenResult;
import com.imall.iportal.core.weshop.vo.WeChatUserResult;
import com.imall.iportal.core.weshop.vo.WechatTemplateMsgSendParam;
import com.imall.iportal.core.weshop.vo.WeiXinJsSdkConfigVo;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

public interface WeChatApiService {
    /**
     * 获取token
     * @return
     */
    String getAccessToken();

    /**
     * 重置token
     */
    void doWeChatGetAccessTokenResultReset();

    /**
     * 获取token请求结果
     * @return
     */
    WeChatGetAccessTokenResult getWeChatGetAccessTokenResult();

    /**
     * 刷新token
     */
    void refreshGetAccessToken();

    /**
     *  根据openId获取用户基本信息
     */
    WeChatUserResult getUserByOpenId(String openId);

    /**
     * 微信自动登录
     * @param code
     * @throws BusinessException
     * @throws IOException
     */
    void weChatAutoLogin(String code) throws BusinessException, IOException;

    /**
     * 接收消息
     * @param map
     * @return
     * @throws BusinessException
     */
    String acceptMsg(Map<String, String> map) throws BusinessException;

    /**
     *获取微信公众号的二维码ticket
     * @return
     */
    String getShopPromoteQRCodeTicket(@NotNull QRCodeTypeCodeEnum type, String sceneStr, Integer expireSeconds) throws IOException;

    void doWeChatAccessTokenTimePlus();

    Integer getWeChatAccessTokenTime();

    void doWeChatAccessTokenTimeReset();

    /**
     * 获取微信JS初始化配置信息
     * @param signUrl
     * @return
     */
    WeiXinJsSdkConfigVo getWeiXinJsConfig(String signUrl);

    /**
     * 获取 JsApi_ticket
     * @return
     */
    String getJsApiTicket();

    /**
     * 通过media_id下载图片
     * @param outputPath
     * @param mediaId
     * @return
     */
    String downloadImgFromWeiXin(String outputPath, String mediaId);

    String getToken();

    /**
     * 发送模板消息
     * @param param
     */
    void sendTemplateMsg(WechatTemplateMsgSendParam param);
}
