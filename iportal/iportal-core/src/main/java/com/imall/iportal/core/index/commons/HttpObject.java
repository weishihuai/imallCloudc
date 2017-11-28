package com.imall.iportal.core.index.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ygw on 2016/5/13.
 */
public class HttpObject {

    protected String requestId;
    protected byte type;// 消息类型
    protected String appName;
    protected String appKey;
    protected String sign;
    protected String protocol = "http";
    private Map<String, String> attachmentMap = new HashMap<>();

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Map<String, String> getAttachmentMap() {
        return attachmentMap;
    }

    public void setAttachmentMap(Map<String, String> attachmentMap) {
        this.attachmentMap = attachmentMap;
    }

    public void setAttachment(String key, String value){
        if(attachmentMap!=null){
            attachmentMap.put(key, value);
        }
    }

    public String getAttachment(String key){
        if(attachmentMap!=null){
            return attachmentMap.get(key);
        }
        return null;
    }
}
