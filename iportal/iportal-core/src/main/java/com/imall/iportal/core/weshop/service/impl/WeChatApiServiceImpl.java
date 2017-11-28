package com.imall.iportal.core.weshop.service.impl;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.JsonBinder;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysParamInf;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.core.weshop.entity.WeChatUser;
import com.imall.iportal.core.weshop.service.WeChatApiService;
import com.imall.iportal.core.weshop.utils.WeChatWebUtil;
import com.imall.iportal.core.weshop.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class WeChatApiServiceImpl implements WeChatApiService {

    private static Logger logger = Logger.getLogger(WeChatApiServiceImpl.class.getName());
    private static WeChatGetAccessTokenResult weChatGetAccessTokenResult = null;
    private static final String WEI_XIN_API_GET_PARAM_LANGUAGE = "zh_CN"; //返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
    private static final String UTF_8 = "UTF-8";
    private static final String APP_ID = "appid";
    private static final String SECRET = "secret";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String OPEN_ID = "openid";
    private static final String SUCCESS = "success";
    private static int weChatAccessTokenTime = 0; //秒
    private static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    private static WeiXinGetJsApiTicketResult weiXinGetJsApiTicketResult = null;
    private static Long weixinGetJsapiTicketEffectiveTime = 0L;  //JsapiTicket 有效时间，毫秒
    private String token;

    @Value("${app.init.weixinconfig.service.token:}")
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public WeChatGetAccessTokenResult getWeChatGetAccessTokenResult() {
        return weChatGetAccessTokenResult;
    }

    @Override
    public void doWeChatGetAccessTokenResultReset() {
        weChatGetAccessTokenResult = null;
    }

    @Override
    public String getAccessToken() {
        if (weChatGetAccessTokenResult == null || StringUtils.isBlank(weChatGetAccessTokenResult.getAccess_token())) {
            refreshGetAccessToken();
        }
        if (weChatGetAccessTokenResult == null || StringUtils.isBlank(weChatGetAccessTokenResult.getAccess_token())) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "获取 Access_token失败");
        }
        return weChatGetAccessTokenResult.getAccess_token();
    }

    @Override
    public void refreshGetAccessToken() {
        SysParamInf appIdInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_APP_ID);
        if (appIdInf == null || StringUtils.isBlank(appIdInf.getParamValueStr())) {
            logger.info("获取微信access_token失败，请先配置appid");
            return;
        }
        SysParamInf secretInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_SECRET);
        if (secretInf == null || StringUtils.isBlank(secretInf.getParamValueStr())) {
            logger.info("获取微信access_token失败，请先配置secret");
            return;
        }
        Map<String, String> param = new HashMap<>();
        param.put("grant_type", "client_credential");
        param.put(APP_ID, appIdInf.getParamValueStr());
        param.put(SECRET, secretInf.getParamValueStr());
        String responseJson = null;
        try {
            responseJson = WeChatWebUtil.doGet("https://api.weixin.qq.com/cgi-bin/token", param, UTF_8, 3000, 3000);
        } catch (IOException e) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, e.getMessage());
        }
        weChatGetAccessTokenResult = JsonBinder.toBeanObject(responseJson, WeChatGetAccessTokenResult.class);
        if (weChatGetAccessTokenResult != null && StringUtils.isBlank(weChatGetAccessTokenResult.getAccess_token())) {
            weChatGetAccessTokenResult = null;
        }
        doWeChatAccessTokenTimeReset();
    }

    @Override
    public WeChatUserResult getUserByOpenId(String openid) {
        Map<String, String> param = new HashMap<>();
        param.put(ACCESS_TOKEN, getAccessToken());
        param.put(OPEN_ID, openid);
        param.put("lang", WEI_XIN_API_GET_PARAM_LANGUAGE);
        String responseJson = null;
        try {
            responseJson = WeChatWebUtil.doGet("https://api.weixin.qq.com/cgi-bin/user/info", param, UTF_8, 1000, 1000);
        } catch (IOException e) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, e.getMessage());
        }
        WeChatUserResult weChatUserResult = JsonBinder.toBeanObject(responseJson, WeChatUserResult.class);
        testResultError(weChatUserResult);
        return weChatUserResult;
    }

    @Override
    @Transactional
    public void weChatAutoLogin(String code) throws BusinessException, IOException {
        SysParamInf appIdInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_APP_ID);
        if (appIdInf == null || StringUtils.isBlank(appIdInf.getParamValueStr())) {
            logger.info("微信自动登录失败，请先配置appid");
            return;
        }
        SysParamInf secretInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_SECRET);
        if (secretInf == null || StringUtils.isBlank(secretInf.getParamValueStr())) {
            logger.info("微信自动登录失败，请先配置secret");
            return;
        }
        //获取权限
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token";
        StringBuilder buf = new StringBuilder();
        buf.append(urlStr)
                .append("?appid=").append(appIdInf.getParamValueStr())
                .append("&secret=").append(secretInf.getParamValueStr())
                .append("&code=").append(code)
                .append("&grant_type=authorization_code");

        String result = WeChatWebUtil.doGet(buf.toString(), UTF_8, 3000, 3000);
        Map responseMap = JsonBinder.toBeanObject(result, HashMap.class);
        if (responseMap == null || !responseMap.containsKey(OPEN_ID)) {
            return;
        }
        String openId = (String) responseMap.get(OPEN_ID);
        //TODO lxh 执行登录操作
    }

    @Override
    public String getShopPromoteQRCodeTicket(QRCodeTypeCodeEnum type, String sceneStr, Integer expireSeconds) throws IOException {
        WeChatQRCodeParams qrCodeParams = new WeChatQRCodeParams();
        WeChatQRCodeParams.ActionInfo actionInfo = new WeChatQRCodeParams.ActionInfo();
        WeChatQRCodeParams.ActionInfo.Scene scene = new WeChatQRCodeParams.ActionInfo.Scene();
        if (QRCodeTypeCodeEnum.QR_STR_SCENE == type) {
            qrCodeParams.setExpire_seconds(expireSeconds == null ? 2592000 : expireSeconds);
        }
        scene.setScene_str(sceneStr);
        qrCodeParams.setAction_name(type.toCode());
        actionInfo.setScene(scene);
        qrCodeParams.setAction_info(actionInfo);
        String reqBody = JsonBinder.toJson(qrCodeParams);

        Map result = null;
        Map<String, String> params = new HashMap<>();
        params.put(ACCESS_TOKEN, this.getAccessToken());
        result = JsonBinder.toBeanObject(WeChatWebUtil.doPost("https://api.weixin.qq.com/cgi-bin/qrcode/create", params, reqBody, UTF_8, 1000, 1000), Map.class);
        if (result == null){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "获取二维码ticket失败");
        }
        if (result.containsKey("errcode")) {
            testResultError(new WeChatBaseResult(Integer.valueOf(result.get("errcode").toString()), result.containsKey("errmsg") ? result.get("errmsg").toString() : ""));
        }
        return result.get("ticket").toString();
    }

    @Override
    public void doWeChatAccessTokenTimePlus() {
        weChatAccessTokenTime++;
    }

    @Override
    public Integer getWeChatAccessTokenTime() {
        return weChatAccessTokenTime;
    }

    @Override
    public void doWeChatAccessTokenTimeReset() {
        weChatAccessTokenTime = 0;
    }

    @Override
    public WeiXinJsSdkConfigVo getWeiXinJsConfig(String signUrl) {
        try {
            String ticket = ServiceManager.weChatApiService.getJsApiTicket();
            SysParamInf appIdInf = ServiceManager.sysParamInfService.findOneByOrgAndInnerCode(Global.ADMIN_DEFAULT_ORG_ID, Global.WE_CHAT_APP_ID);
            if (appIdInf == null || StringUtils.isBlank(appIdInf.getParamValueStr())) {
                logger.info("请先配置appid");
                return null;
            }
            WeiXinJsSdkConfigVo weiXinJsSdkConfigVo = new WeiXinJsSdkConfigVo();
            weiXinJsSdkConfigVo.setAppId(appIdInf.getParamValueStr());
            weiXinJsSdkConfigVo.setNonceStr(UUID.randomUUID().toString());
            weiXinJsSdkConfigVo.setTimestamp(Long.toString(System.currentTimeMillis() / 1000));
            StringBuffer signature = new StringBuffer("");
            signature.append("jsapi_ticket=");
            signature.append(ticket);
            signature.append("&noncestr=");
            signature.append(weiXinJsSdkConfigVo.getNonceStr());
            signature.append("&timestamp=");
            signature.append(weiXinJsSdkConfigVo.getTimestamp());
            signature.append("&url=");
            signature.append(signUrl);

            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(signature.toString().getBytes("UTF-8"));
            weiXinJsSdkConfigVo.setSignature(byteToHex(crypt.digest()));
            return weiXinJsSdkConfigVo;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.JS_SDK_INIT_ERRORS);
            throw new BusinessException(ResGlobalExt.JS_SDK_INIT_ERRORS, message);
        }
    }

    @Override
    public String getJsApiTicket() {
        if (checkJsapiTicketEffective()) {//有效
            return weiXinGetJsApiTicketResult.getTicket();
        } else {//失效
            Map<String, String> param = new HashMap<>();
            param.put("access_token", getAccessToken().trim());
            param.put("type", "jsapi");
            String responseJson = null;
            try {
                responseJson = WeChatWebUtil.doGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", param, UTF_8, 3000, 3000);
                if (StringUtils.isBlank(responseJson)) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.JS_API_TICKET_ERRORS);
                    throw new BusinessException(ResGlobalExt.JS_API_TICKET_ERRORS, message);
                }
                weiXinGetJsApiTicketResult = JsonBinder.toBeanObject(responseJson, WeiXinGetJsApiTicketResult.class);
                testResultError(weiXinGetJsApiTicketResult);
                Long currentTime = new Date().getTime();
                weixinGetJsapiTicketEffectiveTime = currentTime + weiXinGetJsApiTicketResult.getExpires_in() * 1000;
                return weiXinGetJsApiTicketResult.getTicket();
            } catch (IOException e) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.JS_API_TICKET_ERRORS);
                throw new BusinessException(ResGlobalExt.JS_API_TICKET_ERRORS, message);
            }
        }
    }

    @Override
    public String downloadImgFromWeiXin(String outputPath, String mediaId) {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("access_token", getAccessToken().trim());
            param.put("media_id", mediaId);
            return WeChatWebUtil.doGet("https://api.weixin.qq.com/cgi-bin/media/get", param, UTF_8, 3000, 3000, outputPath);
        } catch (IOException e) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.DOWNLOAD_ERROR);
            throw new BusinessException(ResGlobalExt.DOWNLOAD_ERROR, message);
        }
    }

    @Override
    public String getToken() {
        return token;
    }

    /**
     * 检验 jsapi_ticket 是否失效
     *
     * @return
     */
    private boolean checkJsapiTicketEffective() {
        Long currentTime = new Date().getTime();
        if (weiXinGetJsApiTicketResult == null
                || StringUtils.isBlank(weiXinGetJsApiTicketResult.getTicket())
                || currentTime > weixinGetJsapiTicketEffectiveTime) {
            return false;//失效
        } else {
            return true;//有效
        }
    }


    private static String byteToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @Override
    @Transactional
    public String acceptMsg(Map<String, String> map) throws BusinessException {
        String replyMsg = "";
        switch (MsgTypeEnum.fromCode(map.get("MsgType"))) {
            case EVENT:
                replyMsg = acceptEventMsg(map);
                break;
            default:
                break;
        }
        return StringUtils.isBlank(replyMsg) ? SUCCESS : replyMsg;
    }

    private String acceptEventMsg(Map<String, String> map) {
        String replyMsg = "";
        switch (MsgEventTypeEnum.fromCode(map.get("Event"))) {
            case SUBSCRIBE:
                replyMsg = this.afterSubscribe(map);
                break;
            case UNSUBSCRIBE:
                replyMsg = this.afterUnSubscribe(map);
                break;
            case SCAN:
                replyMsg = this.afterScan(map);
            default:
                break;
        }
        return replyMsg;
    }

    private String afterSubscribe(Map<String, String> map) {
        String openId = map.get("FromUserName").trim();
        WeChatUser weChatUser = this.saveOrUpdateWeChatUser(openId);
        if (StringUtils.isNotBlank(map.get("EventKey"))) {
            String[] eventKey = StringUtils.split(map.get("EventKey"), "_");
            if (eventKey.length == 2 && StringUtils.isNumeric(eventKey[1])) {
                Long shopId = Long.valueOf(eventKey[1]);
                this.saveOrUpdateFans(shopId, weChatUser);
            }
        }
        return SUCCESS;
    }

    private String afterUnSubscribe(Map<String, String> map) {
        String openId = map.get("FromUserName").trim();
        WeChatUser weChatUser = ServiceManager.weChatUserService.findByOpenId(openId);
        if (weChatUser != null) {
            weChatUser.setIsSubscribe(BoolCodeEnum.NO.toCode());
            ServiceManager.weChatUserService.save(weChatUser);
        }
        return SUCCESS;
    }

    private String afterScan(Map<String, String> map) {
        String openId = map.get("FromUserName").trim();
        WeChatUser weChatUser = this.saveOrUpdateWeChatUser(openId);
        String eventKey = map.get("EventKey");
        if (StringUtils.isNumeric(eventKey)) {
            Long shopId = Long.valueOf(eventKey);
            this.saveOrUpdateFans(shopId, weChatUser);
        }
        return SUCCESS;
    }

    private WeChatUser saveOrUpdateWeChatUser(String openId) {
        WeChatUser weChatUser = ServiceManager.weChatUserService.findByOpenId(openId);
        WeChatUserResult weChatUserResult = this.getUserByOpenId(openId);
        String nickName = StringUtils.isBlank(weChatUserResult.getNickname()) ? openId : WeChatWebUtil.formatNickname(weChatUserResult.getNickname());
        if (weChatUser == null) {
            weChatUser = new WeChatUser();
            weChatUser.setOpenId(openId);
            weChatUser.setIsSubscribe(BoolCodeEnum.YES.toCode());
            weChatUser.setMobile("");
            weChatUser.setNickName(nickName);
            weChatUser.setUserName(nickName);
            ServiceManager.weChatUserService.save(weChatUser);
        } else {
            //如果用户没更新用户名称
            if (weChatUser.getNickName().equals(weChatUser.getUserName())) {
                weChatUser.setUserName(nickName);
            }
            weChatUser.setIsSubscribe(BoolCodeEnum.YES.toCode());
            weChatUser.setNickName(nickName);
            ServiceManager.weChatUserService.save(weChatUser);
        }
        return weChatUser;
    }

    private void saveOrUpdateFans(Long shopId, WeChatUser weChatUser) {
        Fans fans = ServiceManager.fansService.findByOpenIdAndShopId(weChatUser.getOpenId(), shopId);
        String nickName = weChatUser.getNickName();
        if (fans == null) {
            fans = new Fans();
            fans.setWeChatUserId(weChatUser.getId());
            fans.setShopId(shopId);
            fans.setFansName(nickName);//姓名就是微信昵称
            fans.setBuyTimes(0L);
            fans.setIsMember(BoolCodeEnum.NO.toCode());
            fans.setMobile("");
            fans.setNickName(nickName);
            fans.setFansSourceCode(FansSourceCodeEnum.SHOP.toCode());
            fans.setOpenId(weChatUser.getOpenId());
            ServiceManager.fansService.save(fans);
            WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
            if (weShop != null){
                WechatTemplateMsgSendParam sendParam = new WechatTemplateMsgSendParam();
                sendParam.setTouser(weChatUser.getOpenId());
                Map<String, WechatTemplateMsgSendParam.DataVo> data = new HashMap<>();
                data.put("first", new WechatTemplateMsgSendParam.DataVo("您已关注了"+ weShop.getShopNm() +"，可预约门店送药上门哦！", "#173177"));
                data.put("keynote1", new WechatTemplateMsgSendParam.DataVo(weShop.getSellStartTime() + "-" + weShop.getSellEndTime() , "#173177"));
                String delivery = "";
                switch (DeliveryTypeCodeEnum.fromCode(weShop.getDeliveryTypeCode())){
                    case NEVER_PAY:
                        delivery = "免配送费";
                        break;
                    case NEED_PAY:
                        delivery = "配送费："+ weShop.getDeliveryAmount() +"元";
                        break;
                    case FULL_AMOUNT_NOT_PAY:
                        delivery = "满"+ weShop.getDeliveryMinOrderAmount() +"元免配送费";
                        break;
                    default:break;
                }
                data.put("keynote2", new WechatTemplateMsgSendParam.DataVo(delivery , "#173177"));
                data.put("keynote3", new WechatTemplateMsgSendParam.DataVo(weShop.getShopPromiseSendTime() == null ? "暂无" : weShop.getShopPromiseSendTime() + "小时内" , "#173177"));
                sendParam.setData(data);
                this.sendTemplateMsg(sendParam);
            }
        } else {
            fans.setNickName(nickName);
            ServiceManager.fansService.save(fans);
        }
    }

    /**
     * 检查处理请求返回结果
     *
     * @param weChatBaseResult
     * @throws BusinessException
     */
    private void testResultError(WeChatBaseResult weChatBaseResult) throws BusinessException {
        if (weChatBaseResult == null || weChatBaseResult.getErrcode() == null || weChatBaseResult.getErrcode() == 0) {
            return;
        }
        Integer errorCode = weChatBaseResult.getErrcode();
        //40001 42001是token问题导致，所以需要刷新token
        if (errorCode == 40001 || errorCode == 42001) {
            this.doWeChatGetAccessTokenResultReset();
        }
        throw new BusinessException(ResGlobal.COMMON_ERROR, "微信接口返回：errorMsg=" + weChatBaseResult.getErrmsg());
    }

    @Override
    public void sendTemplateMsg(WechatTemplateMsgSendParam wechatTemplateMsgSendParam) throws BusinessException {
        String rep = WeChatWebUtil.doPost(SEND_TEMPLATE_URL + getAccessToken(), JsonBinder.toJson(wechatTemplateMsgSendParam), UTF_8, 3000, 3000);
        logger.info("发送模板消息结果：" + rep);
    }
}
