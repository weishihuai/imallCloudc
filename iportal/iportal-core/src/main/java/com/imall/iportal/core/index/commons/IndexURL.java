package com.imall.iportal.core.index.commons;

import com.imall.commons.base.util.URLEncoderUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IndexURL {

    private static final long serialVersionUID = -1985165475234910535L;
    private static final int DEFAULT_PORT = 80;

    public static final String PARAM_WEIGHT = "weight";

    private String host;
    private int port;
    private ConcurrentMap<String, String> parametersMap = new ConcurrentHashMap<>();
    private volatile transient String url;
    private volatile transient String uri;
    private volatile transient String address;
    private volatile transient String serviceName;
    private volatile transient String methodName;

    //192.168.1.2:8080/com.imall.soa.sample.api.HelloService?weight=200
    public IndexURL(String url){
        this.url = url;
        init();
    }

    public IndexURL(String serviceName, String address){
        this.url = address + "/" + serviceName;
        init();
    }

    public IndexURL(String serviceName, String address, String parameters){
        this.url = address + "/" + serviceName + "?" + parameters;
        init();
    }

    //http://192.168.1.2:8080/com.imall.soa.sample.api.HelloService/test?a=fd
    //http://IP:PORT/SERVICE_NAME/METHOD_NAME/PARAMTERS
    private void init(){
        url = url.replace("http://", "");
        if(url.contains("/")){
            this.address = url.split("/")[0];
            if(address.contains(":")){
                this.host = address.split(":")[0];
                this.port = Integer.valueOf(address.split(":")[1]);
            } else {
                this.host = address;
                this.port = DEFAULT_PORT;
            }

            if (url.split("/").length > 2) {
                this.serviceName = url.split("/")[1];
                String urlContent = url.split("/")[2];
                if(urlContent.contains("?")){
                    this.methodName = urlContent.split("\\?")[0];
                    String parameters = urlContent.split("\\?")[1];
                    String[] params = parameters.split("&");
                    for(String param:params){
                        parametersMap.put(param.split("=")[0], param.split("=")[1]);
                    }
                } else {
                    this.methodName = urlContent;
                }
            } else {
                String urlContent = url.split("/")[1];
                if(urlContent.contains("?")){
                    this.serviceName = urlContent .split("\\?")[0];
                    String parameters = urlContent.split("\\?")[1];
                    String[] params = parameters.split("&");
                    for(String param:params){
                        parametersMap.put(param.split("=")[0], param.split("=")[1]);
                    }
                } else {
                    this.serviceName = urlContent;
                }
            }


            //http://192.168.1.2:8080/com.imall.soa.sample.api.HelloService
            this.uri = this.address + "/" + this.serviceName;
        }
        else{
            String parameters = url;
            String[] params = parameters.split("&");
            for(String param:params){
                parametersMap.put(param.split("=")[0], param.split("=")[1]);
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getParameters() {
        StringBuilder parametersBuff = new StringBuilder();
        for(Map.Entry<String, String> entry: parametersMap.entrySet()){
            if(parametersBuff.length() > 0){
                parametersBuff.append("&");
            }
            parametersBuff.append(entry.getKey());
            parametersBuff.append("=");
            parametersBuff.append(entry.getValue());
        }
        return parametersBuff.toString();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getParameterValueByName(String name){
        return parametersMap.get(name);
    }

    public String setParameterValueByName(String name, String value){
        return parametersMap.put(name, value);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getParameterAndDecoded(String key) {
        return decode(getParameterValueByName(key));
    }


    public static String encode(String value) {
        return URLEncoderUtil.encode(value);
    }

    public static String decode(String value) {
        return URLEncoderUtil.decode(value);
    }

    public ConcurrentMap<String, String> getParametersMap() {
        return parametersMap;
    }

    public void setParameterAndEncode(String key, String value){
        parametersMap.put(key, decode(value));
    }

    public Map toMap(){
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("host", getHost());
        map.put("port", String.valueOf(getPort()));
        return map;
    }
}