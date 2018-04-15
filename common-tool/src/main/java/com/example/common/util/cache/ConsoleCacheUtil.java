package com.example.common.util.cache;

import org.springframework.cache.Cache.ValueWrapper;

public class ConsoleCacheUtil extends LocalCache{
	
	public static Object getValue(String key)
	{
		ValueWrapper element = consoleCache.get(key);
		if(element == null)
		{
			return null;
		}
		return element.get();
	}
	
	public static void setValue(String key,Object value)
	{
		consoleCache.put(key,value);
	}
	
	public static void remove(String key)
	{
		consoleCache.evict(key);
	}
	
	public static void removeAll()
	{
		consoleCache.clear();
	}
	
}
