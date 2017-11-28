package com.imall.iportal.frontend.wechat.weshop.controller;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.frontend.wechat.WechatBaseController;
import com.imall.iportal.frontend.wechat.weshop.service.WeShopProxyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wsh on 2017/8/22.
 * 微信端 微门店Controller
 */
@Controller
@RequestMapping("/wechat/weShop")
public class FrontWeShopController extends WechatBaseController {

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findByShopId(Long id) {
        WeShopProxyService weShopProxyService = (WeShopProxyService) SpringContextHolder.getBean(WeShopProxyService.class);
        return weShopProxyService.findById(id);
    }

}
