package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.StockCheckCheckedVo;
import com.imall.iportal.core.shop.vo.StockCheckSaveVo;
import com.imall.iportal.core.shop.vo.StockCheckSearchParam;
import com.imall.iportal.core.shop.vo.StockPostingSearchParam;
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
@RequestMapping("/stockCheck")
public class StockCheckController extends BaseRestSpringController {

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id) {
        return null;
    }

    @RequestMapping(value = "/saveStockCheck", method = RequestMethod.POST)
    @ResponseBody
    public Object saveStockCheck(@RequestBody StockCheckSaveVo stockCheckSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        Long operatorId = ServiceManager.sysUserService.findOne(currUserVo.getUserId()).getId();
        ServiceManager.stockCheckService.saveStockCheck(currUserVo.getShopId(), operatorId, stockCheckSaveVo);
        return getSuccess();
}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, StockCheckSearchParam stockCheckSearchParam, @CurrUser CurrUserVo currUserVo) {
        stockCheckSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.stockCheckService.query(pageable, stockCheckSearchParam);
    }

    @RequestMapping(value = "/findByCheckOrderNum", method = RequestMethod.GET)
    @ResponseBody
    public Object findByCheckOrderNum(String checkOrderNum, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.stockCheckService.findByCheckOrderNum(checkOrderNum, currUserVo.getShopId());
    }

    @RequestMapping(value = "/updateCheckedStateByCheckOrderNum", method = RequestMethod.POST)
    @ResponseBody
    public Object updateCheckedStateByCheckOrderNum(String checkOrderNum, @CurrUser CurrUserVo currUserVo) {
        ServiceManager.stockCheckService.updateCheckedStateByCheckOrderNum(checkOrderNum, currUserVo.getShopId());
        return getSuccess();
    }

    @RequestMapping(value = "/updateStockCheckRealCheckQuantity", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStockCheckRealCheckQuantity(@RequestBody StockCheckCheckedVo stockCheckCheckedVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        ServiceManager.stockCheckService.updateStockCheckRealCheckQuantity(stockCheckCheckedVo, currUserVo.getShopId());
        return getSuccess();
    }


    @RequestMapping(value = "/queryStockPosting", method = RequestMethod.GET)
    @ResponseBody
    public Object queryStockPosting(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, StockPostingSearchParam stockPostingSearchParam) {
        stockPostingSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.stockCheckService.queryStockPosting(pageable, stockPostingSearchParam);
    }

    @RequestMapping(value = "/updateStockCheckStateToPosting", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStockCheckStateToPosting(Long id, @CurrUser CurrUserVo currUserVo) {
        ServiceManager.stockCheckService.updateStockCheckStateToPosting(id, currUserVo.getShopId());
        return getSuccess();
    }

}

