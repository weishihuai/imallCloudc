package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.core.main.entity.SysDeveloperAuth;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.vo.PortalMenuVo;
import com.imall.iportal.core.main.vo.RouterConfigVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import com.imall.iportal.shiro.service.SessionCacheService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxd on 2015/9/14.
 */
@Controller
@RequestMapping("/")
public class MainFrameController extends BaseRestSpringController {

    @Autowired
    private SessionCacheService sessionCacheService;


    @RequestMapping(value = "/portal")
    public String link(String link){
        return link;
    }

    @RequestMapping(value = "/test.html")
    public String test(Model model, @CurrUser CurrUserVo currUser, HttpServletRequest request, HttpServletResponse response) {
        return "portal/test";
    }

    @RequestMapping(value = "/main.html")
    public String main(Model model, @CurrUser CurrUserVo currUser, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("sysAppContexts",initSysAppContexts(currUser));
        String ticket = "nothing";
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie: cookies){
                if(cookie.getName()!=null && cookie.getName().equals("ticket")){
                    response.addCookie(cookie);
                    ticket = cookie.getValue();
                }
            }
        }
        model.addAttribute("ticket", ticket);
        return "portal/main";
    }

    @RequestMapping(value = "/posmain.html")
    public String posmain(Model model, @CurrUser CurrUserVo currUser, HttpServletRequest request, HttpServletResponse response) {
        return "portal/posmain";
    }

    @RequestMapping(value = "/report.html")
    public String salesreport(Model model, @CurrUser CurrUserVo currUser, HttpServletRequest request, HttpServletResponse response) {
        return "portal/posreport";
    }

    @RequestMapping(value = "/returned.html")
    public String posreturned(Model model, @CurrUser CurrUserVo currUser, HttpServletRequest request, HttpServletResponse response) {
        return "portal/posreturned";
    }

    private List<SysApp> initSysAppContexts(CurrUserVo currUser){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        String cacheKey = "sysAppContexts";
        List<SysApp> sysAppContexts = (List<SysApp>)session.getAttribute(cacheKey);
        if(sysAppContexts==null){
            sysAppContexts = new ArrayList<SysApp>();
            List<SysApp> sysAppList = ServiceManager.sysAppService.findAll();
            Map<String, String> appHostMap = new HashMap<>();
            if(currUser!=null){
                //开发者 host转换
                List<SysDeveloperAuth> authList = ServiceManager.sysDeveloperAuthService.findByUserIdAndIsAvailable(currUser.getUserId());
                for(SysDeveloperAuth auth: authList){
                    appHostMap.put(auth.getAppName(), auth.getHostname());
                }
            }

            //注意，不能把appKey和appSecret外泄
            for(SysApp dbApp:sysAppList){
                SysApp appMsg = new SysApp();
                appMsg.setAppName(dbApp.getAppName());
                appMsg.setHostname(dbApp.getHostname());
                appMsg.setWebContext(dbApp.getWebContext());
                if(currUser!=null && StringUtils.isNotBlank(appHostMap.get(dbApp.getAppName()))){
                    //开发者 host转换
                    appMsg.setHostname(appHostMap.get(dbApp.getAppName()));
                }
                sysAppContexts.add(appMsg);
            }
            session.setAttribute(cacheKey, sysAppContexts);
        }
        return sysAppContexts;
    }

    @RequestMapping(value = "/getPortalMenus")
    @ResponseBody
    public Object getPortalMenus(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String cacheKey = "portalMenu";
        List<PortalMenuVo> portalMenuList = (List<PortalMenuVo>)session.getAttribute(cacheKey);
        if(portalMenuList==null){
            //缓存失效需要重新加载
            portalMenuList = ServiceManager.sysMenuService.getPortalMenus();
            session.setAttribute(cacheKey, portalMenuList);
        }

        return checkPermission(portalMenuList);
    }

    private List<PortalMenuVo> checkPermission(List<PortalMenuVo> menuMsgList){
        Subject subject = SecurityUtils.getSubject();
        List<PortalMenuVo> menuListResult = new ArrayList<PortalMenuVo>();
        if(menuMsgList!=null){
            for(PortalMenuVo menuMsg:menuMsgList){
                if(subject.isPermitted(menuMsg.getPermissionCode())){
                    menuMsg.setSubChildList(checkPermission(menuMsg.getSubChildList()));
                    menuListResult.add(menuMsg);
                }
            }
        }
        return menuListResult;
    }

    @RequestMapping(value = "/clearUserAuthCache")
    @ResponseBody
    public Object clearUserAuthCache(Model model,  @CurrUser CurrUserVo currUser) {
        Boolean success = sessionCacheService.clearUserAuthCache(currUser.getUserName());
        return getSuccess();
    }

    @RequestMapping(value = "/checkAuthPermission")
    @ResponseBody
    public Object checkAuthPermission(Model model, String permissionCode) {
        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(permissionCode);
    }

    @RequestMapping(value = "/checkAuthPermissions")
    @ResponseBody
    public Object checkAuthPermissions(Model model,@RequestBody String[] permissionCodes) {
        if(permissionCodes==null){
            return new HashMap<String, Boolean>();
        }
        Subject subject = SecurityUtils.getSubject();
        Map<String, Boolean> authMap = new HashMap<String, Boolean>(permissionCodes.length);
        for(String permissionCode: permissionCodes){
            authMap.put(permissionCode, subject.isPermitted(permissionCode));
        }
        return authMap;
    }

    @RequestMapping(value = "/routerConfig",method = RequestMethod.GET)
    @ResponseBody
    public Object getRouterConfig(Model model, @CurrUser CurrUserVo currUser){
        List<RouterConfigVo> configList = ServiceManager.sysMenuService.getPortalRouterConfig();
        if(currUser!=null){
            Map<String, String> appHostMap = new HashMap<>();
            //开发者 host转换
            List<SysDeveloperAuth> authList = ServiceManager.sysDeveloperAuthService.findByUserIdAndIsAvailable(currUser.getUserId());
            for(SysDeveloperAuth auth: authList){
                appHostMap.put(auth.getAppName(), auth.getHostname());
            }
            for(RouterConfigVo config: configList){
                SysApp sysApp = ServiceManager.sysAppService.findOne(config.getAppId());
                if(appHostMap.get(sysApp.getAppName())!=null) {
                    config.setRouterTemplateJs(StringUtils.isNotBlank(config.getRouterTemplateJs()) ? config.getRouterTemplateJs().replace(sysApp.getHostname(), appHostMap.get(sysApp.getAppName())) : config.getRouterTemplateJs());
                    config.setRouterTemplateUrl(StringUtils.isNotBlank(config.getRouterTemplateUrl()) ? config.getRouterTemplateUrl().replace(sysApp.getHostname(), appHostMap.get(sysApp.getAppName())) : config.getRouterTemplateUrl());
                }
            }
        }
        return configList;
    }

    @RequestMapping(value = "/ssologin.html")
    public String ssologin(Model model) {
        model.addAttribute("sysAppContexts",initSysAppContexts(null));
        return "portal/ssologin";
    }

    @RequestMapping(value = "/posssologin.html")
    public String posssologin(Model model) {
        model.addAttribute("sysAppContexts",initSysAppContexts(null));
        return "portal/posssologin";
    }


    @RequestMapping(value = "/ssoFailure.html")
    public String ssoFailure(Model model) {
        return "portal/ssoFailure";
    }


}
