package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.vo.ResultVo;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.platform.entity.GoodsDoc;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.vo.*;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.apache.commons.io.FileUtils;
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
@RequestMapping("/goods")
public class GoodsController extends BaseRestSpringController {


    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsService.findDetail(id,currUserVo.getShopId());
//        return ServiceManager.esIndexQueueService.rebuildIndex(IndexTypeCodeEnum.GOODS);
    }

    @RequestMapping(value = "/isGoodsCodeExist", method = RequestMethod.POST)
    @ResponseBody
    public Object findGoodsCodeExist(@RequestBody GoodsDoc goodsDoc, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsService.findGoodsCodeExist(goodsDoc.getGoodsCode(), goodsDoc.getId(),currUserVo.getShopId());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody GoodsSaveVo goodsSaveVo,@CurrUser CurrUserVo currUserVo) {
        goodsSaveVo.setShopId(currUserVo.getShopId());
        return ServiceManager.goodsService.saveGoods(goodsSaveVo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody GoodsUpdateVo goodsUpdateVo,@CurrUser CurrUserVo currUserVo) throws ParseException {
        goodsUpdateVo.setShopId(currUserVo.getShopId());
        return ServiceManager.goodsService.updateGoods(goodsUpdateVo);
    }

    @RequestMapping(value = "/updateStartUsing", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStartUsing(@RequestBody GoodsUpdateIsEnableParam goodsUpdateIsEnableParam, @CurrUser CurrUserVo currUserVo){
        goodsUpdateIsEnableParam.setShopId(currUserVo.getShopId());
        return ServiceManager.goodsService.updateStartUsing(goodsUpdateIsEnableParam);
    }

    @RequestMapping(value = "/updateBlockUp", method = RequestMethod.POST)
    @ResponseBody
    public Object updateBlockUp(@RequestBody GoodsUpdateIsEnableParam goodsUpdateIsEnableParam, @CurrUser CurrUserVo currUserVo){
        goodsUpdateIsEnableParam.setShopId(currUserVo.getShopId());
        return ServiceManager.goodsService.updateBlockUp(goodsUpdateIsEnableParam);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestBody List<Long> ids) {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, GoodsListSearchParam goodsListSearchParam,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsService.query(pageable, goodsListSearchParam, currUserVo.getShopId());
    }

    @RequestMapping(value = "/commonGoodsList", method = RequestMethod.GET)
    @ResponseBody
    public Object commonList(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, GoodsCommonComponentSearchParam goodsCommonComponentSearchParam,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsService.queryGoodsCommonComponent(pageable, goodsCommonComponentSearchParam, currUserVo.getShopId());
    }

    @RequestMapping(value = "/listPosGoods", method = RequestMethod.GET)
    @ResponseBody
    public Object listPosGoods(ModelMap model, @CurrUser CurrUserVo currUserVo, String searchFields) {
        return ServiceManager.goodsService.listGoodsByPos(currUserVo.getShopId(), searchFields);
    }

    @RequestMapping(value = "/queryOutOfStockWarning", method = RequestMethod.GET)
    @ResponseBody
    public Object queryOutOfStockWarning(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, StockWarningSearchParam stockWarningSearchParam, @CurrUser CurrUserVo currUserVo) {
        stockWarningSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.goodsService.queryOutOfStockWarning(pageable, stockWarningSearchParam);
    }

    @RequestMapping(value = "/queryGoodsActualStock", method = RequestMethod.GET)
    @ResponseBody
    public Object queryGoodsActualStock(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, String searchFields) {
        return ServiceManager.goodsService.queryGoodsActualStock(pageable, currUserVo.getShopId(), searchFields);
    }

    @RequestMapping(value = "/weChatList", method = RequestMethod.GET)
    @ResponseBody
    public Object weChatList(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, WeChatGoodsListSearchParam weChatGoodsListSearchParam,@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.goodsService.queryForWeChat(pageable, weChatGoodsListSearchParam, currUserVo.getShopId());
    }

    @RequestMapping(value = "/queryDecorationRecommendGoods", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDecorationRecommendGoods(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, Long groupId, Long salesCategoryId, String keyword) {
        return ServiceManager.goodsService.queryDecorationRecommendGoods(pageable, groupId, currUserVo.getShopId(), salesCategoryId, keyword);
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public Object exportExcel(ModelMap model, @CurrUser CurrUserVo currUserVo, GoodsListSearchParam goodsListSearchParam) throws ParseException {
        String xlsTemplatePath = WebContextFactory.getWebRealPath() + "xlstemplate";
        SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
        String xlsOutputPath = "xlsoutput/Goods/" + getUUIDPath(sysUser.getUserName(), "x.zip");
        Boolean success = ServiceManager.goodsService.exportExcel(WebContextFactory.getWebRealPath(), xlsTemplatePath, WebContextFactory.getWebRealPath() + xlsOutputPath, goodsListSearchParam, currUserVo.getShopId());
        if(!success){
            return getFailure();
        }
        ResultVo resultVo = getSuccess();
        resultVo.setMsg(xlsOutputPath);
        return resultVo;
    }

    private String getUUIDPath(String userLoginId, String fileName) {
        StringBuilder builder = new StringBuilder();
        builder.append("goods").append("_").append(userLoginId).append("_");
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
        List<ErrorLog> errorLogList = ServiceManager.goodsService.importData(baseDir + fileId, currUserVo.getShopId());
        if(errorLogList.size() > 0){
            SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
            String xlsintputLogPath = "xlsinputlog/Goods/" + getUUIDPath(sysUser.getUserName(), "x.txt");
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

