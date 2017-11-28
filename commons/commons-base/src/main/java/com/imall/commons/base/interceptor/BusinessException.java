package com.imall.commons.base.interceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务逻辑异常封装类，当在业务逻辑处理时出现异常，可抛出此异常，由全局定义的BusinessExceptionHandler处理。
 *  if (service.isContractDuplicated(contractNo))
 ×	  throw new BusinessException("bizErr.duplicatedContractNo",new String[]{contractNo});//根据具体情况，传入的参数最多可至4个
 *  
 * @author Jim Wu 
 * @since 1.0
 */
public class BusinessException extends RuntimeException {


	private static final long serialVersionUID = -7970756494558100175L;

    private String code;
	private Object[] args = new Object[0];
    private Object viewData; //to be presented in the exception error page,will be stored as request attribute with key of Constants.RESULT_KEY

    public Object getViewData() {
        return viewData;
    }

    public void setViewData(Object viewData) {
        this.viewData = viewData;
    }


	@Override
	public boolean equals(Object obj) {
    	if(obj == null){
    		return false;
    	}
    	if(!(obj instanceof BusinessException)){
    		return false;
    	}
    	BusinessException targetBusinessException = (BusinessException)obj;
    	if(!targetBusinessException.getCode().equals(this.getCode())){
    		return false;
    	}
    	Object[] targetArgs = targetBusinessException.getArgs();
    	if(getArgs().length != targetArgs.length){
    		return false;
    	}
    	if(getArgs().length == 0){
    		return true;
    	}
    	for(int i = 0; i < getArgs().length; i ++){
    		if(!getArgs()[i].equals(targetArgs[i])){
    			return false;
    		}
    	}
    	return true;
	}

	@Override
	public int hashCode() { //NOSONAR
		return super.hashCode(); //NOSONAR
	}

	public String getCode(){
		return code;
	}

	public Object[] getArgs(){
		return args;
	}

	public BusinessException(String code){
		super();
		this.code = code;
	}

	public BusinessException(String code, Object[] args){
		super();
		this.code = code ;
		this.args = args;
	}

	public BusinessException(String code, Object[] args, String message){
		super(message);
		this.code = code;
		this.args = args;
	}

	public BusinessException(String code, Throwable cause){
		super(cause);
		this.code = code;
	}

	public BusinessException(String code, Object[] args, String message, Throwable cause){
		super(message,cause);
		this.code = code;
		this.args = args;
	}

    public BusinessException(String code, Object viewData){
		super();
		this.code = code;
		this.viewData = viewData;
	}

	public BusinessException(String code, Object[] args, Object viewData){
		super();
		this.code = code ;
		this.args = args;
        this.viewData = viewData;
	}

	public BusinessException(String code, Object[] args, String message, Object viewData){
		super(message);
		this.code = code;
		this.args = args;
        this.viewData = viewData;
	}

	public BusinessException(String code, Throwable cause, Object viewData){
		super(cause);
		this.code = code;
        this.viewData = viewData;
	}

	public BusinessException(String code, Object[] args, String message, Throwable cause, Object viewData){
		super(message,cause);
		this.code = code;
		this.args = args;
        this.viewData = viewData;
	}
}