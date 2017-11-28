package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.PurchaseReceiveRecordSaveVo;
import com.imall.iportal.core.shop.vo.PurchaseReceiveSearchParams;
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
@RequestMapping("/purchaseReceiveRecord")
public class PurchaseReceiveRecordController extends BaseRestSpringController {

    @RequestMapping(value = "/findEnableAcceptanceReceiveRecord", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.purchaseReceiveRecordService.findEnableAcceptanceReceiveRecord(id, currUserVo.getShopId());
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(Long id, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.purchaseReceiveRecordService.findByIdAndShopId(id, currUserVo.getShopId());
    }


    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate() {
        return null;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo sysUser, PurchaseReceiveSearchParams searchParams) {
        searchParams.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseReceiveRecordService.query(pageable, searchParams);
    }

    //保存收货记录
    @RequestMapping(value = "/saveReceiveRecord", method = RequestMethod.POST)
    @ResponseBody
    public Object saveReceiveRecord(@RequestBody PurchaseReceiveRecordSaveVo saveVo, @CurrUser CurrUserVo sysUser) throws ParseException {
        saveVo.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseReceiveRecordService.saveReceiveRecord(saveVo);
    }

}

