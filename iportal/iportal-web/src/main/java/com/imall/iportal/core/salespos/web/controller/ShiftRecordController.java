package com.imall.iportal.core.salespos.web.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.vo.ResultVo;
import com.imall.iportal.core.salespos.entity.ShiftRecord;
import com.imall.iportal.core.salespos.web.vo.ShiftUserVo;
import com.imall.iportal.core.shop.vo.ShiftRecordSearchParam;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import com.imall.iportal.shiro.service.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shiftRecord")
public class ShiftRecordController extends BaseRestSpringController {

//	@RequestMapping(value = "/init",method = RequestMethod.GET)
//	@ResponseBody
//	public Object init(ModelMap model, @CurrUser CurrUserVo currUserVo){
//		return ServiceManager.shiftRecordService.saveRecord(currUserVo.getShopId(), currUserVo.getUserId(), null, new Date());
//	}

	@Autowired
	private PasswordHelper passwordHelper;

	@RequestMapping(value = "/findNormalByShopId",method = RequestMethod.GET)
	@ResponseBody
	public Object findNormalByShopId(ModelMap model, String username, String password, String validateCode, HttpServletRequest request) {
		ShiftUserVo shiftUserVo = new ShiftUserVo();
		if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password) || !StringUtils.hasLength(validateCode)){
			shiftUserVo.setUserList(new ArrayList<SysUser>());
			return shiftUserVo;
		}
		//验证码
		if(!checkValidateCode(request, validateCode)){
			shiftUserVo.setUserList(new ArrayList<SysUser>());
			return shiftUserVo;
		}
		//用户
		SysUser loginUser = ServiceManager.sysUserService.getByLoginId(username);
		if(loginUser==null || !loginUser.getPassword().equals(passwordHelper.encryptPassword(password, loginUser.getSalt()))){
			shiftUserVo.setUserList(new ArrayList<SysUser>());
			return shiftUserVo;
		}

		Subject subject = SecurityUtils.getSubject();
		Object succeedWhoIdObj = subject.getSession().getAttribute("succeedWhoId");
		Long succeedWhoId = succeedWhoIdObj==null ? null : Long.valueOf(succeedWhoIdObj.toString());
		if(succeedWhoId==null){
			ShiftRecord shiftRecord = ServiceManager.shiftRecordService.findLastNotShift(loginUser.getShopId(), loginUser.getId());
			if(shiftRecord!=null){
				succeedWhoId = shiftRecord.getSucceedWhoId();
			}
		}
		shiftUserVo.setSucceedWhoId(succeedWhoId);
		List<SysUser> users = ServiceManager.sysUserService.findNormalByShopId(loginUser.getShopId());
		List<SysUser> userList = new ArrayList<>();
		for(SysUser user: users){
			//密码安全
			user.setPassword(null);
			user.setSalt(null);
			userList.add(user);
		}
		shiftUserVo.setUserList(userList);
		return shiftUserVo;
	}

	private boolean checkValidateCode(HttpServletRequest request, String input) {
		HttpSession session = request.getSession();
		String validateCode = (String) session.getAttribute(Global.VALIDATE_CODE);
		return org.apache.commons.lang.StringUtils.isNotBlank(input)&& org.apache.commons.lang.StringUtils.equalsIgnoreCase(validateCode, input); //忽略大小写
	}

	@RequestMapping(value = "/findByShopIdAndPosManAndNotShift",method = RequestMethod.GET)
	@ResponseBody
	public Object findByShopIdAndPosManAndNotShift(ModelMap model, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.shiftRecordService.findReadyShiftByShopIdAndPosMan(currUserVo.getShopId(), currUserVo.getUserId());
	}

	@RequestMapping(value = "/updateRecord",method = RequestMethod.POST)
	@ResponseBody
	public Object updateRecord(ModelMap model, @CurrUser CurrUserVo currUserVo, @RequestBody ShiftRecord record, HttpServletRequest request){
		record.setShopId(currUserVo.getShopId());
		record.setPosMan(currUserVo.getUserId());
		ServiceManager.shiftRecordService.updateRecord(record.getId(), record.getShopId(), record.getPosMan(), new Date(), record.getSucceedReadyAmount(), record.getKeepReadyAmount(), record.getHandoverCashAmount());
		return new ResultVo(true, currUserVo.getUserId().toString());
	}

	@RequestMapping(value = "/findPage",method = RequestMethod.GET)
	@ResponseBody
	public Object findPage(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, ShiftRecordSearchParam shiftRecordSearchParam, @CurrUser CurrUserVo currUserVo){
		shiftRecordSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.shiftRecordService.findPage(pageable, shiftRecordSearchParam);
	}

	@RequestMapping(value = "/saveRecord",method = RequestMethod.GET)
	@ResponseBody
	public void saveRecord(ModelMap model, @CurrUser CurrUserVo currUserVo){
		ServiceManager.shiftRecordService.saveRecord(currUserVo.getShopId(), currUserVo.getUserId(), null, new Date());
	}

}

