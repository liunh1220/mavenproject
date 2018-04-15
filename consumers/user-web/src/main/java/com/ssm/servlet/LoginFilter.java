package com.ssm.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.util.Constant;
import com.ssm.util.Context;
import com.ssm.util.CookieUtil;
import com.ssm.util.DateUtil;
import com.ssm.util.LoginInfo;
import com.ssm.util.MD5Util;
import com.ssm.util.RemoteCacheUtil;
import com.ssm.util.SecurityUtil;
/**
 * 登录过滤器
 * @author liu.nh
 *
 */
public class LoginFilter implements Filter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	private String redirectPath;
	
	public String getRedirectPath() {
		return redirectPath;
	}

	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		Context.setRequest(request);
	    String[] strArray = redirectPath.split(";");
        for (String str : strArray) {
            if (str.equals("")){
            	continue;
            }
            if (request.getRequestURL().indexOf(str) >= 0) {
                chain.doFilter(request, response);
                return;
            }
        }
		LoginInfo li = Context.getLoginInfo();
		if(li != null && li.isLogin()){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/loginPage.do");
		}
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		String redirectPath = conf.getInitParameter("redirectPath");
		this.setRedirectPath(redirectPath);
		logger.info("初始化登陆过滤器...");
	}
	
	public Object getCookieUserInfo(HttpServletRequest request)
	{
		Object obj = null;
		String ticket = null;
		try {
			ticket = CookieUtil.getCookieValue(request, MD5Util.getMD5String(DateUtil.getCurrentDate("yyyy-MM-dd")));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		if(ticket == null)
		{
			return null;
		}
		String value = RemoteCacheUtil.getValue(ticket);
		if(value != null)
		{
			byte[] userInfo = null;
			try {
				userInfo = SecurityUtil.decryptDES(Hex.decodeHex(value.toCharArray()), Constant.LOGIN_SECRET_KEY);
				obj = SerializationUtils.deserialize(userInfo);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				return null;
			}
		}
		
		return obj;
	}

}
