package com.imall.iportal.core.weshop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.PrintUtil;
import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.entity.Order;
import com.imall.iportal.core.shop.entity.OrderItem;
import com.imall.iportal.core.shop.entity.WeShop;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Date;

/**
 * 订单打印对象
 */
public class OrderPrintable implements Printable {
    private Long orderId;
    private int paperWidth;
    private int paperHeight;    //纸张高度（与具体打印的内容长短有关）
    private String userName;    //操作人姓名

    /**
     * @param orderId       订单 ID
     * @param paperWidth    纸张宽度
     * @param userName      操作人姓名
     */
    public OrderPrintable(Long orderId, int paperWidth, String userName){
        this.orderId = orderId;
        this.paperWidth = paperWidth;
        this.userName = userName;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(pageIndex>0){
            return NO_SUCH_PAGE;
        }

        Graphics2D graphics2D = (Graphics2D) graphics;
        Font plainFont5 = new Font("宋体", Font.PLAIN, 5);
        Font plainFont6 = new Font("宋体", Font.PLAIN, 6);
        Font plainFont7 = new Font("宋体", Font.PLAIN, 7);
        Font boldFont8 = new Font("宋体", Font.BOLD, 8);
        Color defaultColor = graphics2D.getColor();
        Color grey = new Color(145, 145, 145);

        //订单信息
        Order order = ServiceManager.orderService.findOne(this.orderId);
        int yIndex = 30;
        int lineHeight = 10;
        int lineWidth = paperWidth - 20;

        Stroke stroke = new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0,new float[]{4, 4},0);
        if(StringUtils.isNotBlank(order.getReceiverName()) && StringUtils.isNotBlank(order.getDeliveryAddr())){
            graphics2D.setFont(plainFont5);
            PrintUtil.drawString(graphics2D, PrintUtil.getSlash(graphics2D, ((paperWidth - 20)/5)*3), 10, 17, paperWidth - 20, 8);
            graphics2D.setFont(plainFont7);

            //收货信息
            yIndex = PrintUtil.drawString(graphics2D, "收货人：" + order.getReceiverName(), 10, yIndex, lineWidth, lineHeight);
            yIndex = PrintUtil.drawString(graphics2D, "收货地址：" + order.getDeliveryAddr() + (StringUtils.isNotBlank(order.getDetailAddr()) ? order.getDetailAddr() : ""), 10, yIndex + lineHeight, lineWidth, lineHeight);
            if(StringUtils.isNotBlank(order.getRemark())){
                yIndex = PrintUtil.drawString(graphics2D, "备注：" + order.getRemark(), 10, yIndex + lineHeight, lineWidth, lineHeight);
            }

            //收货信息边框
            graphics2D.setStroke(stroke);
            graphics2D.drawRect(5, 10, paperWidth - 11, yIndex);
        }else{
            yIndex = -15;
            graphics2D.setStroke(stroke);
        }

        //药店名称
        lineWidth = paperWidth - 10;
        lineHeight = 8;
        graphics2D.setFont(boldFont8);
        graphics2D.setColor(defaultColor);
        Shop shop = null;
        WeShop weShop = null;
        if(OrderSourceCodeEnum.SALES_POS.toCode().equals(order.getOrderSourceCode())){
            shop = ServiceManager.shopService.findOne(order.getShopId());
            yIndex = PrintUtil.drawString(graphics2D, shop.getEntNm(), 5, yIndex + lineHeight + 25, lineWidth, 12);
        }else{
            weShop = ServiceManager.weShopService.findOne(order.getWeShopId());
            yIndex = PrintUtil.drawString(graphics2D, weShop.getShopNm(), 5, yIndex + lineHeight + 25, lineWidth, 12);
        }

        //操作员
        graphics2D.setFont(plainFont6);
        graphics2D.setColor(grey);
        yIndex = PrintUtil.drawString(graphics2D, "操作员：" + userName, 5, yIndex + lineHeight + 2, lineWidth, lineHeight);
        yIndex = PrintUtil.drawString(graphics2D, "日期：" + DateTimeUtils.convertDateToString(new Date()), 5 + lineWidth/2, yIndex, lineWidth, lineHeight);

