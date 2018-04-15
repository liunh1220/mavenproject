package com.ssm.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	/**
	 * 图片验证码Key
	 */
	public static final String LOGIN_VALIDATE_IMAGE = "login_validate_image";

	public static final String YES = "Y";

	public static final String NO = "N";

	// 菜单资源编码
	public static final String RESOURCE_CATEGORY_MENU_CODE = "RS_MENU";

	public static Map<String, String> THREAD_NAME_MAP = new HashMap<String, String>();

	public static final String LOGIN_SECRET_KEY = "Ra12igd/uVE=";

	//	public static final String LOGIN_FLAG = "isLogin";
	//	
	//	public static final String LOGIN_USER = "loginUser";

	public static final String TICKET = "ticket";

	public static final String SEND_EMAIL_ADDRESS = "it-server@test.com";

	//淘宝店铺公用报警类型
	public static final String TAOBAO_COMMON_WARNING_MAIL = "-1";

	//分销发货邮件
	public static final String CHAIN_CONSIGN_MAIL = "2";

	//分销库存同步邮件
	public static final String CHAIN_STOCK_MAIL = "3";

	//系统错误预警邮件
	public static final String SYSMGT_ERROR_MAIL = "4";

	//调度告警发送邮件列表
	public static final String SYSMGT_JOB_ALERT_MAILLIST = "5";

	//所有渠道订单监控邮件发送列表
	public static final String SYSMGT_ORDER_MONITOR_MAILLIST = "6";

	//淘宝订单保存到优购时发送邮件地址
	public static final String TAOBAO_ORDER_ERROR_EMAIL = "7";

	//淘宝锁定库存超卖预警邮件
	public static final String TAOBAO_PROMT_WARNING_MAIL = "8";

	//淘宝锁定库存变化监控邮件
	public static final String TAOBAO_LOCK_STORE_SYNC_MAIL = "9";

	//发送商品信息异常告警邮件
	public static final String TAOBAO_SEND_COMMODITY_SYNC_MAIL = "10";

	//淘宝店铺APP过期邮件
	public static final String TAOBAO_ORDER_APPOUTTIME_EMAIL = "11";

	//拍立减库存告警发送告警邮件
	public static final String TAOBAO_PAI_LI_JIAN_WARNING_MAIL = "12";

	//SKU不一致
	public static final String TAOBAO_SKU_DIFFER_WARNING_MAIL = "13";

	/**
	 * 海关回执处理超时监控邮件
	 */
	public static final String CUSTOMS_RESULT_SOLVE_TIMEOUT = "15";

	//淘宝店铺地址库监控邮件
	public static final String TAOBAO_SELLER_ADDRESS_MONITOR = "16";

	//唯品会漏单监控邮件
	public static final String VIP_LOSS_PICK_MONITOR = "17";

	//唯品会推送失败的订单监控邮件
	public static final String VIP_FAILED_PICK_MONITOR = "18";
	
	//唯品会订单监控邮件
	public static final String VIP_PICK_MONITOR = "20";
	
	/**
	 * 京东店铺公用报警类型
	 */
	public static final String JD_COMMON_WARNING_MAIL = "19";
	
	// 推送失败的订单监控
	public static final String PUSH_FAILED_ORDER_MONITOR = "21";
	
	//唯品会门店编码回传失败的订单监控邮件
	public static final String VIP_FAILED_STORE_SN_MONITOR = "22";
}
