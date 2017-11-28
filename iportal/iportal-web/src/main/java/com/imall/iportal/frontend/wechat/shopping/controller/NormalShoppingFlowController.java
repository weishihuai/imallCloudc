package com.imall.iportal.frontend.wechat.shopping.controller;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.commons.base.vo.ResultVo;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.Order;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.shop.valid.NormalOrderSaveValid;
import com.imall.iportal.core.shop.vo.NormalShoppingCart;
import com.imall.iportal.core.weshop.web.vo.DeliveryTimeVo;
import com.imall.iportal.core.weshop.web.vo.OrderSaveVo;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.member.service.ReceiveAddrProxyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by ygw on 2017/8/21.
 * 微店：需求单
 */
@Controller
@RequestMapping("/wechat/normalShopping")
public class NormalShoppingFlowController extends BaseRestSpringController {


    @RequestMapping(value = "/getCart",method = RequestMethod.GET)
    @ResponseBody
    public Object getCart(ModelMap model){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        if (shopId == null){
            return new NormalShoppingCart();
        }
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.getCartByNewest(shopId, weChatUserId);
    }

    //http://localhost:8080/iportal/wechat/normalShopping/addItem.json?skuId=1&quantity=3
    @RequestMapping(value = "/addItem",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object addItem(ModelMap model, Long skuId, Long quantity){
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        Long shopId = WebContextFactory.getWebContext().getShopId();
        return ServiceManager.normalShoppingFlowService.addCartItem(shopId, weChatUserId, skuId, quantity, false);
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    @ResponseBody
    public Object select(ModelMap model, Long skuId){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.selectedChange(shopId, weChatUserId, skuId);
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    @ResponseBody
    public Object selectAll(ModelMap model){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.selectAll(shopId, weChatUserId);
    }

    @RequestMapping(value = "/unselectAll",method = RequestMethod.POST)
    @ResponseBody
    public Object unselectAll(ModelMap model){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.unselectAll(shopId, weChatUserId);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, Long skuId){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.addCartItem(shopId, weChatUserId, skuId, 1L, false);
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public Object count(){
        ResultVo resultVo = getSuccess();
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        if (shopId == null || weChatUserId == null){
            resultVo.setMsg("0");
        }else {
            resultVo.setMsg(ServiceManager.normalShoppingFlowService.getGoodsCount(shopId, weChatUserId).toString());
        }
        return resultVo;
    }

    @RequestMapping(value = "/subtract",method = RequestMethod.POST)
    @ResponseBody
    public Object subtract(ModelMap model, Long skuId){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.addCartItem(shopId, weChatUserId, skuId, -1L, false);
    }

    @RequestMapping(value = "/changeQuantity",method = RequestMethod.POST)
    @ResponseBody
    public Object changeQuantity(ModelMap model, Long skuId, Long quantity){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.addCartItem(shopId, weChatUserId, skuId, quantity, true);
    }


    @RequestMapping(value = "/deleteSelected",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteSelected(ModelMap model, @RequestBody List<Long> ids){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return ServiceManager.normalShoppingFlowService.batchRemoveCartItem(shopId, weChatUserId, ids);
    }

    @RequestMapping(value = "/getDeliveryTime",method = RequestMethod.GET)
    @ResponseBody
    public Object getDeliveryTime(ModelMap model){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        DeliveryTimeVo deliveryTimeVo = new DeliveryTimeVo();
        deliveryTimeVo.setToday("今天[" + DateTimeUtils.getDayOfWeekStrExt(new Date()) + "]");
        deliveryTimeVo.setTomorrow("明天[" + DateTimeUtils.getDayOfWeekStrExt(DateTimeUtils.addDays(new Date(), 1)) + "]");
        WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
        Date now = new Date();
        String sellStartTime = weShop.getSellStartTime();
        String sellEndTime = weShop.getSellEndTime();
        int startHour = Integer.valueOf(sellStartTime.split(":")[0]);
        int startMinute = Integer.valueOf(sellStartTime.split(":")[1]);
        int endHour = Integer.valueOf(sellEndTime.split(":")[0]);
        int endMinute = Integer.valueOf(sellEndTime.split(":")[1]);
        int currMinute = DateTimeUtils.getMinuteOfDay(now);
        int currHour = DateTimeUtils.getHourOfDay(now);
        if (startHour == currHour && startMinute > currMinute){
            deliveryTimeVo.setInBusinessTime(false);
        }
        if (startHour > currHour){
            deliveryTimeVo.setInBusinessTime(false);
        }
        if (endHour == currHour && endMinute < currMinute){
            deliveryTimeVo.setInBusinessTime(false);
        }
        if (endHour < currHour){
            deliveryTimeVo.setInBusinessTime(false);
        }
        if(startMinute>0){
            startHour = startHour + 1;
        }
        //明天
        String[] tomorrowTimes = new String[endHour-startHour];
        int index = 0;
        for(int i=startHour; i<endHour; i++){
            tomorrowTimes[index] = (i < 10 ? "0" + i : i) + ":00-" + (i + 1) + ":00";
            index++;
        }

        //今天(从当前时间开始)
        if(startHour < currHour){
            startHour = currHour;
        }
        //如果现在已经超过30分钟了，则起送时间要往后加一小时
        if (startHour == currHour && currMinute > 30){
            startHour++;
        }
        int len = endHour-startHour;
        String[] todayTimes = new String[len >= 0 ? len : 0];
        index = 0;
        for(int i=startHour; i<endHour; i++){
            todayTimes[index] = (i < 10 ? "0" + i : i) + ":00-" + (i + 1) + ":00";
            index++;
        }
        deliveryTimeVo.setTodayHours(todayTimes);
        deliveryTimeVo.setTomorrowHours(tomorrowTimes);
        return deliveryTimeVo;
    }


    @RequestMapping(value = "/saveOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrder(ModelMap model, @RequestBody OrderSaveVo orderSaveVo){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        NormalShoppingCart shoppingCart = ServiceManager.normalShoppingFlowService.getCartByNewest(shopId, weChatUserId);
        Date startDate = null;
        Date endDate = null;
        if(!orderSaveVo.getDeliveryTime().trim().equals("尽快送达")){
            String startTime = orderSaveVo.getDeliveryTime().split("-")[0] + ":00";
            String endTime = orderSaveVo.getDeliveryTime().split("-")[1] + ":00";
            String date = orderSaveVo.getIsToday() ? DateTimeUtils.convertDateToString(new Date()) : DateTimeUtils.convertDateToString(DateTimeUtils.addDays(new Date(), 1));

            try {
                startDate = DateTimeUtils.convertStringToDateTime(date + " " + startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            try {
                endDate = DateTimeUtils.convertStringToDateTime(date + " " + endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        NormalOrderSaveValid orderSaveValid = new NormalOrderSaveValid();
        orderSaveValid.setRemark(orderSaveVo.getRemark());
        orderSaveValid.setBookDeliveryTimeStart(startDate);
        orderSaveValid.setBookDeliveryTimeEnd(endDate);
        ResultVo resultVo = getSuccess();
        Order order = ServiceManager.orderService.saveNormalOrder(shoppingCart, orderSaveValid);
        resultVo.setMsg(String.valueOf(order.getId()));
        return resultVo;
    }

    @RequestMapping(value = "/saveReceiverAddress",method = RequestMethod.POST)
    @ResponseBody
    public Object saveReceiverAddress(ModelMap model, @RequestBody Long id){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        ServiceManager.normalShoppingFlowService.saveReceiverAddr(shopId, weChatUserId, id);
        return getSuccess();
    }

    @RequestMapping(value = "/saveDefaultReceiverAddress",method = RequestMethod.POST)
    @ResponseBody
    public Object saveDefaultReceiverAddress(ModelMap model){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        NormalShoppingCart normalShoppingCart = ServiceManager.normalShoppingFlowService.saveDefaultReceiverAddr(shopId, weChatUserId);
        return normalShoppingCart;
    }

    @RequestMapping(value = "/cartListAddr",method = RequestMethod.GET)
    @ResponseBody
    public Object cartListAddr(){
        return ((ReceiveAddrProxyService) SpringContextHolder.getBean(ReceiveAddrProxyService.class)).cartListAddr();
    }
}
