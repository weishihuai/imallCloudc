package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.valid.SalesPosOrderSaveValid;
import com.imall.iportal.core.shop.vo.ShoppingCart;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HWJ on 2017/7/21
 */
@Controller
@RequestMapping("/salesPosCart")
public class SalesPosShoppingFlowController {

    @RequestMapping(value = "/getCart",method = RequestMethod.GET)
    @ResponseBody
    public Object getCart(@CurrUser CurrUserVo currUserVo){
        return ServiceManager.salesPosShoppingFlowService.getCartOrNewCart(currUserVo.getShopId());
    }

    @RequestMapping(value = "/addItem",method = RequestMethod.POST)
    @ResponseBody
    public Object addItem(@CurrUser CurrUserVo currUserVo, String uniqueKey, Long skuId, String batch, Long quantity){
        checkIsShowCart(currUserVo.getShopId(), uniqueKey);
        return ServiceManager.salesPosShoppingFlowService.addCartItem(uniqueKey, skuId, batch, quantity, false);
    }

    @RequestMapping(value = "/updateQuantity",method = RequestMethod.POST)
    @ResponseBody
    public Object updateQuantity(@CurrUser CurrUserVo currUserVo, String uniqueKey, Long skuId, String batch, Long quantity){
        checkIsShowCart(currUserVo.getShopId(), uniqueKey);
        return ServiceManager.salesPosShoppingFlowService.addCartItem(uniqueKey, skuId, batch, quantity, true);
    }

    @RequestMapping(value = "/removeCartItem",method = RequestMethod.POST)
    @ResponseBody
    public Object removeCartItem(@CurrUser CurrUserVo currUserVo, String uniqueKey, Long skuId, String batch){
        checkIsShowCart(currUserVo.getShopId(), uniqueKey);
        return ServiceManager.salesPosShoppingFlowService.removeCartItem(uniqueKey, skuId, batch);
    }

    @RequestMapping(value = "/clearCart",method = RequestMethod.POST)
    @ResponseBody
    public Object clearCart(@CurrUser CurrUserVo currUserVo, String uniqueKey){
        checkIsShowCart(currUserVo.getShopId(), uniqueKey);
        return ServiceManager.salesPosShoppingFlowService.clearCart(currUserVo.getShopId(), uniqueKey);
    }

    @RequestMapping(value = "/saveSalesPosOrder",method = RequestMethod.POST)
    @ResponseBody
    public Object saveSalesPosOrder(@CurrUser CurrUserVo currUserVo, String uniqueKey, Double healthInsurancePayAmount, Double cashPayAmount, String paymentWayCode){
        ShoppingCart cart = ServiceManager.salesPosShoppingFlowService.getCart(uniqueKey);
        if(!cart.getShopId().equals(currUserVo.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        SalesPosOrderSaveValid saveValid = new SalesPosOrderSaveValid();
        saveValid.setMedicalInsurancePaymentAmount(healthInsurancePayAmount);
        saveValid.setRealGeveCashAmount(cashPayAmount);
        saveValid.setPaymentWayCode(paymentWayCode);
        ServiceManager.orderService.saveSalesPosOrder(currUserVo, cart, saveValid);
        return ServiceManager.salesPosShoppingFlowService.getCartOrNewCart(currUserVo.getShopId());
    }

    @RequestMapping(value = "/convertWithoutMember",method = RequestMethod.POST)
    @ResponseBody
    public Object convertWithoutMember(@CurrUser CurrUserVo currUserVo, String uniqueKey){
        checkIsShowCart(currUserVo.getShopId(), uniqueKey);
        return ServiceManager.salesPosShoppingFlowService.convertWithoutMember(uniqueKey);
    }

    /**
     * 检查当前操作购物车是否属于登录用户所属子公司
     * @param shopId 子公司ID
     * @param uniqueKey 购物车uniqueKey
     */
    private void checkIsShowCart(Long shopId, String uniqueKey) {
        ShoppingCart cart = ServiceManager.salesPosShoppingFlowService.getCart(uniqueKey);
        if(!cart.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
    }
}
