package com.example.http.model;

import java.io.Serializable;


import java.util.Date;

import net.sf.json.JSONObject;

public class HttpCommonParas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -875413370255480882L;

	/**
	 * 加盟商名称
	 */
	private String shopName;

	/**
	 * 加盟商应用的AppId
	 */
	private String appId;

	/**
	 * 加盟商应用的AppKey
	 */
	private String appKey;
	
	/**
	 * 加盟商应用的appVersion
	 */
	private String appVersion;

	/**
	 * 加盟商应用的密钥
	 */
	private String appSession;

	/**
	 * 加盟商url
	 */
	private String url;
	
	/**
	 * 请求接口method
	 */
	private String method;
	
	/**
	 * 请求时的timestamp
	 */
	private Date timestamp;
	
	/**
	 * 请求时数据的format ：json,xml
	 */
	private String format;
	
	/**
	 * 请求sign
	 */
	private String sign;
	
	/**
	 * 请求signType : MD5 ...
	 */
	private String signMethod;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppSession() {
		return appSession;
	}

	public void setAppSession(String appSession) {
		this.appSession = appSession;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	@Override
	public String toString() {
		return String.valueOf(JSONObject.fromObject(this));
	}

}
