package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/sysLogData")
public class SysLogDataController extends BaseRestSpringController {

	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(Long id) throws ParseException {
		return  ServiceManager.sysLogDataService.findById(id);
	}

	@RequestMapping(value = "/findPageByTableKey",method = RequestMethod.GET)
	@ResponseBody
	public Object findPageByTableKey(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, String tableKey, Long objectId, String searchText){
		return ServiceManager.sysLogDataService.query(pageable, tableKey, objectId, searchText);
	}


}

