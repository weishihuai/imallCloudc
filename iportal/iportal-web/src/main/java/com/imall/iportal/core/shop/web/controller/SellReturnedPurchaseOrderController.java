package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.valid.SellReturnedPurchaseOrderSaveValid;
import com.imall.iportal.core.shop.vo.OrderSearchParam;
import com.imall.iportal.core.shop.vo.SellReturnedPurchaseOrderSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sellReturnedPurchaseOrder")
public class SellReturnedPurchaseOrderController extends BaseRestSpringController {

	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.sellReturnedPurchaseOrderService.findDetail(id, currUserVo.getShopId());
	}

	@RequestMapping(value = "/posReturnedSave",method = RequestMethod.POST)
	@ResponseBody
	public Object posReturnedSave(ModelMap model, @RequestBody SellReturnedPurchaseOrderSaveValid sellReturnedPurchaseOrderSaveValid, @CurrUser CurrUserVo currUserVo){
		sellReturnedPurchaseOrderSaveValid.setCashierId(currUserVo.getUserId());
		ServiceManager.sellReturnedPurchaseOrderService.saveReturnedCalc(sellReturnedPurchaseOrderSaveValid);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, SellReturnedPurchaseOrderSearchParam searchParam){
		searchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.sellReturnedPurchaseOrderService.query(pageable, searchParam);
	}

	@RequestMapping(value = "/commonOrderQuery",method = RequestMethod.GET)
	@ResponseBody
	public Object commonOrderQuery(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable,OrderSearchParam orderSearchParam, @CurrUser CurrUserVo currUserVo){
		orderSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.sellReturnedPurchaseOrderService.commonOrderQuery(pageable,  orderSearchParam);
	}

	@RequestMapping(value = "/findByOrderId",method = RequestMethod.GET)
	@ResponseBody
	public Object findByOrderId(ModelMap model, Long orderId){
		return ServiceManager.sellReturnedPurchaseOrderItemService.findVoByOrderId(orderId);
	}


}

