package com.imall.iportal.core.main.vo;

/**
 * Created by yang on 2015-10-29.
 */
public class ResultVo {

    private boolean success = false;
    private String msg;
    private Object obj;

    public ResultVo(){
    }

    public ResultVo(boolean success, String msg){
        this.success = success;
        this.msg = msg;
    }

    public ResultVo(boolean success, String msg, Object obj){
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
