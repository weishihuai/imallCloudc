package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.ConsultServiceSaveVo;
import com.imall.iportal.core.shop.vo.ConsultServiceSearchParam;
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
@RequestMapping("/consultService")
public class ConsultServiceController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(@CurrUser CurrUserVo currUserVo, Long id){
		return ServiceManager.consultServiceService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody ConsultServiceSaveVo saveVo,@CurrUser CurrUserVo currUserVo){
		saveVo.setShopId(currUserVo.getShopId());
		return ServiceManager.consultServiceService.save(saveVo);
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, ConsultServiceSearchParam searchParam){
		searchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.consultServiceService.query(pageable, searchParam);
	}

}

