package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.OtherOutStockSaveVo;
import com.imall.iportal.core.shop.vo.OtherOutStockSearchParam;
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
@RequestMapping("/otherOutStock")
public class OtherOutStockController extends BaseRestSpringController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, OtherOutStockSearchParam otherOutStockSearchParam, @CurrUser CurrUserVo currUserVo) {
        otherOutStockSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.otherOutStockService.query(pageable, otherOutStockSearchParam);
    }

    @RequestMapping(value = "/findByOutStockCode", method = RequestMethod.GET)
    @ResponseBody
    public Object findByOutStockCode(String outStockCode, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.otherOutStockService.findByOutStockCode(outStockCode, currUserVo.getShopId());
    }

    @RequestMapping(value = "/saveOtherOutStock", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOtherOutStock(@RequestBody OtherOutStockSaveVo otherOutStockSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        otherOutStockSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.otherOutStockService.saveOtherOutStock(otherOutStockSaveVo);
        return getSuccess();
    }

}

