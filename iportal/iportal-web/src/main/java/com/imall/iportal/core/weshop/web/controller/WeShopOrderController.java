package com.imall.iportal.core.weshop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.weshop.vo.*;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;

@RequestMapping("/weshopOrder")
@Controller
public class WeShopOrderController extends BaseRestSpringController {
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public Object query(@PageableDefault(page = 0, size = 10) Pageable pageable, OrderSearchParam orderSearchParam, @CurrUser CurrUserVo currUserVo) throws ParseException {
        if(currUserVo.getWeShopId()==null){
            return new PageImpl<OrderPageVo>(new ArrayList<OrderPageVo>(), pageable, 0);
        }else{
            orderSearchParam.setWeShopId(currUserVo.getWeShopId());
            orderSearchParam.setOrderSourceCode(OrderSourceCodeEnum.WEIXIN.toCode());
            return ServiceManager.weShopOrderService.query(pageable, orderSearchParam);
        }
    }

    @RequestMapping(value="/getOrderStatistics", method = RequestMethod.GET)
    @ResponseBody
    public Object getOrderStatistics(@CurrUser CurrUserVo currUserVo){
        return ServiceManager.weShopOrderService.getOrderStatistics(currUserVo.getWeShopId());
    }

    @RequestMapping(value="/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
        return ServiceManager.weShopOrderService.findById(currUserVo.getWeShopId(), id);
    }

    @RequestMapping(value="/updateOrderState", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrderState(@RequestBody OrderStateSaveVo orderStateSaveVo, @CurrUser CurrUserVo currUserVo){
        orderStateSaveVo.setWeShopId(currUserVo.getWeShopId());
        ServiceManager.weShopOrderService.updateOrderState(orderStateSaveVo);
        return getSuccess();
    }

    @RequestMapping(value="/updateOrderRemark", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrderRemark(@RequestBody OrderRemarkSaveVo orderRemarkSaveVo, @CurrUser CurrUserVo currUserVo){
        orderRemarkSaveVo.setWeShopId(currUserVo.getWeShopId());
        ServiceManager.weShopOrderService.updateOrderRemark(orderRemarkSaveVo);
        return getSuccess();
    }

    @RequestMapping(value="/findOrderItemSendDetail", method = RequestMethod.GET)
    @ResponseBody
    public Object findOrderItemSendDetail(Long orderId, @CurrUser CurrUserVo currUserVo){
        return ServiceManager.weShopOrderService.findOrderItemSendDetail(currUserVo.getWeShopId(), orderId);
    }

    @RequestMapping(value="/updateOrderToSend", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrderToSend(@RequestBody OrderSendVo orderSendVo, @CurrUser CurrUserVo currUserVo){
        orderSendVo.setWeShopId(currUserVo.getWeShopId());
        orderSendVo.setOpenOrderManId(currUserVo.getUserId());
        ServiceManager.weShopOrderService.updateOrderToSend(orderSendVo);
        return getSuccess();
    }

    @RequestMapping(value="/printOrderSmallTicket", method = RequestMethod.GET)
    @ResponseBody
    public Object printOrderSmallTicket(Long orderId, @CurrUser CurrUserVo currUserVo){
        ServiceManager.weShopOrderService.printOrderSmallTicket(orderId, currUserVo.getUserName());
        return getSuccess();
    }
}
