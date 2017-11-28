package com.imall.iportal.core.platform.web.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.vo.ResultVo;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.platform.entity.GoodsDoc;
import com.imall.iportal.core.platform.vo.GoodsDocListSearchParam;
import com.imall.iportal.core.platform.vo.GoodsDocSaveVo;
import com.imall.iportal.core.platform.vo.GoodsDocUpdateVo;
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
@RequestMapping("/goodsDoc")
public class GoodsDocController extends BaseRestSpringController {

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id) {
        return ServiceManager.goodsDocService.findDetail(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody GoodsDocSaveVo goodsDocSaveVo) {
        return ServiceManager.goodsDocService.saveGoodsDoc(goodsDocSaveVo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody GoodsDocUpdateVo goodsDocUpdateVo) throws ParseException {
        return ServiceManager.goodsDocService.updateGoodsDoc(goodsDocUpdateVo);
    }

    @RequestMapping(value = "/updateDenyApprove", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDenyApprove(@RequestBody Long id)   {
        return ServiceManager.goodsDocService.updateDenyApprove(id);
    }

    @RequestMapping(value = "/updatePassApprove", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePassApprove(@RequestBody Long id)   {
        return ServiceManager.goodsDocService.updatePassApprove(id);
    }

    @RequestMapping(value = "/updateDelete", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDelete(@RequestBody Long id)   {
        return ServiceManager.goodsDocService.updateDelete(id);
    }

    @RequestMapping(value = "/isGoodsCodeExist", method = RequestMethod.POST)
    @ResponseBody
    public Object findGoodsCodeExist(@RequestBody GoodsDoc goodsDoc) {
        return ServiceManager.goodsDocService.findGoodsCodeExist(goodsDoc.getGoodsCode(), goodsDoc.getId());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, GoodsDocListSearchParam goodsDocListSearchParam) throws ParseException {
        return ServiceManager.goodsDocService.query(pageable, goodsDocListSearchParam);
    }


    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public Object exportExcel(ModelMap model, @CurrUser CurrUserVo currUserVo, GoodsDocListSearchParam goodsDocListSearchParam) throws ParseException {
        Pageable pageable = new PageRequest(0, Integer.MAX_VALUE);
        String xlsTemplatePath = WebContextFactory.getWebRealPath() + "xlstemplate";
        SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
        String xlsOutputPath = "xlsoutput/GoodsDoc/" + getUUIDPath(sysUser.getUserName(), "x.zip");
        Boolean success = ServiceManager.goodsDocService.exportExcel(WebContextFactory.getWebRealPath(), xlsTemplatePath, WebContextFactory.getWebRealPath() + xlsOutputPath, pageable, goodsDocListSearchParam);
        if(!success){
            return getFailure();
        }
        ResultVo resultVo = getSuccess();
        resultVo.setMsg(xlsOutputPath);
        return resultVo;
    }

    private String getUUIDPath(String userLoginId, String fileName) {
        StringBuilder builder = new StringBuilder();
        builder.append("goodsdoc").append("_").append(userLoginId).append("_");
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
        if(!Global.ADMIN_DEFAULT_ORG_ID.equals(currUserVo.getOrgId())) {
            return getFailure();
        }
        String baseDir = WebContextFactory.getLocalUploadPath();
        List<ErrorLog> errorLogList = ServiceManager.goodsDocService.importData(baseDir + fileId);
        if(errorLogList.size() > 0){
            SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
            String xlsintputLogPath = "xlsinputlog/GoodsDoc/" + getUUIDPath(sysUser.getUserName(), "x.txt");
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

