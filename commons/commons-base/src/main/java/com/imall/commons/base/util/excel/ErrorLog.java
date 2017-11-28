package com.imall.commons.base.util.excel;

/**
 * Created by IntelliJ IDEA.
 * User: yang
 * Date: 12-5-4
 * Time: 下午3:49
 * To change this template use File | Settings | File Templates.
 */
public class ErrorLog {
    private int lineNumber;
    private String errorMessage;

    public ErrorLog() {
    }

    public ErrorLog(int lineNumber, String errorMessage) {
        this.lineNumber = lineNumber;
        this.errorMessage = errorMessage;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
