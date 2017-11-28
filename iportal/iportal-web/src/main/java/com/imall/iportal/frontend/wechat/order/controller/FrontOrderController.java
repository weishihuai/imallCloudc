package com.imall.iportal.frontend.wechat.order.controller;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.iportal.frontend.wechat.WechatBaseController;
import com.imall.iportal.frontend.wechat.order.service.OrderProxyService;
import com.imall.iportal.frontend.wechat.order.vo.OrderBuyAgainVo;
import com.imall.iportal.frontend.wechat.order.vo.OrderStateUpdateVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

/**
 * 订单
 */
@Controller
@RequestMapping("/wechat/order")
public class FrontOrderController extends WechatBaseController {
    @RequestMapping(value="query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(@PageableDefault(page = 0, size = 10) Pageable pageable) throws ParseException {
        OrderProxyService orderProxyService = (OrderProxyService) SpringContextHolder.getBean(OrderProxyService.class);
        return orderProxyService.query(pageable);
    }

    @RequestMapping(value="findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id){
        OrderProxyService orderProxyService = (OrderProxyService) SpringContextHolder.getBean(OrderProxyService.class);
        return orderProxyService.findByIdAndWeChatUserId(id);
    }

    @RequestMapping(value = "/updateOrderState", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrderState(@RequestBody OrderStateUpdateVo orderStateUpdateVo){
        OrderProxyService orderProxyService = (OrderProxyService) SpringContextHolder.getBean(OrderProxyService.class);
        orderProxyService.updateOrderState(orderStateUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/buyAgain", method = RequestMethod.POST)
    @ResponseBody
    public Object buyAgain(Long id){
        OrderProxyService orderProxyService = (OrderProxyService) SpringContextHolder.getBean(OrderProxyService.class);
        orderProxyService.buyAgain(id);
        return getSuccess();
    }
}
