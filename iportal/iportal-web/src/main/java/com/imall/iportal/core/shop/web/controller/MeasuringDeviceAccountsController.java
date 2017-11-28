package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.MeasuringDeviceAccountsCheckVo;
import com.imall.iportal.core.shop.vo.MeasuringDeviceAccountsSaveVo;
import com.imall.iportal.core.shop.vo.MeasuringDeviceAccountsSearchParam;
import com.imall.iportal.core.shop.vo.MeasuringDeviceAccountsUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/measuringDeviceAccounts")
public class MeasuringDeviceAccountsController extends BaseRestSpringController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody MeasuringDeviceAccountsSaveVo measuringDeviceAccountsSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        measuringDeviceAccountsSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.measuringDeviceAccountsService.save(measuringDeviceAccountsSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, MeasuringDeviceAccountsSearchParam measuringDeviceAccountsSearchParam) throws ParseException {
        measuringDeviceAccountsSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.measuringDeviceAccountsService.query(pageable, measuringDeviceAccountsSearchParam);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody MeasuringDeviceAccountsUpdateVo measuringDeviceAccountsUpdateVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        measuringDeviceAccountsUpdateVo.setShopId(currUserVo.getShopId());
        ServiceManager.measuringDeviceAccountsService.update(measuringDeviceAccountsUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/measuringDeviceCheck", method = RequestMethod.POST)
    @ResponseBody
    public Object measuringDeviceCheck(Long id, @RequestBody MeasuringDeviceAccountsCheckVo measuringDeviceAccountsCheckVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        measuringDeviceAccountsCheckVo.setShopId(currUserVo.getShopId());
        ServiceManager.measuringDeviceAccountsService.measuringDeviceCheck(id, measuringDeviceAccountsCheckVo);
        return getSuccess();
    }

}

