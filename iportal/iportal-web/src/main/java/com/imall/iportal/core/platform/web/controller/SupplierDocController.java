package com.imall.iportal.core.platform.web.controller;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.vo.ResultVo;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.platform.entity.SupplierDoc;
import com.imall.iportal.core.platform.vo.SupplierDocSaveVo;
import com.imall.iportal.core.platform.vo.SupplierDocSearchParam;
import com.imall.iportal.core.platform.vo.SupplierDocUpdateVo;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/supplierDoc")
public class SupplierDocController extends BaseRestSpringController {

	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id) {
		return ServiceManager.supplierDocService.findById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody SupplierDocSaveVo supplierDocSaveVo) throws ParseException {
		return ServiceManager.supplierDocService.save(supplierDocSaveVo);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody SupplierDocUpdateVo supplierDocUpdateVo) throws ParseException {
		return ServiceManager.supplierDocService.update(supplierDocUpdateVo);
	}

	@RequestMapping(value = "/checkNameIsExist", method = RequestMethod.POST)
	@ResponseBody
	public Object checkNameIsExist(@RequestBody SupplierDoc supplierDoc, @CurrUser CurrUserVo currUserVo) {
		return ServiceManager.supplierDocService.checkNameIsExist(supplierDoc.getSupplierNm(), supplierDoc.getId());
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, SupplierDocSearchParam supplierDocSearchParam) throws ParseException {
		return ServiceManager.supplierDocService.querySupplierDoc(pageable,supplierDocSearchParam);
	}

	@RequestMapping(value = "/updateSupplierDocState", method = RequestMethod.GET)
	@ResponseBody
	public Object updateSupplierDocState( Long id, String state) {
		return ServiceManager.supplierDocService.updateSupplierDocState(id, state);
	}

	@RequestMapping(value = "/queryPage", method = RequestMethod.GET)
	@ResponseBody
	public Object queryPage(@PageableDefault(page = 0, size = 10)Pageable pageable, String supplierNm, String certificatesNum) {
		return ServiceManager.supplierDocService.queryPage(pageable, supplierNm,"BUSINESS_LICENSE", certificatesNum);
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public Object exportExcel(ModelMap model, @CurrUser CurrUserVo currUserVo, SupplierDocSearchParam supplierDocSearchParam) throws ParseException {
		Pageable pageable = new PageRequest(0, 65000);
		String xlsTemplatePath = WebContextFactory.getWebRealPath() + "xlstemplate/SupplierDoc.xls";
		SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
		String xlsOutputPath = "xlsoutput/SupplierDoc/" + getUUIDPath(sysUser.getUserName(), "x.xls");
		Boolean success = ServiceManager.supplierDocService.exportExcel(xlsTemplatePath, WebContextFactory.getWebRealPath() + xlsOutputPath, pageable, supplierDocSearchParam);
		if(!success){
			return getFailure();
		}
		ResultVo resultVo = getSuccess();
		resultVo.setMsg(xlsOutputPath);
		return resultVo;
	}

	private String getUUIDPath(String userLoginId, String fileName) {
		StringBuilder builder = new StringBuilder();
		builder.append("supplierdoc").append("_").append(userLoginId).append("_");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		String uuid = UUID.randomUUID().toString();
		builder.append(cal.get(Calendar.YEAR))
				.append("_").append(cal.get(Calendar.MONTH) + 1)
				.append("_").append(cal.get(Calendar.DAY_OF_MONTH))
				.append("_").append(uuid)
				.append(fileName);
		return builder.toString();
	}


	@RequestMapping(value = "/importExcel", method = RequestMethod.GET)
	@ResponseBody
	public Object importExcel(ModelMap model, @CurrUser CurrUserVo currUserVo, String fileId) throws ParseException {
		String baseDir = WebContextFactory.getLocalUploadPath();
		List<ErrorLog> errorLogList = ServiceManager.supplierDocService.importData(baseDir + fileId);
		if(errorLogList.size() > 0){
			SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
			String xlsintputLogPath = "xlsinputlog/SupplierDoc/" + getUUIDPath(sysUser.getUserName(), "x.txt");
			List<String> errorList = new ArrayList<>();
			errorList.add("时间" + DateTimeUtils.convertDateTimeToString(new Date()));
			errorList.add("导入文件：" + fileId);
			errorList.add("错误：");
			for(ErrorLog errorLog: errorLogList){
				errorList.add("第" + errorLog.getLineNumber() + "行， " + errorLog.getErrorMessage());
			}
			try {
				FileUtils.writeLines(new File(WebContextFactory.getWebRealPath() + xlsintputLogPath), "UTF-8", errorList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ResultVo resultVo = getFailure();
			resultVo.setMsg(xlsintputLogPath);
			return resultVo;
		}

		return getSuccess();
	}
}

