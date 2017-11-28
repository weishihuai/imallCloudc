package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.SellSummarySearchParams;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController extends BaseRestSpringController {

	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(Long id){
		return null;
	}

	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(){
		return null;
	}

	@RequestMapping(value = "/findByOrderId",method = RequestMethod.GET)
	@ResponseBody
	public Object findByOrderId(ModelMap model, Long orderId){
		return ServiceManager.orderItemService.findVoByOrderId(orderId);
	}

	@RequestMapping(value = "/querySellDetailPageVo",method = RequestMethod.GET)
	@ResponseBody
	public Object querySellDetailPageVo(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, SellSummarySearchParams sellSummarySearchParams){
		sellSummarySearchParams.setShopId(currUserVo.getShopId());
		return ServiceManager.orderItemService.querySellDetailPageVo(pageable, sellSummarySearchParams);
	}
}

