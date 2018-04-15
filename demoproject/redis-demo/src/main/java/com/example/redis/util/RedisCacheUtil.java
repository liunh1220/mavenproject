package com.example.redis.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class RedisCacheUtil{
	
    private StringRedisTemplate jedisTemplate = (StringRedisTemplate) new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml").getBean("jedisTemplate");
    
	public void set(String key, String value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        jedisTemplate.opsForHash().put(key, key, value);  
    }  
  
    public void set(String key, Object value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        jedisTemplate.opsForHash().put(key, key, new JSONObject().toJSON(String.valueOf(value)));  
    }  
  
    public <T> T get(String key, Class<T> className) {  
        Object obj = jedisTemplate.opsForHash().get(key, key);  
        if(obj == null){  
            return null;  
        }  
        return new JSONObject().toJavaObject(new JSONObject().parseObject(String.valueOf(obj)), className);  
    }  
  
    public String get(String key) {  
        Object obj = jedisTemplate.opsForHash().get(key,key);  
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
    
    public Boolean delete(String key) {  
    	if(key == null || key ==""){  
    		return false;  
    	}
    	return jedisTemplate.delete(key);
    }

}
