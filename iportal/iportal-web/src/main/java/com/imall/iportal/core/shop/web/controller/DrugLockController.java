package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.*;
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
@RequestMapping("/drugLock")
public class DrugLockController extends BaseRestSpringController {

    @RequestMapping(value = "/saveDrugLock", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDrugLock(@RequestBody DrugLockSaveVo drugLockSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        drugLockSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.drugLockService.saveDrugLock(drugLockSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/updateDrugLockDeal", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDrugLockDeal(@RequestBody DrugLockDealUpdateVo drugLockDealUpdateVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        drugLockDealUpdateVo.setShopId(currUserVo.getShopId());
        ServiceManager.drugLockService.updateDrugLockDeal(drugLockDealUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam, @CurrUser CurrUserVo currUserVo) {
        problemDrugSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.drugLockService.query(pageable, problemDrugSearchParam);
    }

    @RequestMapping(value = "/queryDrugLockDeal", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDrugLockDeal(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam, @CurrUser CurrUserVo currUserVo) {
        problemDrugSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.drugLockService.queryDrugLockDeal(pageable, problemDrugSearchParam);
    }

    @RequestMapping(value = "/drugLockDealGoodsBatchList", method = RequestMethod.GET)
    @ResponseBody
    public Object drugLockDealGoodsBatchList(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, DrugLockDealGoodsSearchParam drugLockDealGoodsSearchParam, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.drugLockService.queryDrugLockDealGoodsBatchList(pageable, drugLockDealGoodsSearchParam, currUserVo.getShopId());
    }

    @RequestMapping(value = "/queryDrugDestroyPage", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDrugDestroyPage(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, ProblemDrugSearchParam problemDrugSearchParam, @CurrUser CurrUserVo currUserVo) {
        problemDrugSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.drugLockService.queryDrugDestroyPage(pageable, problemDrugSearchParam);
    }

    @RequestMapping(value = "/drugLockDestroy", method = RequestMethod.POST)
    @ResponseBody
    public Object drugLockDestroy(Long id, @RequestBody DrugLockDestroyVo drugLockDestroyVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        drugLockDestroyVo.setShopId(currUserVo.getShopId());
        ServiceManager.drugLockService.drugLockDestroy(id, drugLockDestroyVo);
        return getSuccess();
    }

}

