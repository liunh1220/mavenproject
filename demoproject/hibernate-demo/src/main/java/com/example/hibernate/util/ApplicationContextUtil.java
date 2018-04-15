package com.example.hibernate.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContext;


public class ApplicationContextUtil implements ApplicationContextAware {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private static RequestContext requestContext;
	private static ThreadLocal<ApplicationContextUtil> apLocal = new ThreadLocal<ApplicationContextUtil>();
	private static ApplicationContextUtil applicationContextUtil = null;
	private static ApplicationContext applicationContext;

	public static String getMessage(String code) {
		return requestContext.getMessage(code);
	}

	public HttpSession getSession() {
		if (getRequest() == null)
			return null;
		return getRequest().getSession();
	}

	public ServletContext getServletContext() {
		if (getSession() == null)
			return null;
		return getSession().getServletContext();
	}

	public Object getBean(String name) {
		if (getServletContext() == null)
			return null;
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(name);
	}

	public static ApplicationContextUtil newInstan() {
		if (apLocal.get() == null) {
			applicationContextUtil = new ApplicationContextUtil();
			apLocal.set(applicationContextUtil);
			return applicationContextUtil;
		}
		return (ApplicationContextUtil) apLocal.get();
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
		requestContext = new RequestContext(request);
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}