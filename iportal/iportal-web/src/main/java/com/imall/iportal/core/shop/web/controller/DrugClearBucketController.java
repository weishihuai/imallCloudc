package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DrugClearBucketSaveVo;
import com.imall.iportal.core.shop.vo.DrugClearBucketSearchParam;
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
@RequestMapping("/drugClearBucket")
public class DrugClearBucketController extends BaseRestSpringController {

    @RequestMapping(value = "/saveDrugClearBucket", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDrugClearBucket(@RequestBody DrugClearBucketSaveVo drugClearBucketSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        drugClearBucketSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.drugClearBucketService.saveDrugClearBucket(drugClearBucketSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, DrugClearBucketSearchParam drugClearBucketSearchParam) {
        drugClearBucketSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.drugClearBucketService.query(pageable, drugClearBucketSearchParam);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(@CurrUser CurrUserVo currUserVo, Long id) {
        return ServiceManager.drugClearBucketService.findById(currUserVo.getShopId(), id);
    }

}

