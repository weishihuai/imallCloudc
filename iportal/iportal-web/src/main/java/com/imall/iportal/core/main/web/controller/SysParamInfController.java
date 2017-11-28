package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysParamInfBatchUpdateValid;
import com.imall.iportal.core.main.valid.SysParamInfSaveValid;
import com.imall.iportal.core.main.valid.SysParamInfUpdateValid;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
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
@RequestMapping("/sysParamInf")
public class SysParamInfController extends BaseRestSpringController {


	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(Long id){
		return ServiceManager.sysParamInfService.findOne(id);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody SysParamInfSaveValid sysParamInfSaveValid,  @CurrUser CurrUserVo currUserVo){
		sysParamInfSaveValid.setSysOrgId(currUserVo.getOrgId());
		ServiceManager.sysParamInfService.save(sysParamInfSaveValid);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody SysParamInfUpdateValid sysParamInfUpdateValid, @CurrUser CurrUserVo currUserVo){
		sysParamInfUpdateValid.setSysOrgId(currUserVo.getOrgId());
		ServiceManager.sysParamInfService.update(sysParamInfUpdateValid);
		return getSuccess();
	}

	@RequestMapping(value = "/updateByBatch",method = RequestMethod.POST)
	@ResponseBody
	public Object updateByBatch(@RequestBody List<SysParamInfBatchUpdateValid> msgList,  @CurrUser CurrUserVo currUserVo){
		ServiceManager.sysParamInfService.updateByBatch(currUserVo.getOrgId(), msgList);
		return getSuccess();
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public  Object delete(@RequestBody List<Long> ids){
		ServiceManager.sysParamInfService.delete(ids);
		return getSuccess();
	}

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public Object list(ModelMap model, @CurrUser CurrUserVo currUserVo,@PageableDefault(page = 0, size = 10) Pageable pageable,String innerCode, String paramNm){
		return ServiceManager.sysParamInfService.query(pageable,currUserVo.getOrgId(), innerCode,paramNm);
	}

	@RequestMapping(value = "/listBySysOrgIdAndGroupTypeCode",method = RequestMethod.GET)
	@ResponseBody
	public Object listBySysOrgIdAndGroupTypeCode(ModelMap model,String groupTypeCode, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.sysParamInfService.listBySysOrgIdAndGroupTypeCode(currUserVo.getOrgId(), groupTypeCode);
	}

}

