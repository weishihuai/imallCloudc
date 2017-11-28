package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.WeShopSaveVo;
import com.imall.iportal.core.shop.vo.WeShopUpdateVo;
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
@RequestMapping("/weshop")
public class WeShopController extends BaseRestSpringController {

	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(@CurrUser CurrUserVo currUserVo){
		return ServiceManager.weShopService.getDetailByShopId(currUserVo.getShopId());
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@CurrUser CurrUserVo currUserVo, @RequestBody WeShopSaveVo saveVo){
		saveVo.setShopId(currUserVo.getShopId());
		return ServiceManager.weShopService.saveWeShop(saveVo);
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@CurrUser CurrUserVo currUserVo, @RequestBody WeShopUpdateVo updateVo){
		updateVo.setShopId(currUserVo.getShopId());
		return ServiceManager.weShopService.updateWeSop(updateVo);
	}

	@RequestMapping(value = "/findDecoration",method = RequestMethod.GET)
	@ResponseBody
	public Object query(@CurrUser CurrUserVo currUserVo){
		return ServiceManager.weShopService.findByShopId(currUserVo.getShopId());
	}

	@RequestMapping(value = "/getDecorationShopMsg",method = RequestMethod.GET)
	@ResponseBody
	public Object getDecorationShopMsg(@CurrUser CurrUserVo currUserVo){
		return ServiceManager.weShopService.getDecorationShopMsg(currUserVo.getShopId());
	}

}

