package com.example.hibernate.util;

import net.sf.ehcache.Element;


/**
 * 本地缓存
 *
 */
public class LocalCacheUtil extends LocalCache{
	
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
	
}
