package com.imall.iportal.shiro.controller;

import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yang on 2015-11-10.
 */
@Controller
@RequestMapping("/uam/server")
public class UamServerController {


    @RequestMapping("/unauthorized")
    public void unauthorized(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws BusinessException{
        throw new BusinessException(ResGlobal.OPERATION_NO_PERMISSION);
    }
}
