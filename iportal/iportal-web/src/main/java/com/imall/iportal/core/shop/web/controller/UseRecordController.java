package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.UseRecordSaveVo;
import com.imall.iportal.core.shop.vo.UseRecordSearchParam;
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
@RequestMapping("/useRecord")
public class UseRecordController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.useRecordService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody UseRecordSaveVo saveVo, @CurrUser CurrUserVo currUserVo){
		saveVo.setShopId(currUserVo.getShopId());
		saveVo.setOperationMan(currUserVo.getUserName());
		ServiceManager.useRecordService.save(saveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable,
						UseRecordSearchParam useRecordSearchParam, @CurrUser CurrUserVo currUserVo){
		useRecordSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.useRecordService.query(pageable, useRecordSearchParam);
	}

}

