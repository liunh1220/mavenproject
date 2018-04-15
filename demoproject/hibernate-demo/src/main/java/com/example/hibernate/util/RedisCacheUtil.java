package com.example.hibernate.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.alibaba.fastjson.JSONObject;

public class RedisCacheUtil{
	private ApplicationContext act = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
    private StringRedisTemplate jedisTemplate = (StringRedisTemplate) act.getBean("jedisTemplate");
    
	public void put(String key, String value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        jedisTemplate.opsForHash().put(key, key, value);  
    }  
  
    public void put(String key, Object value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        jedisTemplate.opsForHash().put(key, key, new JSONObject().toJSON(value));  
    }  
  
    public <T> T get(String key, Class<T> className) {  
        Object obj = jedisTemplate.opsForHash().get(key, key);  
        if(obj == null){  
            return null;  
        }  
        return new JSONObject().toJavaObject(new JSONObject().parseObject(String.valueOf(obj)), className);  
    }  
  
    public String get(String key) {  
        Object obj = jedisTemplate.opsForHash().get(key, key);  
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
    	return jedisTemplate.hasKey(key);
    }

}
