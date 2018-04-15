package com.example.testproject.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSONObject;

public class RedisCacheUtil{
	private ApplicationContext act = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
	//private ApplicationContextUtil act = new ApplicationContextUtil();
    private StringRedisTemplate redisTemplate = (StringRedisTemplate) act.getBean("jedisTemplate");
    
	public void put(String key, String value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(key, key, value);  
    }  
  
    @SuppressWarnings("static-access")
	public void put(String key, Object value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(key, key, new JSONObject().toJSON(value));  
    }  
  
    @SuppressWarnings("static-access")
	public <T> T get(String key, Class<T> className) {  
        Object obj = redisTemplate.opsForHash().get(key, key);  
        if(obj == null){  
            return null;  
        }  
        return new JSONObject().toJavaObject(new JSONObject().parseObject(String.valueOf(obj)), className);  
    }  
  
    public String get(String key) {  
        Object obj = redisTemplate.opsForHash().get(key, key);  
        if(obj == null){  
            return null;  
        }else{  
            return String.valueOf(obj);  
        }  
    } 
    
    public Boolean hasKey(String key) {  
    	if(key == null || key ==""){  
    		return false;  
    	}
    	return redisTemplate.hasKey(key);
    }
    
}
