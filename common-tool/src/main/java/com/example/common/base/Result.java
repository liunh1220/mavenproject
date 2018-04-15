package com.example.common.base;

import java.io.Serializable;


public class Result implements Serializable {

    private static final long serialVersionUID = -1659648311860514594L;
	
	public static final String SUCCESS = "0001"; //成功
	public static final String SUCCESS_MSG = "成功";
	
	public static final String FAILURE = "0002"; //失败
	
	public static final String REPEAT = "0003"; //重复调用
	
	public static final String ERROR_PARAM = "0004"; //无效参数
	
	public static final String ERROR_STATUS = "0005"; //无效状态
	
	public static final String INSUFFICIENT = "0006"; //余额不足
	
	public static final String NO_LOGIN = "0007"; //用户没有登录
	
	public static final String SAFETY_CERT_NOT_ALL = "0008"; //安全认证不全
	
	public static final String LOGIN_ERROR = "0009"; //用户连续登录错误超过10次
	
	public static final String SAFETY_CERT_NOT_ONE = "0010"; //没有设置一项安全认证
	
	public static final String NOT_REGISTER_BY_TRUSTEE = "0011"; //用户还没到第三方托管平台注册

	public static final String SYS_ERROR = "9999"; //系统错误代码
	
	public static final String WITHDRAW_SUBMIT = "9998"; //提现待审核
	
	public static final String WITHDRAW_MONEYBACK = "9997"; //提现资金退回
	
	public static final String ERROR_MSG = "系统繁忙，请稍后重试"; //系统消息 
	
	/**
	 * 成功构造方法<默认构造函数>
	 */
	public Result(Object data){
	    this.code = SUCCESS;
        this.data = data;
        this.msg = SUCCESS_MSG;
	}
	

	/**
	 * 构造方法
	 */
    public Result(String code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    
    /**
     * 返回代码
     */
    public String code;
    
    /**
     * 错误信息
     */
    public String msg;
    
    /**
     * 返回数据
     */
    public Object data;
    
    public Result setCode(String code) {
    	this.code = code;
    	return this;
    }
    
    public Result setMsg(String msg) {
    	this.msg = msg;
    	return this;
    }
    
    public Result setData(Object data) {
    	this.data = data;
    	return this;
    }

    /**
     * 返回执行结果代码
     * @return 结果代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 返回错误消息
     * @return 错误消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 返回执行结果数据
     * @return 结果数据，具体类型要看接口定义
     */
    public Object getData() {
        return data;
    }
    
    /**
     * 接口执行是否成功
     * @return 成功返回true，失败返回false
     */
    public boolean isSuccess() {
    	return SUCCESS.equals(code);
    }


    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + "]";
    }
    
    public static Result result(String msg) {
    	return result(null,msg,null);
    }
    
    public static Result result(String code, String msg) {
    	return result(code,msg,null);
    }
    
    public static Result result(String code, String msg, Object data) {
    	Result result = new Result(code, msg, data);
    	return result;
    }
    
}


