package com.imall.iportal.frontend.wechat.goods.controller;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.core.shop.vo.WeChatGoodsListSearchParam;
import com.imall.iportal.frontend.wechat.WechatBaseController;
import com.imall.iportal.frontend.wechat.goods.service.GoodsProxyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lxh on 2017/8/12.
 */
@Controller
@RequestMapping("/wechat/goods")
public class FrontGoodsController extends WechatBaseController {

    @ResponseBody
    @RequestMapping("/list")
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, WeChatGoodsListSearchParam weChatGoodsListSearchParam) {
        GoodsProxyService goodsProxyService = (GoodsProxyService) SpringContextHolder.getBean(GoodsProxyService.class);
        return goodsProxyService.query(pageable, weChatGoodsListSearchParam);
    }

    @ResponseBody
    @RequestMapping("/detail")
    public Object detail(Long id) {
        GoodsProxyService goodsProxyService = (GoodsProxyService) SpringContextHolder.getBean(GoodsProxyService.class);
        return goodsProxyService.findDetail(id);
    }

}
