package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSaveVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSearchParam;
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
@RequestMapping("/maintainingRecord")
public class MaintainingRecordController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.maintainingRecordService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody MaintainingRecordSaveVo saveVo, @CurrUser CurrUserVo currUserVo){
		saveVo.setShopId(currUserVo.getShopId());
		ServiceManager.maintainingRecordService.save(saveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable,
						MaintainingRecordSearchParam maintainingRecordSearchParam, @CurrUser CurrUserVo currUserVo){
		maintainingRecordSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.maintainingRecordService.query(pageable, maintainingRecordSearchParam);
	}

}

