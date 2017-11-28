package com.imall.iportal.frontend.wechat.common.controller;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.dicts.ValidateCodeTypeEnum;
import com.imall.iportal.frontend.wechat.WechatBaseController;
import com.imall.iportal.frontend.wechat.common.utils.FrontendUtils;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import com.imall.iportal.frontend.wechat.member.service.WeChatUserProxyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by wsh on 2017/8/19.
 */
@Controller
@RequestMapping("/wechat/common")
public class CommonController extends WechatBaseController {

    @RequestMapping(value = "/getWeiXinJsConfig", method = RequestMethod.GET)
    @ResponseBody
    public Object getWeiXinJsConfig(ModelMap model, String signUrl) throws UnsupportedEncodingException {
        signUrl = URLDecoder.decode(signUrl, "UTF-8");
        return ServiceManager.weChatApiService.getWeiXinJsConfig(signUrl);
    }

    @RequestMapping(value = "/getLoginUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getLoginUserInfo() throws UnsupportedEncodingException {
        return ((WeChatUserProxyService) SpringContextHolder.getBean(WeChatUserProxyService.class)).getLoginUserInfo();
    }

    @RequestMapping(value = "/sendNormalValidateCode", method = RequestMethod.GET)
    @ResponseBody
    public Object sendNormalValidateCode(ModelMap model, String mobile) throws UnsupportedEncodingException {
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        if (weChatUserProxy == null){
            throw new IllegalArgumentException("用户未登录");
        }
        if (StringUtils.isBlank(mobile)){
            throw new IllegalArgumentException("请输入手机号");
        }
        String reqIp = FrontendUtils.getRealIp(WebContextFactory.getWebContext().getRequest());
        ServiceManager.smsQueueService.saveValidateCodeSmsQueue(mobile, ValidateCodeTypeEnum.NORMAL, weChatUserProxy.getId(), reqIp);
        return getSuccess();
    }

}
