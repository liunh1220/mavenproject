package com.example.common.util.cache;

import org.springframework.cache.Cache.ValueWrapper;

public class FrontCacheUtil extends LocalCache{
	
	public static Object getValue(String key)
	{
		ValueWrapper element = frontCache.get(key);
		if(element == null)
		{
			return null;
		}
		return element.get();
	}
	
	public static void setValue(String key,Object value)
	{
		frontCache.put(key,value);
	}
	
	public static void remove(String key)
	{
		frontCache.evict(key);
	}
	
	public static void removeAll()
	{
		frontCache.clear();
	}
	
}
