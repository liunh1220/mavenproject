package com.example.common.constant;

/**
 * redis中的一些常量
 *
 */
public class RedisConstant {
    /**
     * 在redis中存储短信验证码的值
     */
    public static final String PROJNAME_SMS_CODE_PREFIX = "projname:sms:code:phone:";

    /**
     * 微信token key
     */
    public static final String PROJNAME_REDIS_WX_TOKEN_KEY = "projname:wx:token";

    /**
     * 微信ticket key
     */
    public static final String PROJNAME_REDIS_WX_TICKET_KEY = "projname:wx:ticket";
    /**
     * 微信刷新时间
     */
    public static final long PROJNAME_REDIS_WX_REFRESH_EXPIRESIN = 60 * 115;

    /**
     * wx分布式锁key
     */
    public static final String PROJNAME_REDIS_TICKET_LOCK_KEY = "projname:wx:ticket.lock";

    /**
     * wx分布式锁key
     */
    public static final String PROJNAME_REDIS_ACCESS_TOKEN_LOCK_KEY = "projname:wx:access.token.lock";


}
