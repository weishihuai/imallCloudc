package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.PurchaseOrderStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.FastReceiveSaveVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderSaveVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderSearchParams;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController extends BaseRestSpringController {
    //采购订单列表
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo sysUser, PurchaseOrderSearchParams searchParams) {
        searchParams.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseOrderService.query(pageable, searchParams);
    }

    //采购收货列表
    @RequestMapping(value = "/queryPurchaseReceive", method = RequestMethod.GET)
    @ResponseBody
    public Object queryPurchaseReceive(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo sysUser, PurchaseOrderSearchParams searchParams) {
        searchParams.setShopId(sysUser.getShopId());
        searchParams.setPurchaseOrderState(PurchaseOrderStateCodeEnum.WAIT_RECEIVE.toCode());
        return ServiceManager.purchaseOrderService.query(pageable, searchParams);
    }

    //采购验收列表
    @RequestMapping(value = "/queryPurchaseAcceptance", method = RequestMethod.GET)
    @ResponseBody
    public Object queryPurchaseAcceptance(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo sysUser, PurchaseOrderSearchParams searchParams) {
        searchParams.setShopId(sysUser.getShopId());
        searchParams.setPurchaseOrderState(PurchaseOrderStateCodeEnum.WAIT_ACCEPTANCE.toCode());
        return ServiceManager.purchaseOrderService.query(pageable, searchParams);
    }

    //采购订单详情
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(Long id, @CurrUser CurrUserVo sysUser) {
        return ServiceManager.purchaseOrderService.findByIdAndShopId(id, sysUser.getShopId());
    }

    //取消订单
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object cancel(Long id, @CurrUser CurrUserVo sysUser) {
        return ServiceManager.purchaseOrderService.updateStatToCancel(id, sysUser.getShopId());
    }

    //保存订单
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody PurchaseOrderSaveVo saveVo, @CurrUser CurrUserVo sysUser) {
        saveVo.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseOrderService.savePurchaseOrder(saveVo);
    }

    //保存快速收货
    @RequestMapping(value = "/saveFastReceive", method = RequestMethod.POST)
    @ResponseBody
    public Object saveFastReceive(@RequestBody FastReceiveSaveVo saveVo, @CurrUser CurrUserVo sysUser) throws ParseException {
        saveVo.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseOrderService.saveFastReceive(saveVo);
    }

    //采购收货界面
    @RequestMapping(value = "/findPurchaseReceiveInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object findPurchaseReceiveInfo(Long id, @CurrUser CurrUserVo sysUser) throws ParseException {
        return ServiceManager.purchaseOrderService.findPurchaseReceiveInfo(id, sysUser.getShopId());
    }

}

