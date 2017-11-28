package com.imall.iportal.core.index.commons;

/**
 * 封装 Http 响应
 */
public class HttpResponse extends HttpObject implements Result{

    private Exception exception;
    private Object result;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean hasException() {
        return exception != null;
    }

    @Override
    public Object getValue() {
        return getResult();
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
