package com.example.common.util;

import javax.servlet.http.HttpServletRequest;

import com.example.common.constant.Constant;
import com.example.common.util.cache.RemoteCacheUtil;
import com.example.common.util.cookie.CookieUtils;

public class Context {
	
	private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
	
	public static void setRequest(HttpServletRequest request)
	{
		requestHolder.set(request);
	}
	
	public static HttpServletRequest getRequest()
	{
		return requestHolder.get();
	}
	
	public static void clear() {
		requestHolder.remove();
	}
	
	public static LoginInfo getLoginInfo()
	{
		String ticket = CookieUtils.getCookieValue(getRequest(), Constant.TICKET);
		LoginInfo li = null;
		if(ticket != null)
		{
			li = (LoginInfo)RemoteCacheUtil.getObject(CookieUtils.getCookieValue(getRequest(), Constant.TICKET));
			return li;
		}
		return li;
	}
	
	public static void setLoginInfo(LoginInfo loginInfo)
	{
		RemoteCacheUtil.setObject(CookieUtils.getCookieValue(getRequest(), Constant.TICKET), loginInfo);
	}
	
	public static void logout()
	{
		RemoteCacheUtil.remove(CookieUtils.getCookieValue(getRequest(), Constant.TICKET));
	}
	
}
