package com.imall.iportal.interceptor;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.interceptor.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: lxd
 * Date: 2010-12-26
 * Time: 16:56:13
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BusinessExceptionResolver extends AbstractHandlerExceptionResolver {
	/**
	 * The default name of the exception attribute: "exception".
	 */
	public static final String DEFAULT_EXCEPTION_ATTRIBUTE = "exception";
	private Logger log = Logger.getLogger(this.getClass());

	private Properties exceptionMappings;

	private String defaultErrorView;

	private Integer defaultStatusCode;

	private Map<String, Integer> statusCodes = new HashMap<String, Integer>();

	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;

	public void setExceptionMappings(Properties mappings) {
		this.exceptionMappings = mappings;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}


	public void setStatusCodes(Properties statusCodes) {
		for (Enumeration enumeration = statusCodes.propertyNames(); enumeration.hasMoreElements();) {
			String viewName = (String) enumeration.nextElement();
			Integer statusCode = Integer.valueOf(statusCodes.getProperty(viewName));
			this.statusCodes.put(viewName, statusCode);
		}
	}

	public void setDefaultStatusCode(int defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}


	public void setExceptionAttribute(String exceptionAttribute) {
		this.exceptionAttribute = exceptionAttribute;
	}


	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
											  HttpServletResponse response,
											  Object handler,
											  Exception ex) {
		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		if (viewName != null) {
			// Apply HTTP status code for error views, if specified.
			// Only apply it if we're processing a top-level request.
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			return getModelAndView(viewName, ex, request);
		} else {
			return null;
		}
	}


	protected String determineViewName(Exception ex, HttpServletRequest request) {
		String viewName = null;
		// Check for specific exception mappings.
		if (this.exceptionMappings != null) {
			viewName = findMatchingViewName(this.exceptionMappings, ex);
		}
		// Return default error view else, if defined.
		if (viewName == null && this.defaultErrorView != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Resolving to default view '" + this.defaultErrorView + "' for exception of type [" +
						ex.getClass().getName() + "]");
			}
			viewName = this.defaultErrorView;
		}
		return viewName;
	}


	protected String findMatchingViewName(Properties exceptionMappings, Exception ex) {
		String viewName = null;
		String dominantMapping = null;
		int deepest = Integer.MAX_VALUE;
		for (Enumeration names = exceptionMappings.propertyNames(); names.hasMoreElements();) {
			String exceptionMapping = (String) names.nextElement();
			int depth = getDepth(exceptionMapping, ex);
			if (depth >= 0 && depth < deepest) {
				deepest = depth;
				dominantMapping = exceptionMapping;
				viewName = exceptionMappings.getProperty(exceptionMapping);
			}
		}
		if (viewName != null && logger.isDebugEnabled()) {
			logger.debug("Resolving to view '" + viewName + "' for exception of type [" + ex.getClass().getName() +
					"], based on exception mapping [" + dominantMapping + "]");
		}
		return viewName;
	}


	protected int getDepth(String exceptionMapping, Exception ex) {
		return getDepth(exceptionMapping, ex.getClass(), 0);
	}

	private int getDepth(String exceptionMapping, Class exceptionClass, int depth) {
		if (exceptionClass.getName().contains(exceptionMapping)) {
			// Found it!
			return depth;
		}
		// If we've gone as far as we can go and haven't found it...
		if (exceptionClass.equals(Throwable.class)) {
			return -1;
		}
		return getDepth(exceptionMapping, exceptionClass.getSuperclass(), depth + 1);
	}


	protected Integer determineStatusCode(HttpServletRequest request, String viewName) {
		if (this.statusCodes.containsKey(viewName)) {
			return this.statusCodes.get(viewName);
		}
		return this.defaultStatusCode;
	}

	protected void applyStatusCodeIfPossible(HttpServletRequest request, HttpServletResponse response, int statusCode) {
		if (!WebUtils.isIncludeRequest(request)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Applying HTTP status code " + statusCode);
			}
			response.setStatus(statusCode);
			request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, statusCode);
		}
	}

	protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		String errorPage = (String)request.getAttribute(Global.ERRORPAGE);
		ModelAndView mv = new ModelAndView(errorPage==null?viewName:errorPage);
		if (this.exceptionAttribute != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Exposing Exception as model attribute '" + this.exceptionAttribute + "'");
			}
			ErrorObject error = null;
			if (ex instanceof BusinessException) {
				BusinessException busException = (BusinessException) ex;
				//String message  = IportalClientUtils.getServicePrx(BusinessExceptionServiceRpcPrx.class).getMessageByCode(busException.getCode());
				//message = String.format(message, busException.getArgs());
				busException.setViewData(busException.getViewData());
				//String errorText = ((AbstractMessageSource) SpringContextHolder.getBean("messageSource")).getMessage(busException.getCode(), busException.getArgs(), request.getLocale());
				error = new ErrorObject(busException.getCode(),busException.getCode(), busException.getViewData());
			} else {
				error = new ErrorObject(this.exceptionAttribute, ex.getMessage(), null);
			}
			mv.addObject("errorObject", error);
		}
		return mv;
	}

	public static class ErrorObject {
		private String errorCode;
		private String errorText;
        private Object errorData;

        public Object getErrorData() {
            return errorData;
        }

        public void setErrorData(Object errorData) {
            this.errorData = errorData;
        }

        public ErrorObject(String errorCode, String errorText, Object errorData) {
			this.errorCode = errorCode;
			this.errorText = errorText;
            this.errorData = errorData;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorText() {
			return errorText;
		}

		public void setErrorText(String errorText) {
			this.errorText = errorText;
		}
	}
}
