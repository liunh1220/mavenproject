package com.example.http.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * 本地缓存
 */
public class LocalCacheUtil {
	
	public static Cache localCache = (Cache)ServiceLocator.getInstance().getBeanFactory().getBean("localCache");
	
	public static Object getValue(String key)
	{
		Element element = localCache.get(key);
		if(element == null)
		{
			return null;
		}
		return element.getObjectValue();
	}
	
	public static void setValue(String key,Object value)
	{
		localCache.put(new Element(key,value));
	}
	
	public static void remove(String key)
	{
		localCache.remove(key);
	}
	
	public static void removeAll()
	{
		localCache.removeAll();
	}
	
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
