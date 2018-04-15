package com.ssm.util;

import java.io.Serializable;

public class LoginInfo implements Serializable{
	
	private static final long serialVersionUID = 3083264158716819420L;
	
	private String ticket;
	
	private String userId;
	
	private String userName;
	
	private String password;
	
	private String validationCode;
	
	private boolean isLogin = false;
	
	private boolean isSuperManager = false;

	public boolean isSuperManager() {
		return isSuperManager;
	}

	public void setSuperManager(boolean isSuperManager) {
		this.isSuperManager = isSuperManager;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
}
