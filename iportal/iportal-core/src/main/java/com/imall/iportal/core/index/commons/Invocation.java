package com.imall.iportal.core.index.commons;

/**
 * Created by ygw on 2016/4/12.
 */
public interface Invocation {

    String getRequestId();

    byte getType();

    String getAppName();

    String getAppKey();

    String getSign();

    String getInterfaceName();

    String getServiceVersion();

    String getMethodName();

    Class<?>[] getParameterTypes();

    Object[] getParameters();

    void setAttachment(String key, String value);

    String getAttachment(String key);

}
