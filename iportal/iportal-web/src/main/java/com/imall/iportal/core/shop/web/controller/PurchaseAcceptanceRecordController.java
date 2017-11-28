package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordSaveVo;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordSearchParams;
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
@RequestMapping("/purchaseAcceptanceRecord")
public class PurchaseAcceptanceRecordController extends BaseRestSpringController {

    //保存验收记录
    @RequestMapping(value = "/saveAcceptanceRecord", method = RequestMethod.POST)
    @ResponseBody
    public Object saveAcceptanceRecord(@RequestBody PurchaseAcceptanceRecordSaveVo saveVo, @CurrUser CurrUserVo sysUser) throws ParseException {
        saveVo.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseAcceptanceRecordService.saveAcceptanceRecord(saveVo);
    }

    //验收单详情
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@CurrUser CurrUserVo sysUser, Long id) throws ParseException {
        return ServiceManager.purchaseAcceptanceRecordService.getDetail(id, sysUser.getShopId());
    }

    //快速收货列表
    @RequestMapping(value = "/queryFastReceive", method = RequestMethod.GET)
    @ResponseBody
    public Object queryFastReceive(@CurrUser CurrUserVo sysUser, @PageableDefault(page = 0, size = 10) Pageable pageable, PurchaseAcceptanceRecordSearchParams searchParams) throws ParseException {
        searchParams.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseAcceptanceRecordService.queryFastReceive(pageable, searchParams);
    }


    //验收单列表
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(@CurrUser CurrUserVo sysUser, @PageableDefault(page = 0, size = 10) Pageable pageable, PurchaseAcceptanceRecordSearchParams searchParams) throws ParseException {
        searchParams.setShopId(sysUser.getShopId());
        return ServiceManager.purchaseAcceptanceRecordService.query(pageable, searchParams);
    }
}

