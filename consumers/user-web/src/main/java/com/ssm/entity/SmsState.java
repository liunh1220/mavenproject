package com.ssm.entity;

import java.io.Serializable;
import java.util.Map;

public class SmsState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 状态 */
	private SmsStateEnum smsState;
	
	/** 信息 */
	private String resultMsg;
	
	/** 异常堆栈信息 **/
	private String errorStackTrace;
	
	/** 异常简单描述 **/
	private String exceptionDesc;
	
	private String channel;

	private String[] phone;
	
	/**
	 * 合作商给我们的回执结果
	 */
	private Map<String,String> receipt;
	
	public Map<String, String> getReceipt() {
		return receipt;
	}

	public void setReceipt(Map<String, String> receipt) {
		this.receipt = receipt;
	}

	public String[] getPhone() {
		return phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public SmsStateEnum getSmsState() {
		return smsState;
	}

	public void setSmsState(SmsStateEnum smsState) {
		this.smsState = smsState;
	}

	public String getErrorStackTrace() {
		return errorStackTrace;
	}

	public void setErrorStackTrace(String errorStackTrace) {
		this.errorStackTrace = errorStackTrace;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}