        //列头
        yIndex = PrintUtil.drawString(graphics2D, "品名", 5, yIndex + lineHeight * 2 - 5, lineWidth, lineHeight);
        yIndex = PrintUtil.drawString(graphics2D, "规格", (lineWidth/10)*3, yIndex, lineWidth, lineHeight);
        yIndex = PrintUtil.drawString(graphics2D, "单价", (lineWidth/10)*7, yIndex, lineWidth, lineHeight);
        yIndex = PrintUtil.drawString(graphics2D, "数量", (lineWidth/10)*9 + 5, yIndex, lineWidth, lineHeight);

        //订单项信息
        long goodsNum = 0;
        java.util.List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(order.getId());
        for(OrderItem orderItem : orderItemList){
            graphics2D.setFont(plainFont7);
            yIndex = PrintUtil.drawString(graphics2D, orderItem.getCommonNm(), 5, yIndex + 15, (lineWidth/10)*7, 10);

            graphics2D.setFont(plainFont6);
            graphics2D.setColor(grey);
            yIndex = PrintUtil.drawString(graphics2D, orderItem.getSpec(), 5, yIndex + 11, lineWidth, lineHeight);
            yIndex = PrintUtil.drawString(graphics2D, String.valueOf(orderItem.getGoodsUnitPrice()), (lineWidth/10)*7, yIndex, lineWidth, lineHeight);
            yIndex = PrintUtil.drawString(graphics2D, String.valueOf(orderItem.getQuantity()), (lineWidth/10)*9 + 5, yIndex, lineWidth, lineHeight);
            graphics2D.setFont(plainFont7);
            yIndex = yIndex + 2;
            graphics2D.drawLine(5, yIndex, 5 + lineWidth, yIndex);
            goodsNum = goodsNum + orderItem.getQuantity();
        }

        //结算信息
        if(order.getMemberId()!=null){
            Member member = ServiceManager.memberService.findOne(order.getMemberId());
            graphics2D.setColor(defaultColor);
            yIndex = PrintUtil.drawString(graphics2D, "会员名称：" + member.getName(), 5, yIndex + lineHeight * 2, lineWidth, lineHeight);
        }
        yIndex = PrintUtil.drawString(graphics2D, "总    数：" + goodsNum, 5, (order.getMemberId()==null ? yIndex + lineHeight * 2 : yIndex + lineHeight), lineWidth, lineHeight);
        if(order.getFreightAmount()>0){
            yIndex = PrintUtil.drawString(graphics2D, "运    费：" + order.getFreightAmount(), 5, yIndex + lineHeight, lineWidth, lineHeight);
        }
        yIndex = PrintUtil.drawString(graphics2D, "总    计：" + order.getOrderTotalAmount(), 5, yIndex + lineHeight, lineWidth, lineHeight);
        yIndex = PrintUtil.drawString(graphics2D, "收    款：" + order.getRealGeveCashAmount(), 5, yIndex + lineHeight, lineWidth, lineHeight);
        yIndex = PrintUtil.drawString(graphics2D, "找    零：" + order.getReturnSmall(), 5, yIndex + lineHeight, lineWidth, lineHeight);

        graphics2D.setFont(plainFont6);
        graphics2D.setColor(grey);
        if(OrderSourceCodeEnum.SALES_POS.toCode().equals(order.getOrderSourceCode())){
            yIndex = PrintUtil.drawString(graphics2D, "电话：" + shop.getCompanyTel(), 5, yIndex + lineHeight * 2, lineWidth, lineHeight);
            yIndex = PrintUtil.drawString(graphics2D, "地址：" + shop.getCompanyAddr(), 5, yIndex + lineHeight, lineWidth, lineHeight);

        }else{
            yIndex = PrintUtil.drawString(graphics2D, "电话：" + weShop.getContactTel(), 5, yIndex + lineHeight * 2, lineWidth, lineHeight);
            yIndex = PrintUtil.drawString(graphics2D, "地址：" + weShop.getDetailLocation(), 5, yIndex + lineHeight, lineWidth, lineHeight);

        }

        yIndex = yIndex + 30;
        graphics2D.drawLine(0, yIndex, paperWidth, yIndex);

        paperHeight = yIndex + 30;
        return PAGE_EXISTS;
    }

    public int getPaperHeight() throws PrinterException {
        BufferedImage bi = new BufferedImage(120,120, BufferedImage.TYPE_INT_ARGB);
        this.print(bi.createGraphics(), null, 0);
        return paperHeight;
    }
}
