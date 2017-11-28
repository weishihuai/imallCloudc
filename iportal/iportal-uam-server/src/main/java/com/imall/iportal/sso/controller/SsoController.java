package com.imall.iportal.sso.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.vo.SysUserVo;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import com.imall.iportal.shiro.sso.utils.SsoUtil;
import com.imall.iportal.sso.service.SsoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by yang on 2015-10-22.
 */
//注意：目前生成的ticket还没有什么作用 INITIAL BALANCE CURRENT
@Controller
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private SsoService ssoService;

    @RequestMapping(value = "/authorize",method = {RequestMethod.GET, RequestMethod.POST})
    public Object authorize(ModelMap model, HttpServletRequest request) throws URISyntaxException {
        String appKey = request.getParameter("a_key");
        String ssoTicket = request.getParameter("ticket");
        String redirectUrl = request.getParameter("r_url");
        String sign = request.getParameter("sign");
        String pos = request.getParameter("pos");

        String ssologinUrl = "/ssologin.html";
        if(StringUtils.hasLength(pos) && BoolCodeEnum.YES==BoolCodeEnum.fromCode(pos)){
            ssologinUrl = "/posssologin.html";
        }

        if(StringUtils.hasLength(pos) && BoolCodeEnum.YES==BoolCodeEnum.fromCode(pos)){
            if(!ssoService.checkSign(appKey, ssoTicket, redirectUrl, sign, pos)){
                model.put("sysApp", ServiceManager.sysAppService.findByAppKey(appKey));
                return "forward:" + ssologinUrl;  // 参数校验失败
            }
        }
        else {
            if(!ssoService.checkSign(appKey, ssoTicket, redirectUrl, sign)){
                model.put("sysApp", ServiceManager.sysAppService.findByAppKey(appKey));
                return "forward:" + ssologinUrl;  // 参数校验失败
            }
        }

        Subject subject = SecurityUtils.getSubject();
        //如果用户没有登录
        if(!subject.isAuthenticated()) {
            if(!login(subject, pos, request)) {//登录失败时跳转到登陆页面
                model.put("sysApp", ServiceManager.sysAppService.findByAppKey(appKey));
                return "forward:" + ssologinUrl;
            }
        }

        //登录成功，刷新ticket
        ssoTicket = ssoService.refreshTicket(ssoTicket);

        SysApp sysApp = ServiceManager.sysAppService.findByAppKey(appKey);
        SortedMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("appKey", appKey);
        paramMap.put("ticket", ssoTicket);
        paramMap.put("userName", (String)subject.getPrincipal());
        redirectUrl = redirectUrl + "?ticket=" + ssoTicket + "&userName=" + (String)subject.getPrincipal() + "&sign=" + SsoUtil.md5Hash(paramMap, sysApp.getAppSecret());
        //构建响应
        return "redirect:" + redirectUrl;
    }


    private boolean login(Subject subject, String pos, HttpServletRequest request) {
        if("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String succeedWhoId = request.getParameter("succeedWhoId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String validateCode = request.getParameter("validateCode");
        if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password) || !StringUtils.hasLength(validateCode)) {
            return false;
        }

        if(!checkValidateCode(request, validateCode, true)){
            request.setAttribute("error", "验证码错误...");
            return false;
        }

        SysUser loginUser = ServiceManager.sysUserService.getByLoginId(username);
        if(loginUser==null){
            request.setAttribute("error", "登录失败，账户错误...");
            return false;
        }
        SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.getIsmainByUserId(loginUser.getId());
        if(userOrgJob==null){
            request.setAttribute("error", "登录失败，账户没有设置主岗位...");
            return false;
        }
        if(ServiceManager.sysUserService.findByName(username)){
            request.setAttribute("error", "登录失败，账户已被禁用...");
            return false;
        }
        Shop shop = ServiceManager.shopService.findByOrgId(userOrgJob.getOrgId());
        if(shop!=null && BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
            //不启用
            request.setAttribute("error", "登录失败，门店未启用...");
            return false;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if(StringUtils.hasLength(rememberMe)){
            token.setRememberMe(Boolean.valueOf(rememberMe));
        }
        try {
            subject.login(token);
            //创建交班记录
            if(shop!=null && StringUtils.hasLength(pos) && BoolCodeEnum.YES == BoolCodeEnum.fromCode(pos) && org.apache.commons.lang3.StringUtils.isNotBlank(succeedWhoId)){
                ServiceManager.shiftRecordService.saveRecord(shop.getId(), loginUser.getId(), StringUtils.hasLength(succeedWhoId) ? Long.valueOf(succeedWhoId) : null, new Date());
            }
            return true;
        } catch (UnknownAccountException e) {
            request.setAttribute("error", "登录失败，账户错误...");
            return false;
        }
        catch (IncorrectCredentialsException e) {
            request.setAttribute("error", "登录失败，密码错误...");
            return false;
        }
        catch (LockedAccountException e) {
            request.setAttribute("error", "登录失败，账户被冻结...");
            return false;
        }
        catch (Exception e) {
            request.setAttribute("error", "登录失败，账户/密码错误...");
            return false;
        }
    }

    public boolean checkValidateCode(HttpServletRequest request, String input, boolean remove) {
        HttpSession session = request.getSession();
        String validateCode = (String) session.getAttribute(Global.VALIDATE_CODE);
        boolean result = org.apache.commons.lang.StringUtils.isNotBlank(input)&& org.apache.commons.lang.StringUtils.equalsIgnoreCase(validateCode, input); //忽略大小写
        if (remove || result) {
            session.removeAttribute(Global.VALIDATE_CODE);
        }
        return result;
    }

    @RequestMapping(value = "/login")
    public String showLoginForm(ModelMap model, HttpServletRequest req) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.put("error", error);
        return "login";
    }

    @RequestMapping(value = "/getLoginUser")
    @ResponseBody
    public Object getLoginUser(ModelMap model, @CurrUser CurrUserVo currUser) {

        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setUserName(currUser.getUserName());
        sysUserVo.setOrg(currUser.getOrgName());
        SysUser sysUser = ServiceManager.sysUserService.findOne(currUser.getUserId());
        sysUserVo.setRealName(sysUser.getRealName());
        sysUserVo.setEmployeeCode(sysUser.getEmployeeCode());

        return sysUserVo;
    }

    @RequestMapping(value = "/getAllSsoAuth")
    @ResponseBody
    public Object getAllSsoAuth(ModelMap model) {
        return ssoService.getAllSsoAuth();
    }

}
