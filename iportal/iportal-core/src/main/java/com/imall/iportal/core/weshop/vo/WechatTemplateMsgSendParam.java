package com.imall.iportal.core.weshop.vo;

import java.util.Map;

/**
 * 微信模板消息发送参数包装
 * <p>
 * 接口调用请求说明
 * http请求方式: POST
 * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
 * POST数据说明
 * POST数据示例如下：
 * {
 * "touser":"OPENID",
 * "template_id":"xxxxxxxxxxxxxxx",
 * "url":"http://weixin.qq.com/download",
 * "miniprogram":{
 * "appid":"xiaochengxuappid12345",
 * "pagepath":"index?foo=bar"
 * },
 * "data":{
 * "first": {
 * "value":"恭喜你购买成功！",
 * "color":"#173177"
 * },
 * "keynote1":{
 * "value":"巧克力",
 * "color":"#173177"
 * },
 * "keynote2": {
 * "value":"39.8元",
 * "color":"#173177"
 * },
 * "keynote3": {
 * "value":"2014年9月22日",
 * "color":"#173177"
 * },
 * "remark":{
 * "value":"欢迎再次购买！",
 * "color":"#173177"
 * }
 * }
 * }
 * <p>
 * 参数说明
 * 参数	       是否必填	         说明
 * touser	         是      	 接收者openid
 * template_id     是	         模板ID
 * url	         否	         模板跳转链接
 * miniprogram     否          跳小程序所需数据，不需跳小程序可不用传该数据
 * appid      	 是          所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
 * pagepath	     是          所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
 * data	         是	         模板数据
 * <p>
 * 注：url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
 * <p>
 * 返回码说明
 * 在调用模板消息接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
 * {
 * "errcode":0,
 * "errmsg":"ok",
 * "msgid":200228332
 * }
 */
public class WechatTemplateMsgSendParam {
    private String touser;
    private String template_id;
    private String url;
    private Miniprogram miniprogram;
    private Map<String, DataVo> data;


    /**
     * 小程序参数
     */
    public static class Miniprogram{
        String appid;
        String pagepath;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagepath() {
            return pagepath;
        }

        public void setPagepath(String pagepath) {
            this.pagepath = pagepath;
        }
    }

    /**
     * data参数细分
     */
    public static class DataVo {
        private String value;
        private String color;

        public DataVo() {
        }

        public DataVo(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, DataVo> getData() {
        return data;
    }

    public void setData(Map<String, DataVo> data) {
        this.data = data;
    }

    public Miniprogram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Miniprogram miniprogram) {
        this.miniprogram = miniprogram;
    }
}
