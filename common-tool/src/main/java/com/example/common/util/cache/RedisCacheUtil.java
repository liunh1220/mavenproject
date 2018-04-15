package com.example.common.util.cache;

import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSONObject;
import com.example.common.util.ApplicationContextUtil;

public class RedisCacheUtil{
	//ApplicationContext act = new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
	ApplicationContextUtil act = new ApplicationContextUtil();
    private StringRedisTemplate redisTemplate = (StringRedisTemplate) act.getBean("redisTemplate");
    
	public void put(String key, String value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(key, key, value);  
    }  
  
    public void put(String key, Object value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(key, key, new JSONObject().toJSON(value));  
    }  
  
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
    
}
