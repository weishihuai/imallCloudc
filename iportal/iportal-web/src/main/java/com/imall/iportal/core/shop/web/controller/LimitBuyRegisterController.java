package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.LimitBuyRegisterSaveVo;
import com.imall.iportal.core.shop.vo.LimitBuyRegisterSearchParam;
import com.imall.iportal.core.shop.vo.LimitBuyRegisterUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/limitBuyRegister")
public class LimitBuyRegisterController extends BaseRestSpringController {

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.limitBuyRegisterService.findById(currUserVo.getShopId(), id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody LimitBuyRegisterSaveVo limitBuyRegisterSaveVo, @CurrUser CurrUserVo currUserVo) {
        limitBuyRegisterSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.limitBuyRegisterService.save(limitBuyRegisterSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody LimitBuyRegisterUpdateVo limitBuyRegisterUpdateVo) {
        ServiceManager.limitBuyRegisterService.update(limitBuyRegisterUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, LimitBuyRegisterSearchParam limitBuyRegisterSearchParam, @CurrUser CurrUserVo currUserVo) {
        limitBuyRegisterSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.limitBuyRegisterService.query(pageable, limitBuyRegisterSearchParam);
    }

    @RequestMapping(value = "/listItemByOrderId", method = RequestMethod.GET)
    @ResponseBody
    public Object listItemByOrderId(Long orderId, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.limitBuyRegisterService.listItemByOrderId(currUserVo.getShopId(), orderId);
    }
}

