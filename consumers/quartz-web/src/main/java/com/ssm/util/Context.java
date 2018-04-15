package com.ssm.util;

import javax.servlet.http.HttpServletRequest;

public class Context {
	
	private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
	
	public static void setRequest(HttpServletRequest request){
		requestHolder.set(request);
	}
	
	public static HttpServletRequest getRequest(){
		return requestHolder.get();
	}
	
	public static void clear() {
		requestHolder.remove();
	}
	
	public static LoginInfo getLoginInfo(){
		String ticket = CookieUtil.getCookieValue(getRequest(), Constant.TICKET);
		LoginInfo li = null;
		if(ticket != null){
			li = (LoginInfo)RemoteCacheUtil.getObject(CookieUtil.getCookieValue(getRequest(), Constant.TICKET));
			return li;
		}
		return li;
	}
	
	public static void setLoginInfo(LoginInfo loginInfo){
		RemoteCacheUtil.setObject(CookieUtil.getCookieValue(getRequest(), Constant.TICKET), loginInfo);
	}
	
	public static void logout(){
		RemoteCacheUtil.remove(CookieUtil.getCookieValue(getRequest(), Constant.TICKET));
	}
	
}
