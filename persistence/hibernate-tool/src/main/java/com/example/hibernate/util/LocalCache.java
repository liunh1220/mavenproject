package com.example.hibernate.util;

import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;


import net.sf.ehcache.Cache;

/**
 * 本地缓存
 *
 */
public class LocalCache {
	
	private static CacheManager cacheManager = (CacheManager)ApplicationContextUtil.getApplicationContext().getBean("cacheManager");
	public static Cache localCache = (Cache) ApplicationContextUtil.getApplicationContext().getBean("localCache");
	public static TransactionAwareCacheDecorator consoleCache = (TransactionAwareCacheDecorator) cacheManager.getCache("consoleCache");
	public static TransactionAwareCacheDecorator frontCache = (TransactionAwareCacheDecorator) cacheManager.getCache("frontCache");
	
	public static String getMd5Key(String key)
	{
		if(key == null || "".equals(key) )
		{
			throw new RuntimeException("Key为null 或 空");
		}
		String md5Key = MD5Util.makeMd5Sum(key.getBytes());
		return md5Key;
	}
	
}
