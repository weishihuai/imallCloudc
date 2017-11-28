package com.imall.iportal.frontend.wechat.common.utils;

/**
 * User: yang
 * Date: 14-3-17
 * Time: 下午5:05
 */

import com.imall.iportal.frontend.wechat.common.vo.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类
 *
 */
public class MsgUtil {


    /**
     * 解析微信发来的请求（XML）
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param textMsg 文本消息对象
     * @return xml
     */
    public static String textMsgToXml(TextMsg textMsg) {
        xstream.alias("xml", textMsg.getClass());
        return xstream.toXML(textMsg);
    }

    /**
     * 音乐消息对象转换成xml
     *
     * @param musicMsg 音乐消息对象
     * @return xml
     */
    public static String musicMsgToXml(MusicMsg musicMsg) {
        xstream.alias("xml", musicMsg.getClass());
        return xstream.toXML(musicMsg);
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param newsMsg 图文消息对象
     * @return xml
     */
    public static String newsMsgToXml(NewsMsg newsMsg) {
        xstream.alias("xml", newsMsg.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMsg);
    }

    /**
     * 多客服系统消息对象转换成xml
     *
     * @param customerServiceMsg 多客服系统消息
     * @return xml
     */
    public static String customerServiceMsgToXml(CustomerServiceMsg customerServiceMsg) {
        xstreamExt.alias("xml", customerServiceMsg.getClass());
        String time1 = "<CreateTime><![CDATA[" + customerServiceMsg.getCreateTime() +"]]></CreateTime>";
        String time2 = "<CreateTime>" + customerServiceMsg.getCreateTime() +"</CreateTime>";
        return xstreamExt.toXML(customerServiceMsg).replace(time1, time2);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstreamExt = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
