<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page import="com.imall.commons.base.interceptor.BusinessException" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setAttribute("ctx",request.getContextPath());%>


<%
    String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
    LogFactory.getLog(requestUri).error(exception.getMessage(), exception);
    boolean isCustomException = exception instanceof BusinessException&&((BusinessException) exception).getViewData()!="";
    request.setAttribute("isCustomException",isCustomException);
    if(isCustomException){
        BusinessException businessException = (BusinessException) exception;
        request.setAttribute("viewData",businessException.getViewData());
        response.addHeader("code", businessException.getCode());
        response.addHeader("error", URLEncoder.encode(businessException.getViewData().toString(), "UTF-8"));
    }else {
        response.addHeader("code", "system.error");
        response.addHeader("error", URLEncoder.encode("系统繁忙，请稍后重试", "UTF-8"));
    }
%>

<c:if test="${isCustomException}">
    <div id="customException">${viewData}</div>
</c:if>

<c:if test="${!isCustomException}">
    <div id="customException">对不起,发生系统内部错误,不能处理你的请求,请联系系统管理员</div>
</c:if>