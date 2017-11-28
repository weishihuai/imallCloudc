package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DrugCheckRecordSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/drugCheckRecord")
public class DrugCheckRecordController extends BaseRestSpringController {

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, DrugCheckRecordSearchParam drugCheckRecordSearchParam){
		drugCheckRecordSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.drugCheckRecordService.query(pageable, drugCheckRecordSearchParam);
	}

}

