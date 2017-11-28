package com.imall.iportal.frontend.wechat.member.controller;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.core.shop.vo.ReceiverAddrSaveVo;
import com.imall.iportal.core.shop.vo.ReceiverAddrUpdateVo;
import com.imall.iportal.frontend.wechat.WechatBaseController;
import com.imall.iportal.frontend.wechat.member.service.ReceiveAddrProxyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lxh on 2017/8/12.
 */
@Controller
@RequestMapping("/wechat/receiveAddr")
public class FrontReceiveAddrController extends WechatBaseController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody ReceiverAddrSaveVo saveVo) {
        ((ReceiveAddrProxyService) SpringContextHolder.getBean(ReceiveAddrProxyService.class)).saveReceiveAddr(saveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object update(Long id) {
        ((ReceiveAddrProxyService) SpringContextHolder.getBean(ReceiveAddrProxyService.class)).deleteReceiveAddr(id);
        return getSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody ReceiverAddrUpdateVo updateVo) {
        ((ReceiveAddrProxyService) SpringContextHolder.getBean(ReceiveAddrProxyService.class)).updateReceiveAddr(updateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        return ((ReceiveAddrProxyService) SpringContextHolder.getBean(ReceiveAddrProxyService.class)).findByWeChatUserId();
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id) {
        return ((ReceiveAddrProxyService) SpringContextHolder.getBean(ReceiveAddrProxyService.class)).findOne(id);
    }
}
