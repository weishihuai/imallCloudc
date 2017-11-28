package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/docInf")
public class DocInfController extends BaseRestSpringController {

	@RequestMapping(value = "/downloadDoc",method = RequestMethod.GET)
	@ResponseBody
	public void downloadDoc(Long id, HttpServletResponse response){
		ServiceManager.docInfService.downloadDoc(id, response);
	}

	@RequestMapping(value = "/searchByDocType",method = RequestMethod.GET)
	@ResponseBody
	public Object searchByDocType(String docType){
		return ServiceManager.docInfService.searchByDocType(docType);
	}

}

