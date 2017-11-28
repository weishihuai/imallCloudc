package com.imall.iportal.core.shop.web.controller;

import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lxh on 2017/7/12.
 * 打印专用controller
 */
@Controller
@RequestMapping("/printer")
public class PrinterController {

    //采购单打印
    @RequestMapping(value = "/printPurchaseOrder-{id}.html", method = RequestMethod.GET)
    public String printPurchaseOrder(ModelMap model, @CurrUser CurrUserVo sysUser, @PathVariable Long id) {
        model.put("printTemplateVo", ServiceManager.purchaseOrderService.getPurchaseOrderPrintTemplateVoByIdAndShopId(id, sysUser.getShopId()));
        return "printer/purchase/printPurchaseOrder";
    }

    //采购收货单打印
    @RequestMapping(value = "/printPurchaseReceive-{id}.html", method = RequestMethod.GET)
    public String printPurchaseReceive(ModelMap model, @CurrUser CurrUserVo sysUser, @PathVariable Long id) {
        model.put("printTemplateVo", ServiceManager.purchaseReceiveRecordService.getPurchaseReceivePrintTemplateVoByIdAndShopId(id, sysUser.getShopId()));
        return "printer/purchase/printPurchaseReceive";
    }

    //采购验收单打印
    @RequestMapping(value = "/printPurchaseAcceptance-{id}.html", method = RequestMethod.GET)
    public String printPurchaseAcceptance(ModelMap model, @CurrUser CurrUserVo sysUser, @PathVariable Long id) {
        model.put("printTemplateVo", ServiceManager.purchaseAcceptanceRecordService.getPurchaseAcceptancePrintTemplateVoByIdAndShopId(id, sysUser.getShopId()));
        return "printer/purchase/printPurchaseAcceptance";
    }

    //购进退出单打印
    @RequestMapping(value = "/printReturnedPurchase-{id}.html", method = RequestMethod.GET)
    public String printReturnedPurchase(ModelMap model, @CurrUser CurrUserVo sysUser, @PathVariable Long id) {
        model.put("printTemplateVo", ServiceManager.returnedPurchaseOrderService.getReturnedPurchasePrintTemplateVoByIdAndShopId(id, sysUser.getShopId()));
        return "printer/purchase/printReturnedPurchase";
    }
}
