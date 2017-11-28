package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DrugCheckSaveVo;
import com.imall.iportal.core.shop.vo.DrugCheckSearchParam;
import com.imall.iportal.core.shop.vo.DrugCheckUpdateVo;
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
@RequestMapping("/drugCheck")
public class DrugCheckController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.drugCheckService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody DrugCheckSaveVo drugCheckSaveVo,  @CurrUser CurrUserVo currUserVo) throws ParseException {
		drugCheckSaveVo.setShopId(currUserVo.getShopId());
		ServiceManager.drugCheckService.save(drugCheckSaveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody DrugCheckUpdateVo drugCheckUpdateVo,  @CurrUser CurrUserVo currUserVo) throws ParseException {
		drugCheckUpdateVo.setShopId(currUserVo.getShopId());
		ServiceManager.drugCheckService.update(drugCheckUpdateVo);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, DrugCheckSearchParam drugCheckSearchParam,  @CurrUser CurrUserVo currUserVo) {
		drugCheckSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.drugCheckService.query(pageable, drugCheckSearchParam);
	}

}

