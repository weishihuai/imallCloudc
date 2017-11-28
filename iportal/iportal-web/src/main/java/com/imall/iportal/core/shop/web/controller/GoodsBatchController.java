package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.GoodsBatchUpdateVo;
import com.imall.iportal.core.shop.vo.GoodsCommonComponentSearchParam;
import com.imall.iportal.core.shop.vo.StockWarningSearchParam;
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
import java.util.List;

@Controller
@RequestMapping("/goodsBatch")
public class GoodsBatchController extends BaseRestSpringController {

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id) {
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody GoodsBatchUpdateVo goodsBatchUpdateVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        ServiceManager.goodsBatchService.updateBatch(goodsBatchUpdateVo, currUserVo.getShopId());
        return getSuccess();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestBody List<Long> ids) {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, String searchFields, String batch, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsBatchService.query(pageable, searchFields, batch, currUserVo.getShopId());
    }

    @RequestMapping(value = "/queryExpireDrugWarning", method = RequestMethod.GET)
    @ResponseBody
    public Object queryExpireDrugWarning(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, StockWarningSearchParam stockWarningSearchParam, @CurrUser CurrUserVo currUserVo) {
        stockWarningSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.goodsBatchService.queryExpireDrugWarning(pageable, stockWarningSearchParam);
    }

    @RequestMapping(value = "/queryNearExpireDatePage", method = RequestMethod.GET)
    @ResponseBody
    public Object queryNearExpireDatePage(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, StockWarningSearchParam stockWarningSearchParam, @CurrUser CurrUserVo currUserVo) {
        stockWarningSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.goodsBatchService.queryNearExpireDatePage(pageable, stockWarningSearchParam);
    }

    @RequestMapping(value = "/commonGoodsBatchList", method = RequestMethod.GET)
    @ResponseBody
    public Object commonGoodsBatchList(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, GoodsCommonComponentSearchParam goodsCommonComponentSearchParam, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsBatchService.queryCommonGoodsBatchList(pageable, goodsCommonComponentSearchParam, currUserVo.getShopId());
    }

    @RequestMapping(value = "/findBatch", method = RequestMethod.GET)
    @ResponseBody
    public Object findBatch(Long goodsId, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsBatchService.findByGoodsIdAndShopId(goodsId, currUserVo.getShopId());
    }

    @RequestMapping(value = "/queryBatchActualStock", method = RequestMethod.GET)
    @ResponseBody
    public Object queryBatchActualStock(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, String searchFields, String batch) {
        return ServiceManager.goodsBatchService.queryBatchActualStock(pageable, currUserVo.getShopId(), searchFields, batch);
    }

}

