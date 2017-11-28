package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/orderSendOutBatch")
public class OrderSendOutBatchController extends BaseRestSpringController {

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

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable){
		return null;
	}

}

