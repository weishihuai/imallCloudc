package com.imall.iportal.frontend.wechat.common.servlet;

import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.frontend.wechat.common.utils.MsgUtil;
import com.imall.iportal.frontend.wechat.common.utils.SignUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * User: yang
 * Date: 14-3-17
 * Time: 下午4:37
 * 处理微信服务器发来的消息
 */
public class WeixinAcceptMsgServlet extends HttpServlet {

    private static final long serialVersionUID = 4454212588015635042L;

    private static Logger logger = Logger.getLogger(WeixinAcceptMsgServlet.class.getName());

    /**
     * 确认请求来自微信服务器
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("执行WeixinAcceptMsgServlet.doGet");
        System.out.println("System.out.println=执行WeixinAcceptMsgServlet.doGet");

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        System.out.println("signature=" + signature);
        System.out.println("timestamp=" + signature);
        System.out.println("nonce=" + signature);
        System.out.println("echostr=" + signature);

        PrintWriter out = response.getWriter();

        // 通过检验signature对请求进行校验,若校验成功则原样返回echostr,表示接入成功,否则接入失败

        if (SignUtil.checkSignature(ServiceManager.weChatApiService.getToken(), signature, timestamp, nonce)) {
            logger.info("WeixinAcceptMsgServlet.checkSignature=ok");
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("执行WeixinAcceptMsgServlet.doPost");
        System.out.println("System.out.println=执行WeixinAcceptMsgServlet.doPost");
        // 将请求、响应的编码均设置为UTF-8(防止中文乱码)
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        Map<String,String> map = null;
        try {
            map = MsgUtil.parseXml(request.getInputStream());
        } catch (Exception e) {
            logger.error(e);
        }
        String respMessage = ServiceManager.weChatApiService.acceptMsg(map);
        if(StringUtils.isNotBlank(respMessage)){
            // 响应消息
            PrintWriter out = response.getWriter();
            out.print(respMessage);
            out.close();
        }
    }

}
