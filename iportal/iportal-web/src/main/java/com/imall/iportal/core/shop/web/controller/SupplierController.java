package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.vo.ResultVo;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.vo.SupplierSaveVo;
import com.imall.iportal.core.shop.vo.SupplierSearchParam;
import com.imall.iportal.core.shop.vo.SupplierUpdateVo;
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
@RequestMapping("/supplier")
public class SupplierController extends BaseRestSpringController {

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.supplierService.findById(id,currUserVo.getShopId());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody SupplierSaveVo supplierSaveVo,@CurrUser CurrUserVo currUserVo) throws ParseException {
        supplierSaveVo.setShopId(currUserVo.getShopId());
        return ServiceManager.supplierService.save(supplierSaveVo);
    }
    @RequestMapping(value = "/checkNameIsExist", method = RequestMethod.POST)
    @ResponseBody
    public Object checkNameIsExist(@RequestBody Supplier supplier, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.supplierService.checkNameIsExist(supplier.getSupplierNm(), supplier.getId(),currUserVo.getShopId());
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody SupplierUpdateVo supplierUpdateVo,@CurrUser CurrUserVo currUserVo) throws ParseException {
        supplierUpdateVo.setShopId(currUserVo.getShopId());
        return ServiceManager.supplierService.update(supplierUpdateVo);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, SupplierSearchParam supplierSearchParam,@CurrUser CurrUserVo currUserVo) throws ParseException {
        supplierSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.supplierService.querySupplier(pageable,supplierSearchParam);
    }

    @RequestMapping(value = "/updateSupplierState", method = RequestMethod.GET)
    @ResponseBody
    public Object updateSupplierState( Long id, String state,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.supplierService.updateSupplierState(id, state,currUserVo.getShopId());
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    @ResponseBody
    public Object queryPage(@PageableDefault(page = 0, size = 10)Pageable pageable, String supplierNm, String certificatesNum,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.supplierService.queryPage(pageable, supplierNm,"BUSINESS_LICENSE", certificatesNum,currUserVo.getShopId());
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public Object exportExcel(ModelMap model, @CurrUser CurrUserVo currUserVo, SupplierSearchParam supplierSearchParam) throws ParseException {
        supplierSearchParam.setShopId(currUserVo.getShopId());
        Pageable pageable = new PageRequest(0, 65000);
        String xlsTemplatePath = WebContextFactory.getWebRealPath() + "xlstemplate/Supplier.xls";
        SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
        String xlsOutputPath = "xlsoutput/Supplier/" + getUUIDPath(sysUser.getUserName(), "x.xls");
        Boolean success = ServiceManager.supplierService.exportExcel(xlsTemplatePath, WebContextFactory.getWebRealPath() + xlsOutputPath, pageable, supplierSearchParam);
        if(!success){
            return getFailure();
        }
        ResultVo resultVo = getSuccess();
        resultVo.setMsg(xlsOutputPath);
        return resultVo;
    }

    private String getUUIDPath(String userLoginId, String fileName) {
        StringBuilder builder = new StringBuilder();
        builder.append("supplier").append("_").append(userLoginId).append("_");
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
        if(currUserVo.getShopId() == null || currUserVo.getUserId() == null) {
            return  getFailure();
        }
        String baseDir = WebContextFactory.getLocalUploadPath();
        List<ErrorLog> errorLogList = ServiceManager.supplierService.importData(baseDir + fileId, currUserVo.getShopId());
        if(errorLogList.size() > 0){
            SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
            String xlsintputLogPath = "xlsinputlog/Supplier/" + getUUIDPath(sysUser.getUserName(), "x.txt");
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

