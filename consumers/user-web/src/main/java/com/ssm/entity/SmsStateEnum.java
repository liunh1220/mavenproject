package com.ssm.entity;
/**
 * Sms状态枚举
 * @author wuyang
 *
 */
public enum SmsStateEnum {

	/** 发送成功*/
	STATE_SUCCEED{
		public String getDesc(){
			return "短信发送成功";
		}
		public String getCode(){
			return "succeed";
		}
	},
	
	/** 发送失败 */
	STATE_FAILURE{
		public String getDesc(){
			return "短信发送失败";
		}
		public String getCode(){
			return "failure";
		}
	},
	/** 已过滤  */
	STATE_FILTER{
		public String getDesc(){
			return "手机号码非法或者无效，已被过滤";
		}
		public String getCode(){
			return "filter";
		}
	},
	
	/** 异常 */
	STATE_ERROR{
		public String getDesc(){
			return "发送短信时出现异常";
		}
		public String getCode(){
			return "error";
		}
	};
	
	public abstract String getDesc();
	
	public abstract String getCode();
}
