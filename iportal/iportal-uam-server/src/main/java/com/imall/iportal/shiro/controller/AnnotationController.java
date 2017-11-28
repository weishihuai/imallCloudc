package com.imall.iportal.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yang
 */
@Controller
@RequestMapping("/test")
public class AnnotationController {

    @RequestMapping("/hello1")
    public void hello1(ModelMap model) {
        SecurityUtils.getSubject().checkRole("tt12");
        model.put("result", "ok");
    }

    @RequiresRoles("admin")
    @RequestMapping("/hello2")
    public void hello2(ModelMap model) {
        model.put("result", "ok2");
    }
}
