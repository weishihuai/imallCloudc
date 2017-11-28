package com.imall.iportal.frontend.wechat.member.controller;
import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.commons.base.vo.ResultVo;
import com.imall.iportal.core.shop.vo.WeChatUserNickNameUpdateVo;
import com.imall.iportal.frontend.wechat.WechatBaseController;
import com.imall.iportal.frontend.wechat.member.service.WeChatUserProxyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

/**
 * Created by wsh on 2017/8/18.
 * 微信端 微信用户Controller
 */
@Controller
@RequestMapping("/wechat/weChatUser")
public class FrontWeChatUserController extends WechatBaseController {

    @RequestMapping(value = "/findWeChatUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object findWeChatUserInfo() {
        WeChatUserProxyService weChatUserProxyService = (WeChatUserProxyService) SpringContextHolder.getBean(WeChatUserProxyService.class);
        return weChatUserProxyService.findWeChatUserInfo();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById() {
        WeChatUserProxyService weChatUserProxyService = (WeChatUserProxyService) SpringContextHolder.getBean(WeChatUserProxyService.class);
        return weChatUserProxyService.findById();
    }

    @RequestMapping(value = "/updateNickName", method = RequestMethod.POST)
    @ResponseBody
    public Object updateNickName(@RequestBody WeChatUserNickNameUpdateVo weChatUserNickNameUpdateVo) {
        WeChatUserProxyService weChatUserProxyService = (WeChatUserProxyService) SpringContextHolder.getBean(WeChatUserProxyService.class);
        weChatUserProxyService.updateNickName(weChatUserNickNameUpdateVo);
        return getSuccess();
    }

    /**
     * 从微信下载图片
     *
     * @param model
     * @param mediaId
     * @throws IOException
     */
    @RequestMapping(value = "/uploadUserIcoFromWeiXin", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadUserIcoFromWeiXin(ModelMap model, String mediaId) throws IOException {
        String url = ((WeChatUserProxyService) SpringContextHolder.getBean(WeChatUserProxyService.class)).downloadWeChatFile(mediaId);
        ResultVo resultVo = getSuccess();
        resultVo.setMsg(url);
        return resultVo;
    }

    @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
    @ResponseBody
    public Object bindMobile(String mobile, String validateCode) {
        ((WeChatUserProxyService) SpringContextHolder.getBean(WeChatUserProxyService.class)).bindMobile(mobile, validateCode);
        return getSuccess();
    }
}
