package com.imall.commons.base.util;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.print.*;

/**
 * 打印工具类
 * 1、目标打印机必须设置为默认打印机
 * 2、打印页面的宽度和具体的打印机有关，一般为打印纸的宽度
 * 3、打印页面的高度和具体打印内容的多少有关
 */
public class PrintUtil {
    private static Logger logger = Logger.getLogger(PrintUtil.class);

    /**
     * 打印小票
     * @param printable 打印对象
     * @param paperWidth 纸张宽度（固定，与具体的打印机有关）
     * @param paperHeight 纸张高度（与具体打印的内容长短有关）
     * @return 返回打印结果
     */
    public static Boolean printSmallTicket(Printable printable, int paperWidth, int paperHeight) throws PrinterException {
        //判断是否有打印设备
        if(PrinterJob.lookupPrintServices().length==0){
            logger.error("没有发现打印机服务,请确保打印机驱动已经正确安装，且设置为默认打印机");
            return false;
        }

        //设置页面打印格式
        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);     //设置打印起点从左上角开始，从左到右，从上到下打印

        //设置智障打印格式
        Paper paper = new Paper();
        paper.setSize(paperWidth, paperHeight);
        paper.setImageableArea(0, 0, paperWidth - 5, paperHeight);   //设置打印区域，包括打印起点坐标、打印的宽度和高度
        pageFormat.setPaper(paper);

        //创建打印文档
        Book book = new Book();
        book.append(printable, pageFormat);

        //获取默认打印机（目标打印机必须设置为默认打印机）
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        //设置打印文档
        printerJob.setPageable(book);
        //打印
        printerJob.print();

        return true;
    }

    /**
     * 字符串输出
     * @param graphics2D    画笔
     * @param text          打印文本
     * @param x             打印起点 x 坐标
     * @param y             打印起点 y 坐标
     * @param lineWidth     行宽
     * @param lineHeight    行高
     * @return  返回终点 y 坐标
     */
    public static int drawString(Graphics2D graphics2D, String text, int x, int y, int lineWidth, int lineHeight){
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        if(fontMetrics.stringWidth(text)<lineWidth){
            graphics2D.drawString(text, x, y);
            return y;
        }else{
            char[] chars = text.toCharArray();
            int charsWidth = 0;
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<chars.length; i++){
                if((charsWidth + fontMetrics.charWidth(chars[i]))>lineWidth){
                    graphics2D.drawString(sb.toString(), x, y);
                    sb.setLength(0);
                    y = y + lineHeight;

                    charsWidth = fontMetrics.charWidth(chars[i]);
                    sb.append(chars[i]);
                }else{
                    charsWidth = charsWidth + fontMetrics.charWidth(chars[i]);
                    sb.append(chars[i]);
                }
            }

            if(sb.length()>0){
                graphics2D.drawString(sb.toString(), x, y);
                y = y + lineHeight;
            }

            return y - lineHeight;
        }
    }

    /**
     * 获取斜线头
     * @param graphics2D    画笔
     * @param width         宽度
     * @return
     */
    public static String getSlash(Graphics2D graphics2D, int width){
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int charsWidth = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (charsWidth<width){
            charsWidth = charsWidth + fontMetrics.charWidth('/');
            stringBuffer.append('/');
        }

        return stringBuffer.toString();
    }
}
