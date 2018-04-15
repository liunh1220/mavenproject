package com.example.shiro.redis;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCache;

public class RedisCacheManager implements CacheManager{
	
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);  
    
    // fast lookup by name map  
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();  
  
    private JedisPoolManager redisManager;  

	@Override
	public Collection<String> getCacheNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cache getCache(String name) {
		logger.debug("获取名称为: " + name + " 的RedisCache实例");  
        Cache c = caches.get(name);  
        /*if (c == null) {  
            c = new RedisCacheManager();  
            caches.put(name, c);  
        }  */
        return c;  
	}

	public JedisPoolManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(JedisPoolManager redisManager) {
		this.redisManager = redisManager;
	}

	public ConcurrentMap<String, Cache> getCaches() {
		return caches;
	}  
	
	
}
