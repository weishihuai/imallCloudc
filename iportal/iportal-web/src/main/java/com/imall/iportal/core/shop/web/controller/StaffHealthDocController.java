package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.StaffHealthDocSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/staffHealthDoc")
public class StaffHealthDocController extends BaseRestSpringController {

	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id,@CurrUser CurrUserVo currUserVo) {
		return ServiceManager.staffHealthDocService.findById(id,currUserVo.getShopId());
	}

	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(){
		return null;
	}

	@RequestMapping(value = "/queryStaffHealthDoc",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable,@CurrUser CurrUserVo currUserVo, StaffHealthDocSearchParam staffHealthDocSearchParam) throws ParseException {
		staffHealthDocSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.staffHealthDocService.queryStaffHealthDoc(pageable,staffHealthDocSearchParam);

	}

}

