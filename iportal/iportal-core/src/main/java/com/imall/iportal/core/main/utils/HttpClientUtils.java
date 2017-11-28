package com.imall.iportal.core.main.utils;


import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lxh on 2016/9/19.
 */
public class HttpClientUtils {
    private static PoolingHttpClientConnectionManager cm = null;
    private static CloseableHttpClient httpClient = null;
    private static final String DEFAULT_CHARSET = "UTF-8";

    private HttpClientUtils() {
    }

    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加到200
        cm.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        cm.setDefaultMaxPerRoute(20);
    }

    public synchronized static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = HttpClients.custom()
                    .setConnectionManager(cm)
                    .build();
        }
        return httpClient;
    }

    private static HttpEntity getHttpEntity(String url) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClientUtils.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet, HttpClientContext.create());
        return response.getEntity();
    }

    public static String doGet(String url) throws IOException {
        return HttpClientUtils.getMethod(url, DEFAULT_CHARSET, null);
    }

    public static String doGet(String url, String charset) throws IOException {
        return HttpClientUtils.getMethod(url, charset, null);
    }

    public static String doGet(String url, Map<String, Object> params) throws IOException {
        return HttpClientUtils.getMethod(url, DEFAULT_CHARSET, params);
    }

    public static String doGet(String url, String charset, Map<String, Object> params) throws IOException {
        return HttpClientUtils.getMethod(url, charset, params);
    }

    public static String doPost(String url, Map<String, Object> params) throws IOException {
        return HttpClientUtils.postMethod(url, DEFAULT_CHARSET, params);
    }

    public static String doPost(String url, String charset, Map<String, Object> params) throws IOException {
        return HttpClientUtils.postMethod(url, charset, params);
    }

    private static String getMethod(String url, String charset, Map<String, Object> params) throws IOException {
        String queryString = HttpClientUtils.getQueryString(params);
        String requestUrl = StringUtils.isBlank(queryString) ? url : url + "?" + queryString;
        HttpEntity httpEntity = HttpClientUtils.getHttpEntity(requestUrl);
        String result = EntityUtils.toString(httpEntity, charset);
        EntityUtils.consume(httpEntity);
        return result;
    }

    private static String postMethod(String url, String charset, Map<String, Object> params) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClientUtils.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if (!CollectionUtils.isEmpty(params)) {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                Object val = params.get(key);
                if (val == null) {
                    continue;
                }
                if (val instanceof String) {
                    pairs.add(new BasicNameValuePair(key, (String) val));
                } else if (val instanceof Number) {
                    pairs.add(new BasicNameValuePair(key, val.toString()));
                } else if (val instanceof Number[]) {
                    Number[] arr = (Number[]) val;
                    for (Number a : arr) {
                        pairs.add(new BasicNameValuePair(key, a.toString()));
                    }
                } else if (val instanceof String[]) {
                    String[] arr = (String[]) val;
                    for (String a : arr) {
                        pairs.add(new BasicNameValuePair(key, a));
                    }
                } else if (val instanceof int[]) {
                    int[] arr = (int[]) val;
                    for (int a : arr) {
                        pairs.add(new BasicNameValuePair(key, String.valueOf(a)));
                    }
                } else if (val instanceof double[]) {
                    double[] arr = (double[]) val;
                    for (double a : arr) {
                        pairs.add(new BasicNameValuePair(key, String.valueOf(a)));
                    }
                } else if (val instanceof long[]) {
                    long[] arr = (long[]) val;
                    for (long a : arr) {
                        pairs.add(new BasicNameValuePair(key, String.valueOf(a)));
                    }
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
        }
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, charset);
        EntityUtils.consume(entity);
        return result;
    }

    private static String getQueryString(Map<String, Object> params) {
        if (!CollectionUtils.isEmpty(params)) {
            StringBuilder query = new StringBuilder();
            for (String key : params.keySet()) {
                Object val = params.get(key);
                if (val == null) {
                    continue;
                }
                if (val instanceof String || val instanceof Number) {
                    query.append(key).append("=").append(val).append("&");
                } else if (val instanceof Number[]) {
                    Number[] arr = (Number[]) val;
                    for (Number a : arr) {
                        query.append(key).append("=").append(a).append("&");
                    }
                } else if (val instanceof String[]) {
                    String[] arr = (String[]) val;
                    for (String a : arr) {
                        query.append(key).append("=").append(a).append("&");
                    }
                } else if (val instanceof int[]) {
                    int[] arr = (int[]) val;
                    for (int a : arr) {
                        query.append(key).append("=").append(a).append("&");
                    }
                } else if (val instanceof double[]) {
                    double[] arr = (double[]) val;
                    for (double a : arr) {
                        query.append(key).append("=").append(a).append("&");
                    }
                } else if (val instanceof long[]) {
                    long[] arr = (long[]) val;
                    for (long a : arr) {
                        query.append(key).append("=").append(a).append("&");
                    }
                }
            }
            String temp = query.toString();
            return temp.substring(0, temp.length() - 1);
        }
        return "";
    }
}
