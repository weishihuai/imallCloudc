package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderSaveVo;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderSearchParams;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/returnedPurchaseOrder")
public class ReturnedPurchaseOrderController extends BaseRestSpringController {

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.returnedPurchaseOrderService.findByIdAndShopId(id, currUserVo.getShopId());
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(@PageableDefault(page = 0, size = 10) Pageable pageable, ReturnedPurchaseOrderSearchParams searchParams, @CurrUser CurrUserVo sysUser) {
        searchParams.setShopId(sysUser.getShopId());
        return ServiceManager.returnedPurchaseOrderService.query(pageable, searchParams);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object query(@CurrUser CurrUserVo sysUser, @RequestBody ReturnedPurchaseOrderSaveVo saveVo) {
        saveVo.setShopId(sysUser.getShopId());
        return ServiceManager.returnedPurchaseOrderService.saveReturnedPurchaseOrder(saveVo);
    }
}

