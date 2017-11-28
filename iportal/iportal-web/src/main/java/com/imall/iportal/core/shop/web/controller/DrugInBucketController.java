package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DrugInBucketSaveVo;
import com.imall.iportal.core.shop.vo.DrugInBucketSearchParam;
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
@RequestMapping("/drugInBucket")
public class DrugInBucketController extends BaseRestSpringController {

    @RequestMapping(value = "/saveDrugInBucket", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDrugInBucket(@RequestBody DrugInBucketSaveVo drugInBucketSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        drugInBucketSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.drugInBucketService.saveDrugInBucket(drugInBucketSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, DrugInBucketSearchParam drugInBucketSearchParam, @CurrUser CurrUserVo currUserVo) {
        drugInBucketSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.drugInBucketService.query(pageable, drugInBucketSearchParam);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(@CurrUser CurrUserVo currUserVo, Long id) {
        return ServiceManager.drugInBucketService.findById(currUserVo.getShopId(), id);
    }

}

