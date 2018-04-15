package com.ssm.entity;

/**
 * 模块
 */
public class ModelType {
	 /** 会员找回密码 */
    public static final String MODEL_TYPE_MEMBER_FIND_PASSWORD = "0";
    
    /** 订单发货 */
    public static final String MODEL_TYPE_ORDER_SENDPRODUCT = "1";
    
    /** 订单创建 */
    public static final String MODEL_TYPE_ORDER_CREATE = "2";
    
    /** 订单付款 */
    public static final String MODEL_TYPE_ORDER_PAYFORING = "3";
    
    /** 订单退货 */
    public static final String MODEL_TYPE_ORDER_GOODS_REGECTED = "4";
    
    /** 订单退款 */
    public static final String MODEL_TYPE_ORDER_DRAWBACK = "5";
    
    /** 订单到货 */
    public static final String MODEL_TYPE_ORDER_PRODUCT_ARRIVE = "6";
    
    /** 会员注册 */
    public static final String MODEL_TYPE_MEMBER_REGISTER = "7";
    
    /** 会员更改密码 */
    public static final String MODEL_TYPE_MEMBER_CHANGERPASSWORD = "8";
    
    /** 订单作废 */
    public static final String MODEL_TYPE_ORDER_CANCEL = "9";
    
    /** 优购购物体验评价邀请 */
    public static final String MODEL_TYPE_INVITE_PRODUCT_THEORY = "10";
    
    /** 促销 */
    public static final String MODEL_TYPE_INVITE_PRODUCT_ONSELL = "11";
    
    /** 货到付款 */
    public static final String MODEL_TYPE_INVITE_PRODUCT_ARRIVE_PAY = "12";
    
    /** 您在优购的订单换货成功 */
    public static final String MODEL_TYPE_ORDER_GOODS_CHANGE = "13";
    
    /** 2012集团员工专属优惠券email发送模板 */
    public static final String MODEL_TYPE_COUPON_INFO = "14";
    
    /** 用户行为 */
    public static final String MODEL_TYPE_MEMBER_ACTION = "15";
    
    /** 邀请用户点评邮件 */
    public static final String MODEL_TYPE_INVITATION_COMMENT = "20";
	
	/** 请激活您的帐号 */
    public final static String MODEL_TYPE_MALL_REGISTER_EMAIL_ACTIVE = "21";
    
    /** 邮箱账户绑定确认 */
    public final static String MODEL_TYPE_MALL_USERCENTER_EMAIL_BIND = "22";
    
    /** 无线 */
    public final static String MODEL_TYPE_WIRELESS = "23";
    
    /** 招商商家中心用户找回密码 */
    public final static String MODEL_TYPE_MERCHANT_FINDPWD = "24";
}

