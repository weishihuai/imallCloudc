package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.OtherInStockSaveVo;
import com.imall.iportal.core.shop.vo.OtherInStockSearchParam;
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
@RequestMapping("/otherInStock")
public class OtherInStockController extends BaseRestSpringController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, OtherInStockSearchParam otherInStockSearchParam) {
        otherInStockSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.otherInStockService.query(pageable, otherInStockSearchParam);
    }

    @RequestMapping(value = "/findByInStockCode", method = RequestMethod.GET)
    @ResponseBody
    public Object findByInStockCode(String inStockCode, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.otherInStockService.findByInStockCode(inStockCode, currUserVo.getShopId());
    }

    @RequestMapping(value = "/saveOtherInStock", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOtherInStock(@RequestBody OtherInStockSaveVo otherInStockSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        otherInStockSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.otherInStockService.saveOtherInStock(otherInStockSaveVo);
        return getSuccess();
    }


}

