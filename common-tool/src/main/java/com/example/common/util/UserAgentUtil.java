package com.example.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.example.common.constant.OperatingEnvironmentEnum;

public class UserAgentUtil {
	private static final String OSTYPE_ANDROID = "Android";
	private static final String OSTYPE_IOS = "IOS";
	private static final String OSTYPE_WAP_OTHER = "WAP-Browser";
	private static final String OSTYPE_WAP_WECHAT = "WAP-Wechat";

	public static String getEnv(String ua) {
		String result = "UNKNOWN";
		if (StringUtils.isBlank(ua)) {
			return result;
		}
		ua = ua.toUpperCase();
		if(ua.indexOf("PROJNAMEIOS") != -1){
			result = OSTYPE_IOS;
		}else if(ua.indexOf("PROJNAMEANDROID") != -1){
			result = OSTYPE_ANDROID;
		}else{
			if (ua.indexOf("MICROMESSENGER") > 0) {// 是微信浏览器
				result = OSTYPE_WAP_WECHAT;
			} else {
				result = OSTYPE_WAP_OTHER;
			}
		}
		return result;
	}
	
	  /**
     * 检测运行环境
     *
     * zheng.qq
     */
    public static String checkOperatingEnvironment(HttpServletRequest request) {
    	String rs = OperatingEnvironmentEnum.BROWSER_PLATFORM.getKey();
    	String ua = request.getHeader("User-Agent");
    	if(!StringUtils.isEmpty(ua)){
    		if (ua.contains("PROJNAMEIOS")) {
        		rs = OperatingEnvironmentEnum.IOS_APP_PLATFORM.getKey();
        	}else if(ua.contains("PROJNAMEANDROID")){
        		rs = OperatingEnvironmentEnum.ANDROID_APP_PLATFORM.getKey();
        	}else if(ua.indexOf("MicroMessenger") > 0){
        		rs = OperatingEnvironmentEnum.WX_PLATFORM.getKey();
        	}else{
        		rs = OperatingEnvironmentEnum.BROWSER_PLATFORM.getKey();
        	}
    	}
    	return rs;
    }
	
	public static boolean isApp(String ua) {
		if (StringUtils.isBlank(ua)) {
			return false;
		}
		ua = ua.toUpperCase();
		if(ua.indexOf("PROJNAMEIOS") != -1 || ua.indexOf("PROJNAMEANDROID") != -1){
			return true;
		}
		return false;
	}
}
